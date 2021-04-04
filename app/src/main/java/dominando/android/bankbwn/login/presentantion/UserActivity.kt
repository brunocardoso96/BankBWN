package dominando.android.bankbwn.login.presentantion

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.bankaccentur.data.model.UserResponse
import dominando.android.bankbwn.R
import dominando.android.bankbwn.login.Login
import dominando.android.bankbwn.statement.presentation.BankActivity
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity(), Login.View {

    lateinit var buttonLogin: Button

    lateinit var presenter: UserPresenter

    private val TAG = "loginBank"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        init()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    private fun init() {
        findView()
        saveShared()
        setupPresenter()
        loginclickButton()
    }

    fun loginclickButton() {
        buttonLogin.setOnClickListener{
            presenter.getAutenticLogin(user = textViewUser.text.toString(), pass = textViewPass.text.toString())
        }
    }

    private fun setupPresenter() {
        presenter = UserPresenter(this)
    }

    fun findView() {
        buttonLogin = findViewById(R.id.buttonLogin)

    }

    private fun saveShared() {
        val userSave = PreferenceManager.getDefaultSharedPreferences(this@UserActivity).getString("MYLABEL", "brunowcnascimento@gmail.com")

        // pra não ficar colocando a senha sempre
        val passSave = PreferenceManager.getDefaultSharedPreferences(this@UserActivity).getString("MYPASS", "@Aa2")
        // pra não ficar colocando a senha sempre

        textViewUser.setText(userSave)
        textViewPass.setText(passSave)
    }

    override fun displayError(message: String) {
        showToast(message)
    }

    override fun getUser(user: UserResponse) {
        val intent = BankActivity.getStartIntent(this@UserActivity)
        buttonLogin.setOnClickListener {
            intent.putExtra("EXTRA_userId", user.userId.toString())
            intent.putExtra("EXTRA_name", user.name)
            intent.putExtra("EXTRA_bankAccount", user.agency).toString()
            intent.putExtra("EXTRA_agency", user.bankAccount)
            intent.putExtra("EXTRA_balance", user.balance.toString())
            this@UserActivity.startActivity(intent)
        }
    }

    private fun showToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        Log.i("BANK_BWN", message)
    }
}