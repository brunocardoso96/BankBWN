package dominando.android.bankbwn.data.model

import com.example.bankaccentur.data.model.UserResponse
import dominando.android.bankbwn.data.network.RetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteDataSourceLogin {

    fun autenticLogin(user: String, pass: String) : Observable<UserResponse> = RetrofitClient.serviceLogin
        .postAutenticUser(user, pass)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}