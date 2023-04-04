package com.example.notes.MainActivityFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentEditNoteBinding

class EditNote : Fragment() {

    private lateinit var binding: FragmentEditNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)

        binding.ibBack.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.ibSave.setOnClickListener{
            // save note to database

            val action = EditNoteDirections.actionEditNoteToNotes()
            findNavController().navigate(action)
        }

        return binding.root
    }

}