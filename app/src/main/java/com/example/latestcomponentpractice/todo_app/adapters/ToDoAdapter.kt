package com.example.latestcomponentpractice.todo_app.adapters

import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.latestcomponentpractice.R
import com.example.latestcomponentpractice.todo_app.interfaces.ToDoItemClickListener
import com.example.latestcomponentpractice.todo_app.model.Person

class ToDoAdapter(private var personList: MutableList<Person>,
                  private val onLongClick : (Person) -> Boolean,
                  private val onClick : (Person) -> Unit) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(), View.OnCreateContextMenuListener{

    inner class ToDoViewHolder(view : View) : ViewHolder(view) {
        private var textViewOnePass : TextView
        private var textViewTwoPass : TextView
        private var cardView : CardView

        init {
            textViewOnePass = view.findViewById(R.id.textViewOnePass)
            textViewTwoPass = view.findViewById(R.id.textViewTwoPass)
            cardView = view.findViewById(R.id.cardView)
            cardView.setOnClickListener {
                onClick.invoke(personList[adapterPosition])
            }
            cardView.setOnLongClickListener {
                onLongClick.invoke(personList[adapterPosition])
            }
            cardView.setOnCreateContextMenuListener(this@ToDoAdapter)
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

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {

//        super.onCreateContextMenu(menu, v, menuInfo)
//        val inflater : MenuInflater = menuInflater
//        inflater.inflate(R.menu.floating_menu, menu)
    }
}