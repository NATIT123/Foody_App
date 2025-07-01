package com.example.foodyapplication.data.modelJson.payment

data class Payment(
    val address: String,
    val audiences: String,
    val coordinateId: String,
    val cuisinesId: String,
    val districtId: String,
    val image: String,
    val locationRate: Double,
    val name: String,
    val numberView: Int,
    val ownerId: String,
    val priceRange: String,
    val priceRate: Double,
    val qualityRate: Double,
    val serviceRate: Double,
    val spaceRate: Double,
    val status: String,
    val subCategoryId: String,
    val timeOpen: String
)