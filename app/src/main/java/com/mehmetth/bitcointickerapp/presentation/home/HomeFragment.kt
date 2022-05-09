package com.mehmetth.bitcointickerapp.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehmetth.bitcointickerapp.R
import com.mehmetth.bitcointickerapp.databinding.FragmentHomeBinding
import com.mehmetth.bitcointickerapp.domain.service.entity.ServiceEntity
import com.mehmetth.bitcointickerapp.presentation.home.adapter.HomeAdapter
import com.mehmetth.bitcointickerapp.utils.AndroidUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val serviceViewModel : ServiceViewModel by viewModels()
    private lateinit var homeAdapter : HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        observeServices()
        observeServicesState()
        serviceViewModel.fetchService()

        binding.swipeRefreshLayout.setOnRefreshListener {
            observeServices()
            observeServicesState()
            binding.swipeRefreshLayout.isRefreshing=false
        }

        binding.homeSearchview.isSubmitButtonEnabled = true
        binding.homeSearchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                homeAdapter.updateDataList(serviceViewModel.getCoinDetail(query.toString()))
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        binding.homeSearchview.setOnCloseListener {
            observeServices()
            observeServicesState()

            true
        }
    }

    //Service Value and State Handle
    private fun observeServices(){
        serviceViewModel.mCoins
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { service ->
                binding.homeProgressBar.visibility = View.VISIBLE
                if(service.isNotEmpty())
                {
                    homeAdapter = HomeAdapter(requireActivity(), service){view,coin,item ->
                        if(!item){
                            if(view.visibility == View.VISIBLE){
                                view.visibility = View.GONE
                            }
                            else{
                                view.visibility = View.VISIBLE
                            }
                        }
                        else{
                            val action = HomeFragmentDirections.actionNavigationListToDetailFragment(coin)
                            requireView().findNavController().navigate(action)
                        }
                    }

                    binding.homeRecyclerview.apply {
                        adapter = homeAdapter
                        layoutManager  = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
    private fun observeServicesState(){
        serviceViewModel.mState
            .flowWithLifecycle (viewLifecycleOwner.lifecycle,  Lifecycle.State.STARTED)
            .onEach { state ->
                handleAllServicesState(state)
            }
            .launchIn (viewLifecycleOwner.lifecycleScope)
    }
    private fun handleAllServicesState(state: ServicesState){
        when(state){
            is ServicesState.IsLoading -> handleServicesLoading(state.isLoading)
            is ServicesState.IsError -> handleServicesError(state.isError)
            is ServicesState.IsErrorMessage -> handleServicesErrorMessage(state.isErrorValue)
            is ServicesState.Init -> Unit
        }
    }
    private fun handleServicesLoading(isLoading: Boolean){
        if (!isLoading){
            binding.homeProgressBar.visibility = View.INVISIBLE
        }
    }
    private fun handleServicesError(isError: Boolean){
        AndroidUtils.serviceErrorImage(binding.homeErrorImage,isError)
    }
    private fun handleServicesErrorMessage(isErrorValue: String){
        AndroidUtils.showSnackBar(requireView(),isErrorValue)
    }
}