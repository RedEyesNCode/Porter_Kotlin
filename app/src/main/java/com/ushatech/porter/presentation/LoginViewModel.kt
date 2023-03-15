package com.ushatech.porter.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ushatech.porter.data.CommonResponse
import com.ushatech.porter.data.LoginUserResponse
import com.ushatech.porter.domain.MainRepository
import com.ushatech.porter.utils.Constant
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class LoginViewModel(): ViewModel() {
    private val _isFailed = MutableLiveData<String>()
    val isFailed: LiveData<String> = _isFailed
    private val _isLoading = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isLoading

    private val _commonResponse = MutableLiveData<CommonResponse>()
    val commonResponse: LiveData<CommonResponse> = _commonResponse

    private val _loginUserResponse = MutableLiveData<LoginUserResponse>()
    val loginUserResponse:LiveData<LoginUserResponse> = _loginUserResponse

    val mainRepo = MainRepository()


    fun loginUser(mobileNumber: String,otp:String) = viewModelScope.launch {

        loginUserOtpCoroutine(mobileNumber,otp)

    }

    private suspend fun loginUserOtpCoroutine(mobileNumber: String, otp: String) {
        try {

            val response = mainRepo.loginUser(mobileNumber,otp)
            _isLoading.value = true
            response.enqueue(object : Callback<LoginUserResponse> {

                override fun onResponse(
                    call: Call<LoginUserResponse>,
                    response: Response<LoginUserResponse>
                ) {
                    _isLoading.value = false
                    if (response.code() == 200) {
                        _loginUserResponse.postValue(response.body())
                    } else {
                        _isFailed.value = "${Constant.OOPS_SW} ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<LoginUserResponse>, t: Throwable) {
                    _isFailed.value = t.message
                }
            })
        } catch (t: Throwable) {

            when (t) {
                is IOException -> {
                    _isFailed.value = "IO Exception"
                }
                else -> {
                    _isFailed.value = "Exception." + t.message

                    Log.i("RETROFIT", t.message!!)
                }


            }

        }





    }


    fun checkUserLogin(mobileNumber:String) = viewModelScope.launch {


        checkUserLoginCoroutine(mobileNumber)


    }

    private suspend fun checkUserLoginCoroutine(mobileNumber: String) {
        try {

            val response = mainRepo.checkUserLogin(mobileNumber)
            _isLoading.value = true
            response.enqueue(object : Callback<CommonResponse> {

                override fun onResponse(
                    call: Call<CommonResponse>,
                    response: Response<CommonResponse>
                ) {
                    _isLoading.value = false
                    if (response.code() == 200) {
                        _commonResponse.postValue(response.body())
                    } else {
                        _isFailed.value = "${Constant.OOPS_SW} ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                    _isFailed.value = t.message
                }
            })
        } catch (t: Throwable) {

            when (t) {
                is IOException -> {
                    _isFailed.value = "IO Exception"
                }
                else -> {
                    _isFailed.value = "Exception." + t.message

                    Log.i("RETROFIT", t.message!!)
                }


            }

        }

    }

}