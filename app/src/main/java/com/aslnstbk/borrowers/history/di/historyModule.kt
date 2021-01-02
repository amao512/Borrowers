package com.aslnstbk.borrowers.history.di

import com.aslnstbk.borrowers.history.presentation.HistoryActivityRouter
import com.aslnstbk.borrowers.history.presentation.viewModel.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val historyModule = module {

    viewModel {
        HistoryViewModel(
            borrowerRepository = get()
        )
    }

    factory {
        HistoryActivityRouter()
    }
}