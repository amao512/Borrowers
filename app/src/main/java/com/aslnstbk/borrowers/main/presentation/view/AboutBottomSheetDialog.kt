package com.aslnstbk.borrowers.main.presentation.view

import android.content.Context
import android.content.pm.PackageInfo
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.aslnstbk.borrowers.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.lang.Exception

private const val VERSION_TEXT_FORMAT = "Version %s"
private const val EMPTY_STRING = ""

class AboutBottomSheetDialog {

    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var versionTextView: TextView

    fun show(context: Context){
        bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
        val view: View = LayoutInflater.from(context).inflate(R.layout.layout_about_bottom_sheet, null)

        versionTextView = view.findViewById(R.id.layout_about_bottom_sheet_text_view_version)
        versionTextView.text = VERSION_TEXT_FORMAT.format(getAppVersion(context))

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    private fun getAppVersion(context: Context): String = try {
        val info: PackageInfo = context.packageManager.getPackageInfo(context.packageName, 0)

        info.versionName
    } catch (e: Exception){
        e.localizedMessage ?: EMPTY_STRING
    }
}