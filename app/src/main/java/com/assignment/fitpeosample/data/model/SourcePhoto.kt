package com.assignment.fitpeosample.data.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class SourcePhoto(
    val albumID: Long?,
    val id: Long?,
    val title: String?,
    val url: String?,
    val thumbnailURL: String?):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(albumID)
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(thumbnailURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SourcePhoto> {
        override fun createFromParcel(parcel: Parcel): SourcePhoto {
            return SourcePhoto(parcel)
        }

        override fun newArray(size: Int): Array<SourcePhoto?> {
            return arrayOfNulls(size)
        }
    }

}