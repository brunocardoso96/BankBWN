package dominando.android.bankbwn.statement.presentation

import android.util.Log
import dominando.android.bankbwn.data.model.statement.StatementListResponse
import dominando.android.bankbwn.data.model.statement.StatementResponse
import dominando.android.bankbwn.data.network.RetrofitClient
import dominando.android.bankbwn.statement.Statement
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BankPresenter(
        private val view: Statement.View
) : Statement.Presenter {
    private val TAG = "BANK_BWM"
    private val compositeDisposable = CompositeDisposable()


    override fun stop() {
        compositeDisposable.clear()
    }

    override fun getStatement(userId: Int) {
        RetrofitClient.serviceStatement.getStatement(userId).enqueue(object: Callback<StatementListResponse> {
            override fun onResponse(call: Call<StatementListResponse>, response: Response<StatementListResponse>) {
                if(response.isSuccessful) {
                    val lists: MutableList<StatementResponse> = mutableListOf()
                    response.body()?.let{statementListResponse ->
                        for(result in statementListResponse.statementList) {
                            val listaState = result.getStatementModel()
                            lists.add(listaState)
                        }
                        view.displayStatement(lists)
                    }
                }
            }
            override fun onFailure(call: Call<StatementListResponse>, t: Throwable) {
                Log.i(TAG, "Erro na API")
            }
        })

    }

}