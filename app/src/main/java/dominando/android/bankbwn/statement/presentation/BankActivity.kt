package dominando.android.bankbwn.statement.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankaccentur.data.helper.FormatAccont
import com.example.bankaccentur.data.helper.FormatBalance
import dominando.android.bankbwn.R
import dominando.android.bankbwn.data.model.statement.StatementResponse
import dominando.android.bankbwn.statement.Statement
import dominando.android.bankbwn.statement.presentation.adapter.BankAdapter
import kotlinx.android.synthetic.main.activity_bank_main_activity.*

class BankActivity : AppCompatActivity(), Statement.View {

    lateinit var presenter: BankPresenter

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_main_activity)

        init()
    }

    companion object {
        fun getStartIntent(context: Context): Intent { return Intent(context, BankActivity::class.java) }
    }

    private fun init() {
        insertUserInfo()
        setupPresenter()
        findView()
        initStatementList()
    }

    fun findView() {
        recyclerView = findViewById(R.id.recyclerViewPayment)
    }

    private fun setupPresenter() {
        presenter = BankPresenter(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()

    }

    private fun initStatementList() {
        val userId = intent.getStringExtra("EXTRA_userId")!!.toInt()
        presenter.getStatement(userId)
    }

    private fun initRecycler(list: List<StatementResponse>) {
        recyclerView.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@BankActivity, RecyclerView.VERTICAL, false)
            recyclerView.adapter = BankAdapter(list)
        }
    }

    private fun insertUserInfo() {
        textViewName.setText(intent.getStringExtra("EXTRA_name"))
        textViewAgency.setText(intent.getStringExtra("EXTRA_agency"))
        textViewBankAccount.setText(FormatAccont.format(intent.getStringExtra("EXTRA_bankAccount").toString()))
        textViewBalance.setText(FormatBalance.format(intent.getStringExtra("EXTRA_balance").toString()))
    }

    override fun displayStatement(statementList: List<StatementResponse>) {
        initRecycler(statementList)
    }
}