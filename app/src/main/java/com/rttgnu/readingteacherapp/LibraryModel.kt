package com.rttgnu.readingteacherapp

class LibraryModel (
    val LibraryID: Int,
    val LibraryName: String,
    val LibraryAuthor: String,
    val LibraryCover: String,
    val LibraryCategory: Int,
    val LibrarySeriesNum: Int,
    val LibraryDescription: String
) {
    constructor(): this(0,"","",
        "",0,0,"")
}