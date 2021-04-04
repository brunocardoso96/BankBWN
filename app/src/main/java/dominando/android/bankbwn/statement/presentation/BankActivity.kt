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
import dominando.android.bankbwn.data.model.RemoteDataSourceLogin
import dominando.android.bankbwn.data.model.RemoteDataSourceStatement
import dominando.android.bankbwn.data.model.statement.StatementResponse
import dominando.android.bankbwn.statement.Statement
import dominando.android.bankbwn.statement.presentation.adapter.BankAdapter
import kotlinx.android.synthetic.main.activity_bank_main_activity.*

class BankActivity : AppCompatActivity(), Statement.View {

    lateinit var presenter: BankPresenter

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

    }

    private fun setupPresenter() {
        val dataSource = RemoteDataSourceStatement()
        presenter = BankPresenter(this, dataSource)
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

//    private fun initalizeViewModel() {
//        viewModel.bankLiveData.observe(this, Observer {
//            it?.let {statements ->
//                initalizeRecycler(statements)
//            }
//        })
        val userId = intent.getStringExtra("EXTRA_userId")
//        viewModel.getStatementLive(userId.toInt())
//    }

    private fun initalizeRecycler(list: List<StatementResponse>) {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewPayment)
        recyclerView.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@BankActivity, RecyclerView.VERTICAL, false)
            recyclerView.adapter = BankAdapter(list)
        }
    }

    private fun insertUserInfo() {
        textViewName.setText(intent.getStringExtra("EXTRA_name"))
        textViewAgency.setText(intent.getStringExtra("EXTRA_agency"))
        textViewBankAccount.setText(FormatAccont.format(intent.getStringExtra("EXTRA_bankAccount")))
        textViewBalance.setText(FormatBalance.format(intent.getStringExtra("EXTRA_balance")))
    }
}