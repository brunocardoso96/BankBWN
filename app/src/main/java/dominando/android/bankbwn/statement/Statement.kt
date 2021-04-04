package dominando.android.bankbwn.statement

interface Statement {
    interface Presenter {
        fun getStatement(userId: Int)
        fun stop()
    }

    interface View {

    }
}