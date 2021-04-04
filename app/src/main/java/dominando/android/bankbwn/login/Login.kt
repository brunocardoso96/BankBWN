package dominando.android.bankbwn.login

import com.example.bankaccentur.data.model.UserResponse
import dominando.android.bankbwn.data.model.login.UserAccountResponse

interface Login {

    interface Presenter {
        fun getAutenticLogin(user: String, pass: String)
        fun stop()
    }

    interface View {
        fun displayError(message: String)
        fun getUser(user: UserResponse)
    }
}