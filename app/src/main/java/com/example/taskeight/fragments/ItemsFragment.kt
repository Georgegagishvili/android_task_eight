package com.example.taskeight.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskeight.R
import com.example.taskeight.adapters.GameRecyclerAdapter
import com.example.taskeight.databinding.FragmentItemsBinding
import com.example.taskeight.db.GameRoomDatabase
import com.example.taskeight.models.Game

class ItemsFragment : Fragment() {
    private lateinit var gamesDB: GameRoomDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var games: ArrayList<Game>
    lateinit var binding: FragmentItemsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentItemsBinding.inflate(layoutInflater)
        gamesDB = GameRoomDatabase.getDatabase(container!!.context)

        init()

        return binding.root
    }

    private fun init() {
        recyclerView = binding.mainRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        games = ArrayList()
        updateData()
    }

    private fun updateData() {
        val dataFromDB = ArrayList(gamesDB.gameDao().getAll())
        if (dataFromDB != games) {
            games = ArrayList(gamesDB.gameDao().getAll())
            recyclerView.adapter = GameRecyclerAdapter(games)
        }
    }


}