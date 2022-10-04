package com.rttgnu.readingteacherapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "library_table")
data class LibraryModel (
    @PrimaryKey val LibraryID: Int,
    val LibraryName: String,
    val LibraryAuthor: String,
    val LibraryCover: Int,
    val LibraryCategory: Int,
    val LibrarySeriesNum: Int,
    val LibraryDescription: String
)