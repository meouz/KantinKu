package com.kantinku.data

import android.os.Parcel
import android.os.Parcelable

data class MenuData(
    val image: String,
    val name: String,
    val desc: String,
    val price: Int,
    val stock: Int,
    var quantity: Int,
    val category: Int,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }
    
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeString(desc)
        parcel.writeInt(price)
        parcel.writeInt(stock)
        parcel.writeInt(quantity)
        parcel.writeInt(category)
    }
    
    override fun describeContents(): Int {
        return 0
    }
    
    companion object CREATOR : Parcelable.Creator<MenuData> {
        override fun createFromParcel(parcel: Parcel): MenuData {
            return MenuData(parcel)
        }
        
        override fun newArray(size: Int): Array<MenuData?> {
            return arrayOfNulls(size)
        }
    }
}
