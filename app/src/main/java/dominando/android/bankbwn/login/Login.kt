package dominando.android.bankbwn.login

import dominando.android.bankbwn.data.model.login.UserResponse

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