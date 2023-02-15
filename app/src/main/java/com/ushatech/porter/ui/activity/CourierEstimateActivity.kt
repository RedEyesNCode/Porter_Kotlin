package com.ushatech.porter.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.ushatech.porter.R
import com.ushatech.porter.databinding.ActivityCourierEstimateBinding
import com.ushatech.porter.presentation.BaseActivity

class CourierEstimateActivity : BaseActivity() {

    lateinit var binding:ActivityCourierEstimateBinding

    private val AUTOCOMPLETE_PICKUP_CODE = 77
    private val AUTOCOMPLETE_DROP_CODE = 99


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourierEstimateBinding.inflate(layoutInflater)

        initClicks()

        setContentView(binding.root)
    }

    private fun initClicks() {
        binding.edtPickupAdress.setOnClickListener {
            val fields = listOf(Place.Field.ID, Place.Field.NAME)
            // Start the autocomplete intent.
            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
                .setTypesFilter(listOf(TypeFilter.CITIES.toString()))
                .setCountries(listOf("IN"))
                .build(this@CourierEstimateActivity)
            startActivityForResult(intent, AUTOCOMPLETE_PICKUP_CODE)

        }

        binding.edtDropAddress.setOnClickListener {
            val fields = listOf(Place.Field.ID, Place.Field.NAME)
            // Start the autocomplete intent.
            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
                .setTypesFilter(listOf(TypeFilter.CITIES.toString()))
                .setCountries(listOf("IN"))
                .build(this@CourierEstimateActivity)
            startActivityForResult(intent, AUTOCOMPLETE_DROP_CODE)
        }

        binding.btnEstimatePickup.setOnClickListener {
            if(isValidated()){


            }


        }

    }
    fun isValidated():Boolean{
        if(binding.edtPickupAdress.text.toString().isEmpty()){
            showToast("Please select pickup address")
            return false

        }else if(binding.edtDropAddress.text.toString().isEmpty()){
            showToast("Please select drop address")
            return false
        }else if(binding.edtPincode.text.toString().isEmpty()){
            showToast("Please enter pincode.")
            return false
        }else if(binding.edtItemWeight.text.toString().isEmpty()){
            showToast("Please enter item weight.")
            return false
        }else {
            return true
        }



    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_PICKUP_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        val place = Autocomplete.getPlaceFromIntent(data)
                        binding.edtPickupAdress.setText("$place.name")

                        Log.i("DEV_ASHUTOSH-->", "Place: ${place.name}, ${place.id}")
                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    // TODO: Handle the error.
                    data?.let {
                        val status = Autocomplete.getStatusFromIntent(data)
                        binding.edtPickupAdress.setText("")

                        Log.i("DEV_ASHUTOSH-->", status.statusMessage ?: "")
                    }
                }
                Activity.RESULT_CANCELED -> {
                    binding.edtPickupAdress.setText("")

                    showLog("No location picked by user from Google Auto Suggestions.")
                }
            }
            return
        }else if(requestCode==AUTOCOMPLETE_DROP_CODE){
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        val place = Autocomplete.getPlaceFromIntent(data)
                        binding.edtDropAddress.setText("$place.name")

                        Log.i("DEV_ASHUTOSH-->", "Place: ${place.name}, ${place.id}")
                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    // TODO: Handle the error.
                    data?.let {
                        val status = Autocomplete.getStatusFromIntent(data)
                        binding.edtDropAddress.setText("")

                        Log.i("DEV_ASHUTOSH-->", status.statusMessage ?: "")
                    }
                }
                Activity.RESULT_CANCELED -> {
                    binding.edtDropAddress.setText("")

                    showLog("No location picked by user from Google Auto Suggestions.")
                }
            }
            return



        }


        super.onActivityResult(requestCode, resultCode, data)
    }


}