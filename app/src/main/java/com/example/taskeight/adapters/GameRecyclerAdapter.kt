package com.example.taskeight.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskeight.R
import com.example.taskeight.models.Game
import com.squareup.picasso.Picasso

class GameRecyclerAdapter(private var games: ArrayList<Game>) :
    RecyclerView.Adapter<GameRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val picasso: Picasso = Picasso.get()
        fun bind(game: Game) {
            picasso.load(game.imageUrl).placeholder(R.drawable.ic_launcher_background)
                .into(itemView.findViewById<ImageView>(R.id.gameImage))
            itemView.findViewById<TextView>(R.id.gameTitle).text = game.title
            itemView.findViewById<TextView>(R.id.gameReleaseYear).text = game.releaseYear
            itemView.findViewById<TextView>(R.id.gameDescription).text = game.description
        }

    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_item, parent, false)

        return ViewHolder(view)
    }
}