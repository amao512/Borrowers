package com.aslnstbk.borrowers.common.domain

import androidx.lifecycle.LiveData
import com.aslnstbk.borrowers.common.data.Borrower

interface BorrowerRepository {

    fun getAllBorrowers(): LiveData<List<Borrower>>

    fun addBorrower(borrower: Borrower)

    fun deleteBorrower(borrower: Borrower)

    fun updateBorrower(borrower: Borrower)
}