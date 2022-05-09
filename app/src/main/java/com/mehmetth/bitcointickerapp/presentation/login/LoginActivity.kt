package com.mehmetth.bitcointickerapp.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mehmetth.bitcointickerapp.R
import com.mehmetth.bitcointickerapp.databinding.ActivityLoginBinding
import com.mehmetth.bitcointickerapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}