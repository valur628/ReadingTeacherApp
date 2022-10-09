package com.rttgnu.readingteacherapp

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE


@Dao
interface LibraryDao {
    @Query("SELECT * FROM library_table")
    fun getAllData(): LiveData<List<LibraryModel>>

    @Insert(onConflict = REPLACE)
    suspend fun insertLibrary(libraryModel: LibraryModel)

    @Query("DELETE FROM library_table")
    suspend fun allDeleteLibrary()
}