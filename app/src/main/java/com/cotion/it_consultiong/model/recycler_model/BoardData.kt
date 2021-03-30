package com.cotion.it_consultiong.model.recycler_model

import android.os.Parcelable
import android.widget.ImageButton
import com.xwray.groupie.Group
import com.xwray.groupie.GroupDataObserver
import com.xwray.groupie.Item
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BoardData(
    val name: String,
    val title: String,
    val context: String?,
    val profile: String,
    val day: String
) : Parcelable, Group {
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(position: Int): Item<*> {
        TODO("Not yet implemented")
    }

    override fun getPosition(item: Item<*>): Int {
        TODO("Not yet implemented")
    }

    override fun registerGroupDataObserver(groupDataObserver: GroupDataObserver) {
        TODO("Not yet implemented")
    }

    override fun unregisterGroupDataObserver(groupDataObserver: GroupDataObserver) {
        TODO("Not yet implemented")
    }
}