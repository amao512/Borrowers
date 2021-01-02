package com.aslnstbk.borrowers

import android.app.Application
import com.aslnstbk.borrowers.di.applicationModule
import com.aslnstbk.borrowers.history.di.historyModule
import com.aslnstbk.borrowers.main.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BorrowerApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BorrowerApp)

            modules(
                applicationModule,
                mainModule,
                historyModule
            )
        }
    }
}