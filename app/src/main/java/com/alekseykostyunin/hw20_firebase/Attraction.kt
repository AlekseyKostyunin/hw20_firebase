package com.alekseykostyunin.hw20_firebase

import com.yandex.mapkit.geometry.Point

data class Attraction(
    val name: String,
    val description: String,
    val point: Point
)
