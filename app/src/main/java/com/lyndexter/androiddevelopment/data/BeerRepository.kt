package com.lyndexter.androiddevelopment.data

import com.lyndexter.androiddevelopment.data.entities.Response
import com.lyndexter.androiddevelopment.domain.Repository
import io.reactivex.Single

class BeerRepository(
    private val api: PunkApi
) : Repository<Response> {

    override fun getEntities(): Single<Response> {
        return api.getBeers()
    }
}
