package com.example.news.room.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "articles")
data class Article(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "itemId")
    val itemId: Int? = null,

    @Embedded
    @field:SerializedName("source")
    val source: Source? = null,

    @field:SerializedName("author")
    @ColumnInfo(name = "author")
    val author: String? = null,

    @field:SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String? = null,

    @field:SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String? = null,

    @field:SerializedName("url")
    @ColumnInfo(name = "url")
    val url: String? = null,

    @field:SerializedName("urlToImage")
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("publishedAt")
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String? = null,

    @field:SerializedName("content")
    @ColumnInfo(name = "content")
    val content: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readParcelable(Source::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(itemId)
        parcel.writeParcelable(source, flags)
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(publishedAt)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }
}

/*-----------------Source---------------*/
data class Source(

    @field:SerializedName("id")
    @ColumnInfo(name = "id")
    val id: String? = null,

    @field:SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Source> {
        override fun createFromParcel(parcel: Parcel): Source {
            return Source(parcel)
        }

        override fun newArray(size: Int): Array<Source?> {
            return arrayOfNulls(size)
        }
    }
}
