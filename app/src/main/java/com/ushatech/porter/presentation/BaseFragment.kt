package com.ushatech.porter.presentation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment() : Fragment() {


    lateinit var fragmentContext:Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.fragmentContext = context
    }
    public fun showToast(message:String){
        Toast.makeText(fragmentContext,message, Toast.LENGTH_SHORT).show()

    }

    public fun showLog(message:String){
        Log.i("DEV_ASHUTOSH", "showLog: $message")


    }


}