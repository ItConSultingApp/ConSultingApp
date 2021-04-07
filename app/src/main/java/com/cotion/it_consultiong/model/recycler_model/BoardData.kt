package com.cotion.it_consultiong.model.recycler_model

import android.os.Parcelable
import android.widget.ImageView
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class BoardData(
    val contents: String? = null,
    val job: String? = null,
    val name: String? = null,
    val title: String? = null,
    val day: String? = null,
) : Parcelable
