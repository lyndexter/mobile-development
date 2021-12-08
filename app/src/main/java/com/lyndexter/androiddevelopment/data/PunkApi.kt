package com.lyndexter.androiddevelopment.data

import com.lyndexter.androiddevelopment.data.entities.Response
import io.reactivex.Single
import retrofit2.http.GET

const val BASE_URL_PUNK = "https://api.punkapi.com/v2/beers/"

interface PunkApi {

    @GET("?ids=1|2|3|4|5|6|7|8|9|10|11|12|13")
    fun getBeers(): Single<Response>
}
