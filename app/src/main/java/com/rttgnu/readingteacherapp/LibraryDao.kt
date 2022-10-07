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
//
    @Query ("SELECT * FROM library_table WHERE LibraryID=(:id)")      //id를 통하여 특정 데이터 가져오는 쿼리
    suspend fun getRecord(id : Long): LiveData<LibraryModel?>
//
    @Query("DELETE FROM library_table")
    suspend fun allDeleteLibrary()
}