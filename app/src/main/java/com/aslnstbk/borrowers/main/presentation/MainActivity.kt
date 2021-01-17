package com.aslnstbk.borrowers.main.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.aslnstbk.borrowers.R
import com.aslnstbk.borrowers.common.data.BorrowerClickListener
import com.aslnstbk.borrowers.common.data.models.Borrower
import com.aslnstbk.borrowers.common.view.BorrowersAdapter
import com.aslnstbk.borrowers.common.view.DEBT_TEXT_FORMAT
import com.aslnstbk.borrowers.common.view.InfoBottomSheetDialog
import com.aslnstbk.borrowers.history.presentation.HistoryActivityRouter
import com.aslnstbk.borrowers.main.presentation.view.AboutBottomSheetDialog
import com.aslnstbk.borrowers.main.presentation.view.AddBottomSheetDialog
import com.aslnstbk.borrowers.main.presentation.viewModel.MainViewModel
import com.aslnstbk.borrowers.utils.CalendarParser
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), BorrowerClickListener {

    private val mainViewModel: MainViewModel by viewModel()
    private val historyActivityRouter: HistoryActivityRouter by inject()
    private val addBottomSheetDialog: AddBottomSheetDialog by inject()
    private val aboutBottomSheetDialog: AboutBottomSheetDialog by inject()
    private val infoBottomSheetDialog: InfoBottomSheetDialog by inject()
    private val calendarParser: CalendarParser by inject()

    private val borrowersAdapter: BorrowersAdapter by lazy {
        BorrowersAdapter(
            borrowerClickListener = this,
            calendarParser = calendarParser
        )
    }

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var addFab: FloatingActionButton
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        toolbar = findViewById(R.id.activity_main_toolbar)
        recyclerView = findViewById(R.id.activity_main_recycler_view)
        addFab = findViewById(R.id.activity_main_fab_add)
        collapsingToolbarLayout = findViewById(R.id.activity_main_collapsing_toolbar)

        recyclerView.adapter = borrowersAdapter
    }

    private fun initListeners(){
        initToolbarItemListener()

        addFab.setOnClickListener {
            addBottomSheetDialog.show(context = this)
        }

        toolbar.setNavigationOnClickListener {
            aboutBottomSheetDialog.show(context = this)
        }
    }

    private fun initToolbarItemListener() {
        val historyItem: MenuItem = toolbar.menu.findItem(R.id.main_toolbar_menu_history)

        historyItem.setOnMenuItemClickListener {
            startActivity(historyActivityRouter.createIntent(context = this))

            true
        }
    }

    private fun observeLiveData() {
        mainViewModel.borrowersLiveData.observe(this, ::handleBorrowers)
    }

    private fun handleBorrowers(list: List<Borrower>) {
        val notPaidList: List<Borrower> = getNotPaidBorrowerList(list)

        setAllDebts(notPaidList)
        borrowersAdapter.setList(notPaidList)
    }

    private fun getNotPaidBorrowerList(list: List<Borrower>): List<Borrower> {
        val notPaidList: MutableList<Borrower> = mutableListOf()
        list.map {
            if(!it.isPaid){
                notPaidList.add(it)
            }
        }

        return notPaidList
    }

    private fun setAllDebts(list: List<Borrower>) {
        var allDebts = 0
        list.map {
            allDebts += it.debt
        }

        collapsingToolbarLayout.title = DEBT_TEXT_FORMAT.format(allDebts.toString())
    }
}