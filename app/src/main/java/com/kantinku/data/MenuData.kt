package com.kantinku.data

import android.os.Parcel
import android.os.Parcelable

data class MenuData(
    val name: String,
    val image: String,
    val description: String,
    val quality: Int,
    val price: Int,
    var quantity: Int,
    var notes: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }
    
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(description)
        parcel.writeInt(quality)
        parcel.writeInt(price)
        parcel.writeInt(quantity)
        parcel.writeString(notes)
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
