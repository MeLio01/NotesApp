package com.example.notes.MainActivityFragments

import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.notes.Database.Note
import com.example.notes.Database.NoteDatabase
import com.example.notes.MainActivity
import com.example.notes.databinding.FragmentEditNoteBinding
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class EditNote : Fragment() {

    private lateinit var binding: FragmentEditNoteBinding
    private lateinit var db: NoteDatabase
    private var noteId: Int = 0
    private lateinit var note: Note

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)

        noteId = (activity as MainActivity).getId()
        db = NoteDatabase.getDatabase(requireContext())

        // Get Note
        if (noteId != -1) {
            lifecycleScope.launch {
                note = db.noteDao.getNoteById(noteId)
                binding.etNoteTitle.setText(note.title)
                binding.etNoteContent.setText(note.content)
            }
        }

        // Back Button
        binding.ibBack.setOnClickListener {
            activity?.onBackPressed()
        }

        // Save Note
        binding.ibSave.setOnClickListener{

            val title = binding.etNoteTitle.text.toString()
            val content = binding.etNoteContent.text.toString()

            if (noteId != -1) {
                if (title != note.title) note.title = title
                if (content != note.content) note.content = content
            }
            else {
                note = Note(
                    id = 0,
                    title = title,
                    content = content
                )
            }

            saveNote(note)

            val action = EditNoteDirections.actionEditNoteToNotes()
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun saveNote(note: Note) {
        lifecycleScope.launch {
            note.date = getDate()
            db.noteDao.insert(note)
        }
    }

    private fun getDate(): String {
        val date = Date()
        val formatter = SimpleDateFormat("MM/dd/yyyy")
        val formattedDate = formatter.format(date)

        val inputFormat = SimpleDateFormat("MM/dd/yyyy")
        val outputFormat = SimpleDateFormat("MMMM d, yyyy")
        val parsedDate = inputFormat.parse(formattedDate)
        val finalDate = outputFormat.format(parsedDate)

        return finalDate
    }

}