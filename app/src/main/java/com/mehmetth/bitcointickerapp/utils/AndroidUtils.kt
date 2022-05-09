package com.mehmetth.bitcointickerapp.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar

class AndroidUtils {
    companion object {
        fun shakeView(view: View?, x: Int, num: Int) {
            if (view == null) {
                return
            }
            if (num == 6) {
                view.translationX = 0f
                return
            }
            val animatorSet = AnimatorSet()
            val value = view.resources.displayMetrics.density * x
            animatorSet.playTogether(ObjectAnimator.ofFloat(view, "translationX", value))
            animatorSet.duration = 50
            animatorSet.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    shakeView(view, if (num == 5) 0 else -x, num + 1)
                }
            })
            animatorSet.start()
        }

        fun showSnackBar(view: View, message: String){
            val snackBar = Snackbar.make(view,message,Snackbar.LENGTH_LONG)
            snackBar.show()
            shakeView(snackBar.view, 10, 0)
        }

        fun serviceErrorImage(view: View, value: Boolean){
            if(value){
                view.visibility = View.VISIBLE
            }else{
                view.visibility = View.INVISIBLE
            }
            shakeView(view, 10, 0)
        }
    }
}