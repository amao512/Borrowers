package com.aslnstbk.borrowers.common.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Borrower::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun borrowerDao(): BorrowerDao

    companion object {
        fun getInstance(context: Context): AppDatabase = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "borrower-database"
        ).build()
    }
}