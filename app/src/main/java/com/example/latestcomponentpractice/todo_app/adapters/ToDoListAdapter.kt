package com.example.latestcomponentpractice.todo_app.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.latestcomponentpractice.databinding.ListViewCustomItemBinding
import com.example.latestcomponentpractice.todo_app.model.Person

// MutableList<Person>, Person

class ToDoListAdapter(val onClick : (Person) -> Unit) : ListAdapter<Person, ToDoListAdapter.ToDoListAdapterViewHolder>(ToDoListAdapterDiffUtil()) {

    inner class  ToDoListAdapterViewHolder(val binding: ListViewCustomItemBinding) : ViewHolder(binding.root) {

        init {
            binding.cardView.setOnClickListener {
                onClick(getItem(adapterPosition))
            }
        }

        fun bind(person: Person) {
            binding.textViewOnePass.text = person.name
            binding.textViewTwoPass.text = person.age.toString()
            binding.cardView.setBackgroundColor(Color.parseColor("#${person.age}"))
        }
    }

    class ToDoListAdapterDiffUtil : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return newItem == oldItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListAdapterViewHolder {
        return ToDoListAdapterViewHolder(
            ListViewCustomItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ToDoListAdapterViewHolder, position: Int) {
        val person = getItem(position)
        holder.bind(person)
    }

}