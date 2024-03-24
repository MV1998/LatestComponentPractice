package com.example.latestcomponentpractice.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.latestcomponentpractice.R

class IntroductionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_introduction, container, false)
        view.apply {
            findViewById<TextView>(R.id.introductionTextViewId).text = position.toString()
        }
        return view;
    }

    companion object {
        var position : Int = -1
        fun newInstance(position : Int) : IntroductionFragment {
            this.position = position
            return IntroductionFragment()
        }
    }
}