package com.aslnstbk.borrowers.main.presentation.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.aslnstbk.borrowers.R
import com.aslnstbk.borrowers.main.presentation.viewModel.MainViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class AddBottomSheetDialog(
    private val mainViewModel: MainViewModel
) {

    private lateinit var bottomSheetDialog: BottomSheetDialog

    private lateinit var nameEditText: EditText
    private lateinit var debtEditText: EditText
    private lateinit var cancelButton: Button
    private lateinit var addButton: Button

    fun show(context: Context){
        bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
        val view: View = LayoutInflater.from(context).inflate(R.layout.layout_add_bottom_sheet, null)

        initViews(view = view)
        initListeners()


        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.behavior.skipCollapsed = true
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetDialog.show()
    }

    private fun initViews(view: View){
        nameEditText = view.findViewById(R.id.layout_add_bottom_sheet_edit_text_name)
        debtEditText = view.findViewById(R.id.layout_add_bottom_sheet_edit_text_debt)
        cancelButton = view.findViewById(R.id.layout_add_bottom_sheet_button_cancel)
        addButton = view.findViewById(R.id.layout_add_bottom_sheet_button_add)
    }

    private fun initListeners(){
        addButton.setOnClickListener {
            mainViewModel.addBorrower(
                fullName = nameEditText.text.toString(),
                debt =  debtEditText.text.toString().toInt()
            )
            bottomSheetDialog.cancel()
        }

        cancelButton.setOnClickListener {
            bottomSheetDialog.cancel()
        }
    }
}