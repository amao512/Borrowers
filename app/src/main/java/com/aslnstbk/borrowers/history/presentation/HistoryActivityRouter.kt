package com.aslnstbk.borrowers.history.presentation

import android.content.Context
import android.content.Intent

class HistoryActivityRouter {

    fun createIntent(
        context: Context
    ) = Intent(context, HistoryActivity::class.java)
}