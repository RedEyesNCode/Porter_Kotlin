package com.ushatech.porter.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ushatech.porter.R
import com.ushatech.porter.databinding.BottomSheetAddMoneyBinding
import com.ushatech.porter.databinding.BottomSheetLogoutBinding
import com.ushatech.porter.databinding.FragmentPaymentBinding
import com.ushatech.porter.presentation.BaseFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PaymentFragment : BaseFragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding:FragmentPaymentBinding





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

        binding = FragmentPaymentBinding.inflate(layoutInflater)

        initClicks()
        // Inflate the layout for this fragment

        return binding.root

    }

    private fun initClicks() {
        binding.tvAddmoney.setOnClickListener {
            setupBottomSheetDialog()


        }


    }

    private fun setupBottomSheetDialog() {
        var bottomSheetBinding = BottomSheetAddMoneyBinding.inflate(layoutInflater)
        var bottomSheetDialog = BottomSheetDialog(fragmentContext,R.style.BottomSheetDialogTheme)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetBinding.btnProceed.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        bottomSheetBinding.tv500.setOnClickListener {

            bottomSheetDialog.dismiss()

        }
        bottomSheetBinding.tv1000.setOnClickListener {
            bottomSheetDialog.dismiss()

        }
        bottomSheetBinding.tv2000.setOnClickListener {

            bottomSheetDialog.dismiss()


        }

        bottomSheetDialog.show()






    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PaymentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PaymentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}