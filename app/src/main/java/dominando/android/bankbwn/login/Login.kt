package dominando.android.bankbwn.login

import com.example.bankaccentur.data.model.UserResponse

interface Login {

    interface Presenter {
        fun getAutenticLogin(user: String, pass: String)
        fun stop()
    }

    interface View {
        fun displayError(message: String)
        fun goToDashboard(user: UserResponse)
    }
}