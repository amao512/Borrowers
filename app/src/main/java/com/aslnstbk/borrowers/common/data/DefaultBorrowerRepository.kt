package com.aslnstbk.borrowers.common.data

import androidx.lifecycle.LiveData
import com.aslnstbk.borrowers.common.domain.BorrowerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DefaultBorrowerRepository(
    private val appDatabase: AppDatabase
) : BorrowerRepository {

    override fun getAllBorrowers(): LiveData<List<Borrower>> = appDatabase.borrowerDao().getAllBorrowers()

    override fun addBorrower(borrower: Borrower) {
        CoroutineScope(Dispatchers.IO).launch {
            appDatabase.borrowerDao().insert(borrower)
        }
    }

    override fun deleteBorrower(borrower: Borrower) {
        CoroutineScope(Dispatchers.IO).launch {
            appDatabase.borrowerDao().delete(borrower)
        }
    }

    override fun updateBorrower(borrower: Borrower) {
        CoroutineScope(Dispatchers.IO).launch {
            appDatabase.borrowerDao().update(borrower)
        }
    }
}