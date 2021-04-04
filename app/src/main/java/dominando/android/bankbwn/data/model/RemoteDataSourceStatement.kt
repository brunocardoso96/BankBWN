package dominando.android.bankbwn.data.model

import dominando.android.bankbwn.data.model.statement.StatementResponse
import dominando.android.bankbwn.data.network.RetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteDataSourceStatement {

    fun statementList(userId: Int) : Observable<List<StatementResponse>> = RetrofitClient.serviceStatement
        .getStatement(userId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}