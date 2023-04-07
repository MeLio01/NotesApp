package com.example.notes.MainActivityFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.notes.Database.Note
import com.example.notes.Database.NoteDatabase
import com.example.notes.RecyclerView.Adapter
import com.example.notes.databinding.FragmentNotesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Notes : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private lateinit var db: NoteDatabase
    private lateinit var notes: List<Note>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNotesBinding.inflate(layoutInflater, container, false)

        db = NoteDatabase.getDatabase(requireContext())

        // Get Notes
        lifecycleScope.launch {
            notes = db.noteDao.getAll()
            val adapter = Adapter(requireContext(), notes.toList())
            binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
            binding.rvNotes.adapter = adapter

        }

        // Add Note
        binding.ibAddNote.setOnClickListener {
            val action = NotesDirections.actionNotesToEditNote()
            findNavController().navigate(action)
        }

        // Search Note
        binding.ibSearch.setOnClickListener {


        }


        return binding.root
    }


}