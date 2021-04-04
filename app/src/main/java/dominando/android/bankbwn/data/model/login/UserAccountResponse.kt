package dominando.android.bankbwn.data.model.login

import com.example.bankaccentur.data.model.UserResponse
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserAccountResponse(
    val userAccount: UserResponse
)