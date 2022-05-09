package com.mehmetth.bitcointickerapp.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.mehmetth.bitcointickerapp.R
import com.mehmetth.bitcointickerapp.databinding.FragmentSignInBinding
import com.mehmetth.bitcointickerapp.databinding.FragmentSignUpBinding
import com.mehmetth.bitcointickerapp.presentation.MainActivity
import com.mehmetth.bitcointickerapp.presentation.home.HomeFragmentDirections
import com.mehmetth.bitcointickerapp.utils.AndroidUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignInBinding.bind(view)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView.setOnClickListener {
            requireView().findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
        }

        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Intent(requireActivity(), MainActivity::class.java).also {
                            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(it)
                        }
                    } else {
                        AndroidUtils.showSnackBar(requireView(),it.exception.toString())
                        AndroidUtils.shakeView(binding.button,10,0)
                    }
                }
            }
            else {
                AndroidUtils.showSnackBar(requireView(),getString(R.string.empty_fields))
                AndroidUtils.shakeView(binding.emailEt,10,0)
                AndroidUtils.shakeView(binding.passET,10,0)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser != null){
            Intent(requireActivity(), MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}