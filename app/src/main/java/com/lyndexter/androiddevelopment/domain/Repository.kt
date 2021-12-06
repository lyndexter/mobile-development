package com.lyndexter.androiddevelopment.domain

import io.reactivex.Single

interface Repository<T> {
    fun getEntities(): Single<T>
}
