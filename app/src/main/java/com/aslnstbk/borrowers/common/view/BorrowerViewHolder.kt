package com.aslnstbk.borrowers.common.view

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aslnstbk.borrowers.R
import com.aslnstbk.borrowers.common.data.BorrowerClickListener
import com.aslnstbk.borrowers.common.data.Borrower

const val DEBT_TEXT_FORMAT = "- %s тг"
const val PAID_DEBT_TEXT_FORMAT = "+ %s тг"

class BorrowerViewHolder(
    itemView: View,
    private val borrowerClickListener: BorrowerClickListener
) : RecyclerView.ViewHolder(itemView) {

    private val borrowerNameTextView: TextView = itemView.findViewById(R.id.borrower_item_borrower_name)
    private val dateTextView: TextView = itemView.findViewById(R.id.borrower_item_date)
    private val debtTextView: TextView = itemView.findViewById(R.id.borrower_item_debt)

    fun onBind(borrower: Borrower){
        borrowerNameTextView.text = borrower.fullName
        dateTextView.text = borrower.date
        debtTextView.text = DEBT_TEXT_FORMAT.format(borrower.debt)

        if(borrower.isPaid){
            debtTextView.setTextColor(Color.GREEN)
            debtTextView.text = PAID_DEBT_TEXT_FORMAT.format(borrower.debt)
        }

        itemView.setOnClickListener {
            borrowerClickListener.onBorrowerClick(borrower = borrower)
        }
    }
}