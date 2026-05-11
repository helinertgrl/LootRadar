package com.example.lootradar.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "games_table")
data class GameEntity(
    @PrimaryKey
    val id: Int,

    val title: String,

    @SerializedName("platforms")
    val platform: String,

    @SerializedName("worth")
    val worth: String? = "N/A",

    val users: Int? = 0,

    @ColumnInfo(name = "is_saved")
    val isSaved: Boolean = false,

    val thumbnail: String,

    @SerializedName("end_date")
    @ColumnInfo(name = "end_date")
    val endDate: String? = "N/A",

    @SerializedName("type")
    val type: String? = "Unknown"
)