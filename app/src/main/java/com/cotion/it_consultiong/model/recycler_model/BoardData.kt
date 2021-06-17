package com.cotion.it_consultiong.model.recycler_model

import android.os.Parcelable
import android.widget.ImageView
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class BoardData(
    val contents: String ,
    val job: String,
    val name: String ,
    val title: String ,
    val day: String ,

) : Parcelable{
    constructor() : this("", "", "", "","")
}
