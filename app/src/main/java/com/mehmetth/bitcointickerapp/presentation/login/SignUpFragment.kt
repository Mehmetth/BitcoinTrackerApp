package com.mehmetth.bitcointickerapp.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.mehmetth.bitcointickerapp.R
import com.mehmetth.bitcointickerapp.databinding.FragmentHomeBinding
import com.mehmetth.bitcointickerapp.databinding.FragmentSignUpBinding
import com.mehmetth.bitcointickerapp.utils.AndroidUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignUpBinding.bind(view)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            requireView().findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
                        }
                        else {
                            AndroidUtils.showSnackBar(requireView(),it.exception.toString())
                        }
                    }
                } else {
                    AndroidUtils.showSnackBar(requireView(),getString(R.string.password_not_matching))
                    AndroidUtils.shakeView(binding.passET,10,0)
                    AndroidUtils.shakeView(binding.confirmPassEt,10,0)
                }
            } else {
                AndroidUtils.showSnackBar(requireView(),getString(R.string.empty_fields))
            }
        }

        binding.textView.setOnClickListener {
            requireView().findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
        }
    }
}