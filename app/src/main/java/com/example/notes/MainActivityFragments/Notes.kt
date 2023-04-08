package com.example.notes.MainActivityFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.room.Room
import com.example.notes.Database.Note
import com.example.notes.Database.NoteDatabase
import com.example.notes.MainActivity
import com.example.notes.RecyclerView.Adapter
import com.example.notes.RecyclerView.CellClickListener
import com.example.notes.databinding.FragmentNotesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Notes : Fragment(), CellClickListener {

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
            val adapter = Adapter(requireContext(), notes.toList(), this@Notes)
            binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
            binding.rvNotes.adapter = adapter
        }

        // Add Note
        binding.ibAddNote.setOnClickListener {
            (activity as MainActivity).setId(-1)
            val action = NotesDirections.actionNotesToEditNote()
            findNavController().navigate(action)
        }

        // Search Note
        binding.ibSearch.setOnClickListener {


        }


        return binding.root
    }


    override fun onCellClickListener(id: Int) {
//        setFragmentResult("noteid", bundleOf("id" to id))
        Log.d("Notes", "onCellClickListener: $id")

        (activity as MainActivity).setId(id)

        val action = NotesDirections.actionNotesToViewNote()
        findNavController().navigate(action)
    }

}