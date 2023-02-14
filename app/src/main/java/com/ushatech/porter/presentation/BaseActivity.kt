package com.ushatech.porter.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity() :AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    public fun showToast(message:String){
        Toast.makeText(this@BaseActivity,message,Toast.LENGTH_SHORT).show()
        
    }
    
    public fun showLog(message:String){
        Log.i("DEV_ASHUTOSH", "showLog: $message")
        
        
    }


}