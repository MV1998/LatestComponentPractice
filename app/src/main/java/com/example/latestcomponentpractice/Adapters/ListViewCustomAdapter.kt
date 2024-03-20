package com.example.latestcomponentpractice.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.latestcomponentpractice.R
import kotlin.math.log

class ListViewCustomAdapter( val activity : Context, private val items : ArrayList<String>) : BaseAdapter() {

    val an = activity
    val collect = items

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): Any {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, p2: ViewGroup?): View {
        val view : View?
        val viewHolder : ViewHolder

        if (convertView == null) {
            Log.d(javaClass.simpleName, "getView: $position ${p2?.context.hashCode()}")
        //    val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = LayoutInflater.from(p2?.context).inflate(R.layout.list_view_custom_item, null)
            viewHolder = ViewHolder(view, activity)
            view?.tag = viewHolder
        }else {
            // reusing the view and viewHolder for particular row.
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val name = items[position]
        viewHolder.textViewOnePass?.text = name
        viewHolder.textViewTwoPass?.text = name
        viewHolder.textViewTwoPass?.tag = name

        return view!!
    }


    private class ViewHolder(row: View?, activity: Context) {
        var textViewOnePass : TextView? = null
        var textViewTwoPass : TextView? = null
        var linearLayout : LinearLayout? = null

        init {
            this.textViewOnePass = row?.findViewById(R.id.textViewOnePass)
            this.textViewTwoPass = row?.findViewById(R.id.textViewTwoPass)
            this.linearLayout = row?.findViewById(R.id.underTaker)
            row?.setOnClickListener {
                Toast.makeText(activity, textViewTwoPass?.tag as String, Toast.LENGTH_LONG).show()
            }
        }
    }

}