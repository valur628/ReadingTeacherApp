package com.rttgnu.readingteacherapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "library_table")
data class LibraryModel (
    @PrimaryKey val LibraryID: Long,
    val LibraryName: String,
    val LibraryAuthor: String,
    val LibraryCover: Int,
    val LibraryCategory: Long,
    val LibrarySeriesNum: Long,
    val LibraryDescription: String
)