package com.example.bankaccentur.data.helper

object FormatAccont {
    fun format(account: String): String{
        val campo1 = account.substring(0,2)
        val campo2 = account.substring(2,8)
        val campo3 = account.substring(8,9)
        return "$campo1.$campo2-$campo3"
    }
}