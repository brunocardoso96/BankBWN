package dominando.android.bankbwn.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BANK_BASE_URL = "https://bank-app-test.herokuapp.com/api/"

    val serviceLogin = Retrofit.Builder()
        .baseUrl(BANK_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(BankAPI::class.java)
}