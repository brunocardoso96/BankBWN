package dominando.android.bankbwn.statement.presentation

import android.util.Log
import dominando.android.bankbwn.data.model.RemoteDataSourceStatement
import dominando.android.bankbwn.data.model.statement.StatementResponse
import dominando.android.bankbwn.statement.Statement
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class BankPresenter(
        private val view: Statement.View,
        private val dataSource: RemoteDataSourceStatement
) : Statement.Presenter {
    private val TAG = "BANK_BWM"
    private val compositeDisposable = CompositeDisposable()

    //Retorno da API
    private val statementListObserver: DisposableObserver<List<StatementResponse>>
        get() = object : DisposableObserver<List<StatementResponse>>() {
            override fun onNext(statementList: List<StatementResponse>) {
                view.displayStatement(statementList)
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError")
            }

            override fun onComplete() {
                Log.i(TAG, "onComplete")
            }

        }

    override fun stop() {
        compositeDisposable.clear()
    }

    override fun getStatement(userId: Int) {
            val disposable = dataSource.statementList(userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn((AndroidSchedulers.mainThread()))
                    .subscribeWith(statementListObserver)

            compositeDisposable.add(disposable)
        }

}