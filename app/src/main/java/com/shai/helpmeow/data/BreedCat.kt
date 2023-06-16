package com.shai.helpmeow.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BreedCat(
    val imageResId: Int,
    val breed: String,
    val detailInfo: String,
    val price: String,
    val weight: String
) : Parcelable