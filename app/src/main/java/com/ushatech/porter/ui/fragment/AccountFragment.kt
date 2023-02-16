package com.ushatech.porter.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ushatech.porter.R
import com.ushatech.porter.databinding.BottomSheetLogoutBinding
import com.ushatech.porter.databinding.FragmentAccountBinding
import com.ushatech.porter.presentation.BaseFragment
import com.ushatech.porter.ui.activity.TermsConditionsActivity


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AccountFragment : BaseFragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding:FragmentAccountBinding




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
        binding = FragmentAccountBinding.inflate(layoutInflater)
        initClicks()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initClicks() {

        binding.tvlogout.setOnClickListener {
            // show the bottom sheet dialog.
            setupBottomSheetDialog()
        }
        binding.tvLegal.setOnClickListener {

            var termsIntent = Intent(fragmentContext,TermsConditionsActivity::class.java)
            fragmentContext.startActivity(termsIntent)


        }



    }

    private fun setupBottomSheetDialog() {
        var bottomSheetBinding = BottomSheetLogoutBinding.inflate(layoutInflater)
        var bottomSheetDialog = BottomSheetDialog(fragmentContext,R.style.BottomSheetDialogTheme)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        bottomSheetBinding.btnCancel.setOnClickListener {

            bottomSheetDialog.dismiss()
        }
        bottomSheetBinding.btnLogout.setOnClickListener {
            activity?.finish()
            showToast("Logout successfully ! ")


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
         * @return A new instance of fragment AccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}