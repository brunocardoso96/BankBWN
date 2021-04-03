package dominando.android.bankbwn.login.presentantion

import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bankaccentur.data.model.UserResponse
import dominando.android.bankbwn.R
import dominando.android.bankbwn.login.Login
import dominando.android.bankbwn.model.RemoteDataSourceLogin
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity(), Login.View {

    lateinit var buttonLogin: Button

    lateinit var userPresenter: UserPresenter

    private val TAG = "loginBank"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        init()
    }

    private fun init() {
        findView()
        saveShared()
        setupPresenter()
        clickButtonLogin()
    }

    private fun setupPresenter() {
        val dataSource = RemoteDataSourceLogin()
        userPresenter = UserPresenter(this, dataSource)
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


    private fun clickButtonLogin() {
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener {


        }
    }

    override fun displayError(message: String) {
        showToast(message)
    }

    override fun goToDashboard(user: UserResponse) {
//            val intent = BankMainActivity.getStartIntent(this@UserActivity)
                        intent.putExtra("EXTRA_userId", user.userId.toString())
                        intent.putExtra("EXTRA_userId", user.userId.toString())
                        intent.putExtra("EXTRA_name", user.name)
                        intent.putExtra("EXTRA_bankAccount", user.agency)
                        intent.putExtra("EXTRA_agency", user.bankAccount)
                        intent.putExtra("EXTRA_balance", user.balance.toString())
                    this@UserActivity.startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}





//        }
//    }

