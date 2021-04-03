package dominando.android.bankbwn.data.model.statement

import com.squareup.moshi.JsonClass
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@JsonClass(generateAdapter = true)
data class StatementResponse(
    val title: String,
    val desc: String,
    val date: String,
    val value: String) {

    fun getStatementModel() =  StatementResponse(
        title = this.title,
        desc = this.desc,
        date = this.date,
        value = this.value)

    val createdDate : String
        get() {
            val locale = Locale("pt", "BR")
            return try {
                val date = SimpleDateFormat("yyyy-MM-dd", locale).parse(date?: "")
                SimpleDateFormat("dd/MM/yyyy", locale).format(date).capitalize()
            } catch (e: ParseException){
                ""
            }
        }

    val createdValue : String
        get() {
            val value = this.value.toDouble()
            val numberFormat = DecimalFormat("R$#,##0.00", DecimalFormatSymbols(Locale("pt", "BR")))
            return numberFormat.format(value).toString()
        }
}
