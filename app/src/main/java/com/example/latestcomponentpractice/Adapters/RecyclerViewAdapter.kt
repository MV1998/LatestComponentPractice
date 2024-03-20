package com.example.latestcomponentpractice.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.latestcomponentpractice.Models.Box
import com.example.latestcomponentpractice.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RecyclerViewAdapter(val activity : Context,
                          private val boxes : ArrayList<Box>) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {


    inner class RecyclerViewHolder(row: View) : ViewHolder(row) {
        var textViewOnePass : TextView? = null
        var textViewTwoPass : TextView? = null
        var cardView : CardView? = null

        init {
            this.textViewOnePass = row?.findViewById(R.id.textViewOnePass)
            this.textViewTwoPass = row?.findViewById(R.id.textViewTwoPass)
            this.cardView = row?.findViewById(R.id.cardView)
            cardView?.setOnClickListener {
                CoroutineScope(Dispatchers.IO).apply {
                    var result = async(Dispatchers.IO) {
                        boxes[adapterPosition].startPackaging()
                    }
                    launch(Dispatchers.Main) {
                        var res = result.await()
                        Toast.makeText(activity, "$res", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_view_custom_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return boxes.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val box = boxes[position]
        holder.textViewOnePass?.text = box.name
        if (box.isPackaged) {
            holder.textViewTwoPass?.text = "Packaged"
        }else {
            holder.textViewTwoPass?.text = "Not Packaged"
        }
    }
}