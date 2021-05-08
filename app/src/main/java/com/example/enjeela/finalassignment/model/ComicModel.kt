package com.example.enjeela.finalassignment.model


import android.os.Parcel
import android.os.Parcelable

data class ComicModel(
        val vegImg:String?=null,
        val vegName:String?=null,
        val price:String?=null,
        val value:String?=null
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(vegImg)
        parcel.writeString(vegName)
        parcel.writeValue(price)
        parcel.writeValue(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ComicModel> {
        override fun createFromParcel(parcel: Parcel): ComicModel {
            return ComicModel(parcel)
        }

        override fun newArray(size: Int): Array<ComicModel?> {
            return arrayOfNulls(size)
        }
    }
}
