package com.example.bankaccentur.data.helper

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

object FormatBalance {
    fun format(balance: String): String {
        val formattedValue = contacValueText(balance).toInt()
        val numberFormat = DecimalFormat("#,##0.00", DecimalFormatSymbols(Locale("pt", "BR")))
        return numberFormat.format(formattedValue).toString()
    }

    private fun contacValueText(balance: String): String{
        var formattedValue: String = ""
        for(i in 0..balance.length-1) {
            var char = balance.substring(i, i+1)
            if ("." != char )  formattedValue += char
        }
        return formattedValue
    }
}