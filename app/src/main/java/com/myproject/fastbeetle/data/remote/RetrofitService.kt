package com.myproject.fastbeetle.data.remote

import com.myproject.fastbeetle.data.EmailObject
import com.myproject.fastbeetle.data.Items
import com.myproject.fastbeetle.data.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface RetrofitService {
    @POST("list")
    suspend fun login(
         @Body body: EmailObject
    ): Response<User>
}


