package com.example.enjeela.finalassignment.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comicss (
        var ComicssName:String?=null,
        var ComicssImage:String?=null,
        var ComicssPrice:String?=null,
        var ComicssDesc:String?=null,
        var ComicssAvailable:String?=null,
        var ComicssRating:String?=null,
        var ComicssType:String?=null

):Parcelable {
    @PrimaryKey
    var pID:Int?=0
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
        parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ComicssName)
        parcel.writeString(ComicssImage)
        parcel.writeString(ComicssPrice)
        parcel.writeString(ComicssDesc)
        parcel.writeString(ComicssAvailable)
        parcel.writeString(ComicssRating)
        parcel.writeString(ComicssType)
        parcel.writeValue(pID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comicss> {
        override fun createFromParcel(parcel: Parcel): Comicss {
            return Comicss(parcel)
        }

        override fun newArray(size: Int): Array<Comicss?> {
            return arrayOfNulls(size)
        }
    }
}