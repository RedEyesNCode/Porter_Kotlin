package com.ushatech.porter.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.ushatech.porter.R
import com.ushatech.porter.databinding.FragmentItemStepBinding
import com.ushatech.porter.presentation.BaseFragment
import com.ushatech.porter.ui.adapter.ItemCartPager


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ItemStepFragment : BaseFragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding:FragmentItemStepBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentItemStepBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        setupTabLayout()

        return binding.root
    }

    private fun setupTabLayout() {
        val addProductTab = binding.tabLayout.newTab()
        addProductTab.text = "Furniture(0)"
        binding.tabLayout.addTab(addProductTab)
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Appliances(0)"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Cartons(0)"))
        addProductTab.select()

        val viewPagerAdapter = ItemCartPager(requireActivity())
        binding.tabViewpager.setAdapter(viewPagerAdapter)
        TabLayoutMediator(binding.tabLayout, binding.tabViewpager) { tab, position ->
            if(position==0){
                tab.setText("Furniture(0)")
            }else if(position==1){
                tab.setText("Appliances(0)")
            }else if(position==2){
                tab.setText("Cartons(0)")
            }

        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemStepFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}