package hu.ait.andwallet

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_summary.*
import kotlin.math.exp

class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        var income = intent.getFloatExtra(MainActivity.KEY_INCOME, 0f)
        var expenses = intent.getFloatExtra(MainActivity.KEY_EXPENSES, 0f)

        var balance = income - expenses

        tvIncomeAmt.text = income.toString()
        tvExpensesAmt.text = expenses.toString()
        tvBalanceAmt.text = balance.toString()

    }
}