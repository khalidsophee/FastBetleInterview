package com.myproject.fastbeetle.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.fastbeetle.data.EmailObject
import com.myproject.fastbeetle.data.Items
import com.myproject.fastbeetle.data.remote.NetworkState
import com.myproject.fastbeetle.data.remote.RetrofitService
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel @Inject constructor(private val apiService: RetrofitService) : ViewModel() {

    private val _loginState = MutableLiveData<NetworkState<List<Items>>>()
    val loginState: LiveData<NetworkState<List<Items>>> get() = _loginState

    fun login() {
        _loginState.value = NetworkState.Loading()
        viewModelScope.launch {
            try {
                val body = EmailObject("narendra@modi.com")
                val response = apiService.login(body)

                if (response.isSuccessful && !response.body()?.items.isNullOrEmpty()) {
                    val data = response.body()?.items
                    _loginState.value = NetworkState.Success(data)
                    Log.e("response", response.body()?.items?.first()?.firstName.toString())
                } else {
                    //_loginState.value = NetworkState.Failure("Something went wrong")
                    Log.e("response", "Something went wrong")
                }

            } catch (ex: Exception) {
                _loginState.value = NetworkState.Failure(ex.message)
            }
        }
    }
}