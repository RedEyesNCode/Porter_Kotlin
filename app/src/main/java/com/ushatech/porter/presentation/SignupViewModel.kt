package com.ushatech.porter.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ushatech.porter.data.RegistrationResponse
import com.ushatech.porter.data.RequirementMetaResponse
import com.ushatech.porter.domain.MainRepository
import com.ushatech.porter.utils.Constant
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class SignupViewModel(): ViewModel() {
    private val _isFailed = MutableLiveData<String>()
    val isFailed: LiveData<String> = _isFailed
    private val _isLoading = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isLoading

    private val _commonResponse = MutableLiveData<RegistrationResponse>()
    val registerUserResponse:LiveData<RegistrationResponse> = _commonResponse
    val mainRepo = MainRepository()

    private val _requirementUiMeta = MutableLiveData<RequirementMetaResponse>()
    val requirementMetaResponse:LiveData<RequirementMetaResponse> = _requirementUiMeta


    fun getAppRequirements() = viewModelScope.launch {

        getAppRequirementsCoroutine()


    }

    private suspend fun getAppRequirementsCoroutine() {
        try {

            val response = mainRepo.getRequirementUiMeta()
            _isLoading.value = true
            response.enqueue(object : Callback<RequirementMetaResponse> {

                override fun onResponse(
                    call: Call<RequirementMetaResponse>,
                    response: Response<RequirementMetaResponse>
                ) {
                    _isLoading.value = false
                    if (response.code() == 200) {
                        // status 1 is success in this api.

                        if(response.body()?.status==1){
                            _requirementUiMeta.postValue(response.body())

                        }else{

                            _isFailed.value = "${Constant.OOPS_SW} ${response.code()}"


                        }

                    } else {
                        _isFailed.value = "${Constant.OOPS_SW} ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<RequirementMetaResponse>, t: Throwable) {



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


    fun signupUser(firstName:String,lastName:String,email:String,contactNo:String)= viewModelScope.launch {

        signupUserCoroutine(firstName, lastName, email, contactNo)


    }
    private suspend fun signupUserCoroutine(firstName:String,lastName:String,email:String,contactNo:String) {
        try {

            val response = mainRepo.signupUser(firstName, lastName, email, contactNo)
            _isLoading.value = true
            response.enqueue(object : Callback<RegistrationResponse> {

                override fun onResponse(
                    call: Call<RegistrationResponse>,
                    response: Response<RegistrationResponse>
                ) {
                    _isLoading.value = false
                    if (response.code() == 200) {
                        if(response.body()?.status==0){
                            _commonResponse.postValue(response.body())

                        }else{

                            _isFailed.value = "User Already exists please Login !"


                        }

                    } else {
                        _isFailed.value = "${Constant.OOPS_SW} ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {



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