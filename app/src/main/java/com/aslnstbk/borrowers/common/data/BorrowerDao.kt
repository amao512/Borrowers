package com.aslnstbk.borrowers.common.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aslnstbk.borrowers.common.data.Borrower

@Dao
interface BorrowerDao {

    @Query("SELECT * FROM borrower_table")
    fun getAllBorrowers(): LiveData<List<Borrower>>

    @Query("SELECT * FROM borrower_table WHERE id = :id")
    fun getById(id: Int): Borrower

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(borrower: Borrower)

    @Update
    fun update(borrower: Borrower)

    @Delete
    fun delete(borrower: Borrower)
}