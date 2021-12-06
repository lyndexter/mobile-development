package com.lyndexter.androiddevelopment.domain

import com.lyndexter.androiddevelopment.data.entity.UserResponse
import io.reactivex.Single

interface Repository {
    fun getUsers(): Single<UserResponse>
}