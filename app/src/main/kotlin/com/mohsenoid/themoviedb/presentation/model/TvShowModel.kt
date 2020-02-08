package com.mohsenoid.themoviedb.presentation.model

import android.os.Parcel
import android.os.Parcelable

data class TvShowModel(
    val name: String,
    val firstAirDate: String,
    val overview: String,
    val voteAverage: Double,
    val posterUrl: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        name = parcel.readString()!!,
        firstAirDate = parcel.readString()!!,
        overview = parcel.readString()!!,
        voteAverage = parcel.readDouble(),
        posterUrl = parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeString(firstAirDate)
        dest?.writeString(overview)
        dest?.writeDouble(voteAverage)
        dest?.writeString(posterUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TvShowModel> {
        override fun createFromParcel(parcel: Parcel): TvShowModel {
            return TvShowModel(parcel)
        }

        override fun newArray(size: Int): Array<TvShowModel?> {
            return arrayOfNulls(size)
        }
    }
}
