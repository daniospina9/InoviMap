package com.example.inovimap.navigation.map

import kotlinx.serialization.Serializable

@Serializable
data class MapRoute(
    val latitude: Double,
    val longitude: Double
)