package com.example.enjeela.finalassignment.model


import android.os.Parcel
import android.os.Parcelable

data class availablecomicModel(
        val Img:String? =null,
        val Name:String? = null,
        val price:String? = null,
        val value:String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Img)
        parcel.writeString(Name)
        parcel.writeValue(price)
        parcel.writeValue(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<availablecomicModel> {
        override fun createFromParcel(parcel: Parcel): availablecomicModel {
            return availablecomicModel(parcel)
        }

        override fun newArray(size: Int): Array<availablecomicModel?> {
            return arrayOfNulls(size)
        }
    }

}