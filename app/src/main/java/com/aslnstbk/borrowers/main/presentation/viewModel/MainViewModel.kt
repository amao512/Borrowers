package com.aslnstbk.borrowers.main.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslnstbk.borrowers.common.data.Borrower
import com.aslnstbk.borrowers.common.domain.BorrowerRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

const val SIMPLE_DATE_FORMAT = "dd/M/yyyy hh:mm:ss"

class MainViewModel(
    private val borrowerRepository: BorrowerRepository
) : ViewModel() {

    val borrowersLiveData: LiveData<List<Borrower>> = borrowerRepository.getAllBorrowers()

    fun addBorrower(
        fullName: String,
        debt: Int
    ) = viewModelScope.launch {
        val sdf = SimpleDateFormat(SIMPLE_DATE_FORMAT)
        val currentDate = sdf.format(Date())
        val borrower = Borrower(id = 0, fullName = fullName, debt = debt, date = currentDate)

        borrowerRepository.addBorrower(borrower)
    }

    fun deleteBorrower(borrower: Borrower) = viewModelScope.launch {
        borrowerRepository.deleteBorrower(borrower)
    }

    fun approveBorrower(borrower: Borrower) = viewModelScope.launch {
        val sdf = SimpleDateFormat(SIMPLE_DATE_FORMAT)
        val currentDate = sdf.format(Date())

        borrower.isPaid = true
        borrower.paidDate = currentDate
        borrowerRepository.updateBorrower(borrower)
    }
}