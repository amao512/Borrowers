package com.aslnstbk.borrowers.common.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "borrower_table")
data class Borrower (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fullName: String,
    val debt: Int,
    val date: String,
    var paidDate: String? = null,
    var isPaid: Boolean = false
)