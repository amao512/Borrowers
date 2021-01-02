package com.aslnstbk.borrowers.main.di

import com.aslnstbk.borrowers.main.presentation.view.AboutBottomSheetDialog
import com.aslnstbk.borrowers.main.presentation.view.AddBottomSheetDialog
import com.aslnstbk.borrowers.main.presentation.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        MainViewModel(
            borrowerRepository = get()
        )
    }

    factory {
        AddBottomSheetDialog(
            mainViewModel = get()
        )
    }

    single {
        AboutBottomSheetDialog()
    }
}