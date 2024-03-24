package com.example.latestcomponentpractice.Adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.latestcomponentpractice.fragments.IntroductionFragment
import com.example.latestcomponentpractice.fragments.ViewPagerFragment1
import com.example.latestcomponentpractice.fragments.ViewPagerFragment2
import com.example.latestcomponentpractice.fragments.ViewPagerFragment3

class ViewPagerFragmentAdapter(val fragment : FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return IntroductionFragment.newInstance(position)
//        when(position) {
//            0 ->
//                ViewPagerFragment1()
//            1 ->
//                ViewPagerFragment2()
//            2 ->
//                ViewPagerFragment3()
//
//        }
//        return ViewPagerFragment1()
    }


}