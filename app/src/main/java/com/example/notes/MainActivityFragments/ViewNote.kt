package com.example.notes.MainActivityFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import com.example.notes.R
import com.example.notes.databinding.FragmentViewNoteBinding
import androidx.navigation.fragment.findNavController
import com.example.notes.Database.Note
import com.example.notes.Database.NoteDatabase
import com.example.notes.MainActivity
import kotlinx.coroutines.launch


class ViewNote : Fragment() {

    private lateinit var binding: FragmentViewNoteBinding
    private lateinit var db: NoteDatabase
    private var noteId: Int = 0
    private lateinit var note: Note

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentViewNoteBinding.inflate(layoutInflater, container, false)

        noteId = (activity as MainActivity).getId()
        db = NoteDatabase.getDatabase(requireContext())

        // Get Note
        lifecycleScope.launch {
            note = db.noteDao.getNoteById(noteId)
            binding.tvNoteTitle.text = note.title
            binding.tvNoteContent.text = note.content
            binding.tvNoteDate.text = note.date
        }

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
            deleteNote()
        }

        return binding.root
    }

    private fun deleteNote() {
        lifecycleScope.launch {
            db.noteDao.delete(note)
            activity?.onBackPressed()
        }
    }
}