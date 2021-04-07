package dominando.android.bankbwn.data.model.login

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserAccountResponse(
    val userAccount: UserResponse
)