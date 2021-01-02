package com.aslnstbk.borrowers.common.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.aslnstbk.borrowers.R
import com.aslnstbk.borrowers.common.data.Borrower
import com.aslnstbk.borrowers.main.presentation.viewModel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

private const val DATE_TEXT_FORMAT = "Взял - %s"
private const val PAID_DATE_TEXT_FORMAT = "Вернул - %s"

class InfoBottomSheetDialog(
    private val mainViewModel: MainViewModel
) {

    private lateinit var bottomSheetDialog: BottomSheetDialog

    private lateinit var nameTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var paidTextView: TextView
    private lateinit var debtTextView: TextView
    private lateinit var deleteButton: Button
    private lateinit var approveButton: Button

    fun show(
        context: Context,
        borrower: Borrower
    ){
        bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)

        val view: View = LayoutInflater.from(context).inflate(R.layout.layout_info_bottom_sheet, null)
        initViews(
            context = context,
            view = view,
            borrower = borrower
        )
        initListeners(borrower = borrower)

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    private fun initViews(
        context: Context,
        view: View,
        borrower: Borrower
    ){
        nameTextView = view.findViewById(R.id.layout_info_bottom_sheet_name)
        dateTextView = view.findViewById(R.id.layout_info_bottom_sheet_date)
        paidTextView = view.findViewById(R.id.layout_info_bottom_sheet_paid_date)
        debtTextView = view.findViewById(R.id.layout_info_bottom_sheet_debt)
        deleteButton = view.findViewById(R.id.layout_add_bottom_sheet_button_cancel)
        approveButton = view.findViewById(R.id.layout_add_bottom_sheet_button_add)

        nameTextView.text = borrower.fullName
        dateTextView.text = DATE_TEXT_FORMAT.format(borrower.date)
        debtTextView.text = DEBT_TEXT_FORMAT.format(borrower.debt)

        if(borrower.isPaid){
            paidTextView.text = PAID_DATE_TEXT_FORMAT.format(borrower.paidDate)
            debtTextView.setTextColor(ContextCompat.getColor(context, R.color.green))
            debtTextView.text = PAID_DEBT_TEXT_FORMAT.format(borrower.debt)
            approveButton.visibility = View.GONE
        }
    }

    private fun initListeners(borrower: Borrower){
        deleteButton.setOnClickListener {
            mainViewModel.deleteBorrower(borrower)
            bottomSheetDialog.cancel()
        }

        approveButton.setOnClickListener {
            mainViewModel.approveBorrower(borrower)
            bottomSheetDialog.cancel()
        }
    }
}