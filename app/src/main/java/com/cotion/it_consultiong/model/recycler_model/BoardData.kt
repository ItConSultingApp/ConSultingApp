package com.cotion.it_consultiong.model.recycler_model

import android.os.Parcelable
import android.widget.ImageView
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class BoardData(
    //  var class_number: String,
    val contents: String?=null,
    //  val grade_class: String?,
    //  val grade: String?,
    val job: String?=null,
    val name: String?=null,

    val title: String?=null,
    val When: String?=null,
) : Parcelable
