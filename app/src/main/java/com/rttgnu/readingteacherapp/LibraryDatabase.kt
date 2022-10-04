package com.rttgnu.readingteacherapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LibraryModel::class], version = 1)
abstract class LibraryDatabase : RoomDatabase() {
    abstract fun libraryDao(): LibraryDao

    companion object{
        @Volatile //다른 thread에서 접근 가능하게 만드는 것입니다.
        private var INSTANCE: LibraryDatabase? = null

        fun getDatabase(context: Context): LibraryDatabase {
            synchronized(this) {
                var instanse : LibraryDatabase? = INSTANCE
                if (instanse == null) {
                    instanse = Room.databaseBuilder(
                        context,
                        LibraryDatabase::class.java,
                        "library-db")
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instanse
                }
                return instanse
            }
        }
    }
}