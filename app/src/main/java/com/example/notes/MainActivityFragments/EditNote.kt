package com.example.notes.MainActivityFragments

import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.notes.Database.Note
import com.example.notes.Database.NoteDatabase
import com.example.notes.databinding.FragmentEditNoteBinding
import kotlinx.coroutines.*
import java.util.*

class EditNote : Fragment() {

    private lateinit var binding: FragmentEditNoteBinding
    private lateinit var db: NoteDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)

        db = NoteDatabase.getDatabase(requireContext())
        val noteDao = db.noteDao

        // Back Button
        binding.ibBack.setOnClickListener {
            activity?.onBackPressed()
        }

        // Save Note
        binding.ibSave.setOnClickListener{

            val title = binding.etNoteTitle.text.toString()
            val content = binding.etNoteContent.text.toString()

            val note = Note(
                id = 0,
                title = title,
                content = content
//                date = Date().toString()
            )

            CoroutineScope(Dispatchers.IO).launch {
                noteDao.insert(note)
            }



            val action = EditNoteDirections.actionEditNoteToNotes()
            findNavController().navigate(action)
        }

        return binding.root
    }

}