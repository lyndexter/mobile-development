package com.lyndexter.androiddevelopment.data

import com.lyndexter.androiddevelopment.data.entity.UserResponse
import com.lyndexter.androiddevelopment.domain.Repository
import io.reactivex.Single

class UserRepository(
    private val api: RandomUserApi
) : Repository<UserResponse> {

    override fun getEntities(): Single<UserResponse> {
        return api.getUsers()
    }
}
