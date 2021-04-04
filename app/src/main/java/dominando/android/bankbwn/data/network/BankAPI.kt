package dominando.android.bankbwn.data.network

import com.example.bankaccentur.data.model.UserResponse
import dominando.android.bankbwn.data.model.login.UserAccountResponse
import dominando.android.bankbwn.data.model.statement.StatementListResponse
import dominando.android.bankbwn.data.model.statement.StatementResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface BankAPI {

    @GET("statements/{userId}")
    fun getStatement(@Path("userId") userId: Int) : Call<StatementListResponse>

    @POST("login")
    @FormUrlEncoded
    fun postAutenticUser(
        @Field("user") user:String,
        @Field("password") pass:String) : Call<UserAccountResponse>
}