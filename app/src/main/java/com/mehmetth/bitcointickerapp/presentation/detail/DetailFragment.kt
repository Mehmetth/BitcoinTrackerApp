package com.mehmetth.bitcointickerapp.presentation.detail

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.firestore.FirebaseFirestore
import com.mehmetth.bitcointickerapp.R
import com.mehmetth.bitcointickerapp.databinding.FragmentDetailBinding
import com.mehmetth.bitcointickerapp.domain.service.entity.ServiceEntity
import com.mehmetth.bitcointickerapp.utils.AndroidUtils
import com.mehmetth.bitcointickerapp.utils.Constant
import com.mehmetth.bitcointickerapp.utils.FirebaseUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail)  {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel : DetailViewModel by viewModels()

    val args : DetailFragmentArgs by navArgs()

    private var foundCoinStatusFireStore : Boolean = false
    private var foundCoinDocumentIdFireStoreValue : String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        readAllFireStore()

        observeServiceDetail()
        observeServicesState()
        detailViewModel.fetchServiceDetail(args.coinData!!.id)

        binding.favoriteButton.setOnClickListener {
            readAllFireStore()
            if(foundCoinStatusFireStore){
                FirebaseUtils.deleteFavoriteCoinFireStore(requireContext(), requireView(),foundCoinDocumentIdFireStoreValue)
                binding.favoriteButton.setImageResource(R.drawable.favorite_unselected_icon)
            }
            else{
                FirebaseUtils.addFireStore(requireContext(), requireView(),args.coinData!!)
            }
        }
    }

    fun readAllFireStore(){
        FirebaseUtils.readFavoriteCoinFireStore(requireContext(), requireView()){ hasmap ->
            hasmap.keys.forEach { item ->
                if(hasmap[item]!!.id == args.coinData!!.id){
                    foundCoinStatusFireStore = true
                    foundCoinDocumentIdFireStoreValue = item
                    binding.favoriteButton.setImageResource(R.drawable.favorite_selected_icon)
                    return@readFavoriteCoinFireStore
                }
            }
        }
    }
    //Service Value and State Handle
    @SuppressLint("SetTextI18n")
    private fun observeServiceDetail(){
        detailViewModel.mCoinDetail
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { service ->
                binding.detailProgressBar.visibility = View.VISIBLE
                if(service != null)
                {
                    Glide.with(requireContext())
                        .load(service.image!!.large)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .error(android.R.drawable.ic_menu_report_image)
                        .into(binding.detailCoinImage)

                    if(service.name.isNullOrEmpty()){
                        binding.detailCoinName.text = getString(R.string.no_value)
                    }
                    else{
                        binding.detailCoinName.text = service.name
                    }
                    if(service.symbol.isNullOrEmpty()){
                        binding.detailCoinSymbol.text = getString(R.string.no_value)
                    }
                    else{
                        binding.detailCoinSymbol.text = service.symbol
                    }
                    if(service.market_data!!.current_priceResponse!!.usd.toString().isEmpty()){
                        binding.detailCoinStatus.text = getString(R.string.no_value)
                    }
                    else{
                        binding.detailCoinStatus.text = "${Constant.VSCURRENCYSYMBOL} ${service.market_data!!.current_priceResponse!!.usd.toString()}"
                    }
                    if(service.market_data!!.high_24h!!.usd.toString().isEmpty()){
                        binding.detailCoinHigh.text = getString(R.string.no_value)
                    }
                    else{
                        binding.detailCoinHigh.text = "${Constant.VSCURRENCYSYMBOL} ${service.market_data!!.high_24h!!.usd.toString()}"
                    }
                    if(service.market_data!!.low_24h!!.usd.toString().isEmpty()){
                        binding.detailCoinLow.text = getString(R.string.no_value)
                    }
                    else{

                        binding.detailCoinLow.text = "${Constant.VSCURRENCYSYMBOL} ${service.market_data!!.low_24h!!.usd.toString()}"
                    }
                    if(service.hashing_algorithm.isNullOrEmpty()){
                        binding.hashingAlgorithm.text = getString(R.string.no_value)
                    }
                    else{
                        binding.hashingAlgorithm.text = service.hashing_algorithm
                    }
                    if(service.block_time_in_minutes.toString().isEmpty()){
                        binding.refreshInterval.text = getString(R.string.no_value)
                    }
                    else{
                        binding.refreshInterval.text = service.block_time_in_minutes.toString()
                    }
                    if(service.description!!.en.isNullOrEmpty()){
                        binding.description.text = getString(R.string.no_value)
                    }
                    else{
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            binding.description.text = Html.fromHtml(service.description!!.en, Html.FROM_HTML_MODE_COMPACT)
                        } else {
                            binding.description.text = Html.fromHtml(service.description!!.en)
                        }
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    //Detail Service Data and State Operaiton
    private fun observeServicesState(){
        detailViewModel.mState
            .flowWithLifecycle (viewLifecycleOwner.lifecycle,  Lifecycle.State.STARTED)
            .onEach { state ->
                handleAllServicesState(state)
            }
            .launchIn (viewLifecycleOwner.lifecycleScope)
    }
    private fun handleAllServicesState(state: ServiceDetailState){
        when(state){
            is ServiceDetailState.IsLoading -> handleServiceDetailLoading(state.isLoading)
            is ServiceDetailState.IsError -> handleServiceDetailError(state.isError)
            is ServiceDetailState.IsErrorMessage -> handleServiceDetailErrorMessage(state.isErrorValue)
            is ServiceDetailState.Init -> Unit
        }
    }
    private fun handleServiceDetailLoading(isLoading: Boolean){
        if (!isLoading){
            binding.detailProgressBar.visibility = View.INVISIBLE
        }
    }
    private fun handleServiceDetailError(isError: Boolean){
        AndroidUtils.serviceErrorImage(binding.detailErrorImage,isError)
    }
    private fun handleServiceDetailErrorMessage(isErrorValue: String){
        AndroidUtils.showSnackBar(requireView(),isErrorValue)
    }

}