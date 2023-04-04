package com.example.notes.MainActivityFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentNotesBinding

class Notes : Fragment() {

    private lateinit var binding: FragmentNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNotesBinding.inflate(layoutInflater, container, false)

        binding.ibAddNote.setOnClickListener {
            val action = NotesDirections.actionNotesToEditNote()
            findNavController().navigate(action)
        }

        return binding.root
    }

}