package com.ushatech.porter.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.ushatech.porter.R
import com.ushatech.porter.databinding.ActivityTermsConditionsBinding
import com.ushatech.porter.presentation.BaseActivity


class TermsConditionsActivity : BaseActivity() {

    private lateinit var binding:ActivityTermsConditionsBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsConditionsBinding.inflate(layoutInflater)

        initClicks()

        setContentView(binding.root)
    }

    private fun initClicks() {
        binding.termsLayout.setOnClickListener {

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.TERMS)))
            startActivity(browserIntent)

        }
        binding.policyLayout.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.POLICY)))
            startActivity(browserIntent)

        }
        binding.CommonTitleBar.tvTitle.setText("Terms & Conditions")
        binding.CommonTitleBar.ivClose.setOnClickListener {
            finish()

        }

    }
}