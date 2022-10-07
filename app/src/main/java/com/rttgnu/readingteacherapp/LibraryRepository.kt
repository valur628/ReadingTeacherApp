package com.rttgnu.readingteacherapp

import androidx.lifecycle.LiveData

class LibraryRepository(private val libraryDao: LibraryDao) {
    val getAllData: LiveData<List<LibraryModel>>
        = libraryDao.getAllData()

    suspend fun insertLibrary(libraryModel: LibraryModel){
        libraryDao.insertLibrary(libraryModel)
    }

    //
    suspend fun getRecord(id: Long): LiveData<LibraryModel?>
        = libraryDao.getRecord(id)!!
    //
    suspend fun allDeleteLibrary(){
        libraryDao.allDeleteLibrary()
    }
}