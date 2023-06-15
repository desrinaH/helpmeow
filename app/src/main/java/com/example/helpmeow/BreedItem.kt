package com.example.helpmeow

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BreedItem(
    val imageResId: Int,
    val breed: String,
    val detailInfo: String,
    val price: String,
    val weight: String
) : Parcelable