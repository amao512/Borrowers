package com.aslnstbk.borrowers.history.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.aslnstbk.borrowers.R
import com.aslnstbk.borrowers.common.data.Borrower
import com.aslnstbk.borrowers.common.data.BorrowerClickListener
import com.aslnstbk.borrowers.common.view.BorrowersAdapter
import com.aslnstbk.borrowers.common.view.InfoBottomSheetDialog
import com.aslnstbk.borrowers.history.presentation.viewModel.HistoryViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : AppCompatActivity(), BorrowerClickListener {

    private val historyViewModel: HistoryViewModel by viewModel()
    private val infoBottomSheetDialog: InfoBottomSheetDialog by inject()

    private val historyAdapter: BorrowersAdapter by lazy {
        BorrowersAdapter(
            borrowerClickListener = this
        )
    }

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        initViews()
        initListeners()
        observeLiveData()
    }

    override fun onBorrowerClick(borrower: Borrower) {
        infoBottomSheetDialog.show(
            context = this,
            borrower = borrower
        )
    }

    private fun initViews() {
        toolbar = findViewById(R.id.activity_history_main_toolbar)
        recyclerView = findViewById(R.id.activity_history_recycler_view)
        recyclerView.adapter = historyAdapter
    }

    private fun initListeners() {
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun observeLiveData() {
        historyViewModel.borrowersLiveData.observe(this, ::handleBorrowers)
    }

    private fun handleBorrowers(list: List<Borrower>) {
        historyAdapter.setList(getPaidBorrowerList(list))
    }

    private fun getPaidBorrowerList(list: List<Borrower>): List<Borrower> {
        val paidBorrowerList: MutableList<Borrower> = mutableListOf()
        list.map {
            if(it.isPaid){
                paidBorrowerList.add(it)
            }
        }

        return paidBorrowerList
    }
}