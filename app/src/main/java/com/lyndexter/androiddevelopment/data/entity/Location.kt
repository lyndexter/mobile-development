package com.lyndexter.androiddevelopment.data.entity

data class Location(
    val city: String,
    val coordinates: Coordinates,
    val postcode: String,
    val state: String,
    val timezone: Timezone
)
