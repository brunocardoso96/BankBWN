package dominando.android.bankbwn.login.presentantion

import android.util.Log
import com.example.bankaccentur.data.model.UserResponse
import dominando.android.bankbwn.login.Login
import dominando.android.bankbwn.model.RemoteDataSourceLogin
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.regex.Pattern


class UserPresenter(
    private val view: Login.View,
    private val dataSource: RemoteDataSourceLogin
) : Login.Presenter{

    private val TAG = "HomePresenter"
    private val compositeDisposable = CompositeDisposable()

    //Retorno da API
    private val autenticUserObserver: DisposableObserver<UserResponse>
    get() = object : DisposableObserver<UserResponse>() {
        override fun onNext(t: UserResponse) {
            Log.i(TAG, "onNext")
            view.goToDashboard(t)
        }

        override fun onError(e: Throwable) {
            Log.i(TAG, "onError")
        }

        override fun onComplete() {

        }
    }

    override fun stop() {
        compositeDisposable.clear()
    }

    override fun getAutenticLogin(user: String, pass: String) {
        if(verifyUser(user = user, pass = pass)) {
            val disposable = dataSource.autenticLogin(user, pass)
                .subscribeOn(Schedulers.io())
                .observeOn((AndroidSchedulers.mainThread()))
                .subscribeWith(autenticUserObserver)

            compositeDisposable.add(disposable)
        } else {
            view.displayError("Erro ao acessa")
        }
    }

    fun verifyUser(user: String, pass: String): Boolean {
        val pass = autenticPassword(pass)
        val email= autenticEmail(user)
        val cpf= autenticCpf(user)

        when {
            !pass -> {
                view.displayError("Login/Password error Password")
                return false
            }
            cpf -> return true
            email -> return true
            else -> {
                view.displayError("Login/Password error - pass OK")
                return false
            }
        }
    }

    //Autentica que a senha esta OK/NOK
    private fun autenticPassword(pass: String): Boolean {
        if(!verifyPassword(pass)) return false
        return true
    }

    private fun autenticCpf(user: String): Boolean {
        if(!verifyCpf(user)) return false
        return true
    }

    private fun autenticEmail(user: String): Boolean {
        if(!verifyEmail(user)) return false
        return true
    }

    //Validação de Senhas

    fun verifyPassword(pass: String): Boolean {
        return PASSWORD_PATTERN.matcher(pass).matches()
    }

    fun verifyCpf(CPF: String): Boolean {
        if (CPF == "00000000000" || CPF == "11111111111" || CPF == "22222222222" ||
            CPF == "33333333333" || CPF == "44444444444" || CPF == "55555555555" ||
            CPF == "66666666666" || CPF == "77777777777" || CPF == "88888888888" ||
            CPF == "99999999999" || CPF.length != 11) return false

        val dig10: Char
        val dig11: Char
        var sm: Int
        var i: Int
        var r: Int
        var num: Int
        var peso: Int

        return try {
            sm = 0
            peso = 10
            i = 0

            while (i < 9) {
                num = (CPF[i].toInt() - 48)
                sm = sm + num * peso
                peso = peso - 1
                i++ }

            r = 11 - sm % 11
            dig10 = if (r == 10 || r == 11) '0' else (r + 48).toChar() // converte no respectivo caractere numerico

            sm = 0
            peso = 11
            i = 0
            while (i < 10) {
                num = (CPF[i].toInt() - 48)
                sm = sm + num * peso
                peso = peso - 1
                i++
            }
            r = 11 - sm % 11
            dig11 = if (r == 10 || r == 11) '0' else (r + 48).toChar()

            if (dig10 == CPF[9] && dig11 == CPF[10]) true else false
        } catch (erro: InputMismatchException) {
            false
        }
    }

    fun verifyEmail(email: String): Boolean {
        if(EMAIL_ADRESS.matcher(email).matches()) { return true }
        if(EMAIL_ADRESS_BR.matcher(email).matches()) { return true }
        return false
    }

    private val EMAIL_ADRESS = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})"
    )

    private val EMAIL_ADRESS_BR = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})" +
                "(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})"
    )

    private val PASSWORD_PATTERN = Pattern.compile(
//        "^" +  "(?=.*[0-9])" +
        "(?=.*[A-Z])" +
                "(?=.*[a-z0-9])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$)" +
                ".{3,}" +
                "$"
    )

}