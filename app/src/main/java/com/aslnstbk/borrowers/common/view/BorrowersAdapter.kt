package com.aslnstbk.borrowers.common.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aslnstbk.borrowers.R
import com.aslnstbk.borrowers.common.data.BorrowerClickListener
import com.aslnstbk.borrowers.common.data.Borrower

class BorrowersAdapter(
    private val borrowerClickListener: BorrowerClickListener
) : RecyclerView.Adapter<BorrowerViewHolder>() {

    private val borrowersList: MutableList<Borrower> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BorrowerViewHolder {
        return BorrowerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.borrower_item,
                parent,
                false
            ),
            borrowerClickListener
        )
    }

    override fun onBindViewHolder(holder: BorrowerViewHolder, position: Int) {
        holder.onBind(borrowersList[position])
    }

    override fun getItemCount(): Int = borrowersList.size

    fun setList(borrowersList: List<Borrower>) {
        this.borrowersList.clear()
        this.borrowersList.addAll(borrowersList)
        notifyDataSetChanged()
    }
}