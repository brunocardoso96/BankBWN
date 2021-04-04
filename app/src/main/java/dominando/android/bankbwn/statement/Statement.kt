package dominando.android.bankbwn.statement

import dominando.android.bankbwn.data.model.statement.StatementResponse

interface Statement {
    interface Presenter {
        fun getStatement(userId: Int)
        fun stop()
    }

    interface View {
        fun displayStatement(statementList: List<StatementResponse>)
    }
}