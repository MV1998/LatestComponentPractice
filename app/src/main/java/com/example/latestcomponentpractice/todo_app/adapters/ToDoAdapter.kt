package com.example.latestcomponentpractice.todo_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.latestcomponentpractice.R
import com.example.latestcomponentpractice.todo_app.model.Person

class ToDoAdapter(private var personList: MutableList<Person>) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    inner class ToDoViewHolder(view : View) : ViewHolder(view) {
        private var textViewOnePass : TextView
        private var textViewTwoPass : TextView

        init {
            textViewOnePass = view.findViewById(R.id.textViewOnePass)
            textViewTwoPass = view.findViewById(R.id.textViewTwoPass)
            textViewTwoPass.setOnClickListener {

            }
        }

        fun bind() {
            val person = personList[adapterPosition]
            textViewOnePass.text = person.name
            textViewTwoPass.text = person.age.toString()
        }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_view_custom_item, parent, false)
        return ToDoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind()
    }

    fun notifyDataChange(updateList : MutableList<Person>) {
        this.personList = updateList
        notifyItemChanged(updateList.size)
    }
}