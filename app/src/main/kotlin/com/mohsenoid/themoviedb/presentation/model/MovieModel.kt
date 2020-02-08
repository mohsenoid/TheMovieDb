package com.mohsenoid.themoviedb.presentation.model

import android.os.Parcel
import android.os.Parcelable

data class MovieModel(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val voteAverage: Double,
    val posterUrl: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        id = parcel.readInt(),
        title = parcel.readString()!!,
        releaseDate = parcel.readString()!!,
        overview = parcel.readString()!!,
        voteAverage = parcel.readDouble(),
        posterUrl = parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id)
        dest?.writeString(title)
        dest?.writeString(releaseDate)
        dest?.writeString(overview)
        dest?.writeDouble(voteAverage)
        dest?.writeString(posterUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieModel> {
        override fun createFromParcel(parcel: Parcel): MovieModel {
            return MovieModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieModel?> {
            return arrayOfNulls(size)
        }
    }
}
