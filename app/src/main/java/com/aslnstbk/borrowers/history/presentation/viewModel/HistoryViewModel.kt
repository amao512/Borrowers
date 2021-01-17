package com.aslnstbk.borrowers.history.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aslnstbk.borrowers.common.data.models.Borrower
import com.aslnstbk.borrowers.common.domain.BorrowerRepository

class HistoryViewModel(
    borrowerRepository: BorrowerRepository
) : ViewModel() {

    val borrowersLiveData: LiveData<List<Borrower>> = borrowerRepository.getAllBorrowers()
}