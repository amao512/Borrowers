package com.aslnstbk.borrowers.main.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslnstbk.borrowers.common.data.models.Borrower
import com.aslnstbk.borrowers.common.domain.BorrowerRepository
import com.aslnstbk.borrowers.utils.CalendarParser
import kotlinx.coroutines.launch

class MainViewModel(
    private val borrowerRepository: BorrowerRepository,
    private val calendarParser: CalendarParser
) : ViewModel() {

    val borrowersLiveData: LiveData<List<Borrower>> = borrowerRepository.getAllBorrowers()

    fun addBorrower(
        fullName: String,
        debt: Int
    ) = viewModelScope.launch {
        val calendar = calendarParser.getCurrentTime().toString()
        val borrower = Borrower(
            id = 0,
            branchId = 1,
            fullName = fullName,
            debt = debt,
            date = calendar
        )

        borrowerRepository.addBorrower(borrower)
    }

    fun deleteBorrower(borrower: Borrower) = viewModelScope.launch {
        borrowerRepository.deleteBorrower(borrower)
    }

    fun approveBorrower(borrower: Borrower) = viewModelScope.launch {
        borrower.isPaid = true
        borrower.paidDate = calendarParser.getCurrentTime().toString()
        borrowerRepository.updateBorrower(borrower)
    }
}