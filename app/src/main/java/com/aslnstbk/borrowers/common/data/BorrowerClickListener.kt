package com.aslnstbk.borrowers.common.data

import com.aslnstbk.borrowers.common.data.models.Borrower

interface BorrowerClickListener {

    fun onBorrowerClick(borrower: Borrower) = Unit
}