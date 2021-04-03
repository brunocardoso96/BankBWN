package dominando.android.bankbwn.network

import com.example.bankaccentur.data.model.UserResponse
import dominando.android.bankbwn.model.statement.StatementListResponse
import io.reactivex.Observable
import retrofit2.http.*

interface BankAPI {

    @GET("statements/{userId}")
    fun getStatement(@Path("userId") userId: Int) : Observable<StatementListResponse>

    @POST("login")
    @FormUrlEncoded
    fun postAutenticUser(
        @Field("user") user:String,
        @Field("password") pass:String) : Observable<UserResponse>
}