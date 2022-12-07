package com.example.taskeight.fragments
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taskeight.databinding.FragmentHomeBinding
import com.example.taskeight.db.GameRoomDatabase
import com.example.taskeight.models.Game
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var gamesDB: GameRoomDatabase
    private var game: Game? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        gamesDB = GameRoomDatabase.getDatabase(container!!.context)

        binding.addGameButton.setOnClickListener {
            onAdd()
        }

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    @OptIn(DelicateCoroutinesApi::class)
    private fun onAdd() {
        val title = binding.addGameTitle.text
        val desc = binding.addGameDescription.text
        val releaseYear = binding.addGameYear.text
        val imageUrl = binding.addGameImageUrl.text

        if (title.isEmpty() || desc.isEmpty() || releaseYear.isEmpty() || imageUrl.isEmpty()) {
            Snackbar.make(binding.root, "Please fill empty fields", Snackbar.LENGTH_LONG).show()
            return
        }
        game = Game(
            null,
            title.toString(),
            desc.toString(),
            releaseYear.toString(),
            imageUrl.toString(),
        )

        GlobalScope.launch(Dispatchers.IO) {
            if (game!!.id == null) {
                gamesDB.gameDao().insert(
                    game!!
                )
            } else {
                gamesDB.gameDao().updateGame(game!!)
            }
        }

        binding.addGameTitle.text.clear()
        binding.addGameDescription.text.clear()
        binding.addGameYear.text.clear()
        binding.addGameImageUrl.text.clear()
    }

}