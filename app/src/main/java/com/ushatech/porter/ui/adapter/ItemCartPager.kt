package com.ushatech.porter.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ushatech.porter.ui.fragment.ItemCartFragment

class ItemCartPager:FragmentStateAdapter {

    constructor(fragmentActivity: FragmentActivity):super(fragmentActivity)


    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        // Manage for furniture, gadgets in one place.
        return ItemCartFragment()
    }
}