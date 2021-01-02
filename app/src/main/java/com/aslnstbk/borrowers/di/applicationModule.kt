package com.aslnstbk.borrowers.di

import com.aslnstbk.borrowers.common.data.AppDatabase
import com.aslnstbk.borrowers.common.data.DefaultBorrowerRepository
import com.aslnstbk.borrowers.common.domain.BorrowerRepository
import com.aslnstbk.borrowers.common.view.InfoBottomSheetDialog
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val applicationModule = module {

    single {
        AppDatabase.getInstance(
            context = androidContext()
        )
    }

    factory {
        DefaultBorrowerRepository(
            appDatabase = get()
        ) as BorrowerRepository
    }

    factory {
        InfoBottomSheetDialog(
            mainViewModel = get()
        )
    }
}