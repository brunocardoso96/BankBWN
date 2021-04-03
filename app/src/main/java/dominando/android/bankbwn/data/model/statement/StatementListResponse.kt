package dominando.android.bankbwn.data.model.statement

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatementListResponse(
    val statementList: List<StatementResponse>
)