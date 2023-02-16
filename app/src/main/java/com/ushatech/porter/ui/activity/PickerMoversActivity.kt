package com.ushatech.porter.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.ushatech.porter.databinding.ActivityPickerMoversBinding
import com.ushatech.porter.presentation.BaseActivity


class PickerMoversActivity : BaseActivity() {

    private lateinit var binding:ActivityPickerMoversBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickerMoversBinding.inflate(layoutInflater)
        initClicks()
        setContentView(binding.root)
    }

    private fun initClicks() {
        binding.ivClose.setOnClickListener {
            finish()

        }
        binding.btnChatWithUs.setOnClickListener {

            val phone = "+34666777888"
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)

        }
    }
}