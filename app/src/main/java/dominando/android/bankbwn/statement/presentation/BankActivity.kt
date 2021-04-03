package dominando.android.bankbwn.statement.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dominando.android.bankbwn.R
import kotlinx.android.synthetic.main.activity_bank_main_activity.*

class BankActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_main_activity)

    }

    companion object {
        fun getStartIntent(context: Context): Intent { return Intent(context, BankActivity::class.java) }
    }

    private fun initalizer() {
//        insertUserInfo()
//        initalizeViewModel()
    }

//    private fun initalizeViewModel() {
//        val viewModel: BankViewModel = ViewModelProviders.of(this).get(BankViewModel::class.java)
//        viewModel.bankLiveData.observe(this, Observer {
//            it?.let {statements ->
//                initalizeRecycler(statements)
//            }
//        })
//        val userId = intent.getStringExtra("EXTRA_userId")
//        viewModel.getStatementLive(userId.toInt())
//    }

//    private fun initalizeRecycler(list: List<StatementResponse>) {
//        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewPayment)
//        recyclerView.apply {
//            recyclerView.layoutManager = LinearLayoutManager(this@BankMainActivity, RecyclerView.VERTICAL, false)
//            recyclerView.adapter = BankAdapter(list)
//        }
//    }

//    private fun insertUserInfo() {
//        textViewName.setText(intent.getStringExtra("EXTRA_name"))
//        textViewAgency.setText(intent.getStringExtra("EXTRA_agency"))
//        textViewBankAccount.setText(FormatAccont.format(intent.getStringExtra("EXTRA_bankAccount")))
//        textViewBalance.setText(FormatBalance.format(intent.getStringExtra("EXTRA_balance")))
//    }

}