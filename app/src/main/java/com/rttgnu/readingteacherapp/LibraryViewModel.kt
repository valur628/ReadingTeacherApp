package com.rttgnu.readingteacherapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LibraryViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<LibraryModel>>
    private val repository: LibraryRepository

    init {
        val libraryDao = LibraryDatabase.getDatabase(application).libraryDao()
        repository = LibraryRepository(libraryDao) //initialize repository
        readAllData = repository.getAllData
    }

    fun allDeleteLibrary(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.allDeleteLibrary()
        }
    }

    fun insertLibrary(libraryModel: LibraryModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertLibrary(libraryModel)
        }
    }
}