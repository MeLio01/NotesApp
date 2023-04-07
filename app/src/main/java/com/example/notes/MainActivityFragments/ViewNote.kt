package com.example.notes.MainActivityFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notes.R
import com.example.notes.databinding.FragmentViewNoteBinding
import androidx.navigation.fragment.findNavController


class ViewNote : Fragment() {

    private lateinit var binding: FragmentViewNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentViewNoteBinding.inflate(layoutInflater, container, false)

        // Back Button
        binding.ibBack.setOnClickListener {
            activity?.onBackPressed()
        }

        // Edit Note
        binding.ibEdit.setOnClickListener {
            val action = ViewNoteDirections.actionViewNoteToEditNote()
            findNavController().navigate(action)
        }

        // Delete Note
        binding.ibDelete.setOnClickListener {

        }

        return binding.root
    }

}