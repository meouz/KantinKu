package com.kantinku.data

import android.os.Parcel
import android.os.Parcelable

data class OrdersData(
    val orders: List<MenuData>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(MenuData) ?: listOf(),
    )
    
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(orders)
    }
    
    override fun describeContents(): Int {
        return 0
    }
    
    companion object CREATOR : Parcelable.Creator<OrdersData> {
        override fun createFromParcel(parcel: Parcel): OrdersData {
            return OrdersData(parcel)
        }
        
        override fun newArray(size: Int): Array<OrdersData?> {
            return arrayOfNulls(size)
        }
    }
}
