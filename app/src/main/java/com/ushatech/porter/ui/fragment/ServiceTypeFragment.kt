package com.ushatech.porter.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ushatech.porter.R
import com.ushatech.porter.databinding.FragmentServiceTypeActivityBinding
import com.ushatech.porter.presentation.BaseFragment
import com.ushatech.porter.ui.adapter.ShiftDateAdapter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ServiceTypeFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding:FragmentServiceTypeActivityBinding


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
        binding  = FragmentServiceTypeActivityBinding.inflate(layoutInflater)

        initClicks()
        setupRecyclerView()


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recvDateShift.adapter = ShiftDateAdapter(fragmentContext)
        binding.recvDateShift.layoutManager = LinearLayoutManager(fragmentContext,LinearLayoutManager.HORIZONTAL,false)


    }

    private fun initClicks() {



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ServiceTypeActivity.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ServiceTypeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}