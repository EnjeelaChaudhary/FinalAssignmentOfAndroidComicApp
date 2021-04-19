package com.example.riya.finalassignment.model

import android.os.Parcel
import android.os.Parcelable

data class CartModel(
        var ComicName:String?=null,
        var ComicImage:String?=null,
        var ComicPrice:String?=null,
        var ComicAvailable:String?=null,
        var ComicDesc:String?=null,
        var username:String?=null
) :Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(ComicName)
                parcel.writeString(ComicImage)
                parcel.writeString(ComicPrice)
                parcel.writeString(ComicAvailable)
                parcel.writeString(ComicDesc)
                parcel.writeString(username)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<CartModel> {
                override fun createFromParcel(parcel: Parcel): CartModel {
                        return CartModel(parcel)
                }

                override fun newArray(size: Int): Array<CartModel?> {
                        return arrayOfNulls(size)
                }
        }
}