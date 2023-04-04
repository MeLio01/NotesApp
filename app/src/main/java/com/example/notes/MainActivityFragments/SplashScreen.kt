package com.example.notes.MainActivityFragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.notes.databinding.FragmentSplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)

        Handler(Looper.myLooper()!!).postDelayed({

            val action = SplashScreenDirections.actionSplashScreenToNotes()
            findNavController().navigate(action)

        }, 3000)

        return binding.root
    }


}