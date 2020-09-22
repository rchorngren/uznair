package com.example.uznair

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HighScoreRecyclerAdapter(val context: Context, var sortedList: List<Player>) : RecyclerView.Adapter<HighScoreRecyclerAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)

    override fun getItemCount(): Int {
        return sortedList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.high_score_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var highScoreEntry = sortedList[position]


        holder.textViewName.text = highScoreEntry.name
        holder.textViewPoints.text = highScoreEntry.score.toString()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById<TextView>(R.id.highScoreNameTextView)
        val textViewPoints = itemView.findViewById<TextView>(R.id.highScorePointsTextView)
    }
}