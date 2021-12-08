package com.lyndexter.androiddevelopment.data

import com.lyndexter.androiddevelopment.data.entity.UserResponse
import io.reactivex.Single
import retrofit2.http.GET

const val BASE_URL = "https://randomuser.me/api/"

interface RandomUserApi {

    @GET("?results=10")
    fun getUsers(): Single<UserResponse>
}
