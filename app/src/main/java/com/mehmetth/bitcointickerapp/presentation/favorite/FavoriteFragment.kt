package com.mehmetth.bitcointickerapp.presentation.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.mehmetth.bitcointickerapp.R
import com.mehmetth.bitcointickerapp.databinding.FragmentFavoriteBinding
import com.mehmetth.bitcointickerapp.databinding.FragmentHomeBinding
import com.mehmetth.bitcointickerapp.domain.service.entity.ServiceEntity
import com.mehmetth.bitcointickerapp.presentation.home.HomeFragmentDirections
import com.mehmetth.bitcointickerapp.presentation.home.adapter.HomeAdapter
import com.mehmetth.bitcointickerapp.utils.Constant
import com.mehmetth.bitcointickerapp.utils.FirebaseUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment  : Fragment(R.layout.fragment_favorite) {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeAdapter : HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoriteBinding.bind(view)

        binding.favoriteProgressBar.visibility = View.VISIBLE
        readAllFavoriteCoins()
    }

    fun readAllFavoriteCoins(){
        val favoriteCoinList : MutableList<ServiceEntity> = mutableListOf()
        FirebaseUtils.readFavoriteCoinFireStore(requireContext(),requireView()){ hasmap ->
            hasmap.keys.forEach { item ->
                favoriteCoinList.add(hasmap[item]!!)
            }

            binding.favoriteProgressBar.visibility = View.INVISIBLE
            if(favoriteCoinList.size == 0 ){
                binding.favoriteInfoText.visibility = View.VISIBLE
            }
            else{
                binding.favoriteInfoText.visibility = View.INVISIBLE
                homeAdapter = HomeAdapter(requireActivity(), favoriteCoinList){view,coin,item ->
                    if(!item){
                        if(view.visibility == View.VISIBLE){
                            view.visibility = View.GONE
                        }
                        else{
                            view.visibility = View.VISIBLE
                        }
                    }
                    else{
                        val action = FavoriteFragmentDirections.actionNavigationFavoriteToDetailFragment(coin)
                        requireView().findNavController().navigate(action)
                    }
                }

                binding.favoriteRecyclerview.apply {
                    adapter = homeAdapter
                    layoutManager  = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
            }

        }
    }
}