package hu.ait.andwallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_row.view.*
import kotlin.text.toFloat

data class item(var type: Int, var amount: Float)

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_INCOME = "KEY_INCOME"
        const val KEY_EXPENSES = "KEY_EXPENSES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener{
            checkEmpty()
        }

        btnType.setOnClickListener {
            changeExpenseType()
        }

        btnDeleteAll.setOnClickListener {
            deleteAllItems()
        }

        btnSummary.setOnClickListener {
            viewSummary()
        }
    }

    var itemType = 0 // 0 for income, 1 for expense
    var income = 0f
    var expenses = 0f

    fun viewSummary() {
        var intentDetails = Intent()
        intentDetails.setClass(this, SummaryActivity::class.java)
        intentDetails.putExtra(KEY_INCOME, income)
        intentDetails.putExtra(KEY_EXPENSES, expenses)
        startActivity(intentDetails)
    }

    fun deleteAllItems() {
        layoutContent.removeAllViews()
        income = 0f
        expenses = 0f
        updateBalance()
    }

    fun checkEmpty() {
        val txt = etName.text
        val amt = etAmount.text
        val view: View = mainView

        if (txt.isEmpty() and amt.isNullOrBlank()) {
            Snackbar.make(view, getString(R.string.name_value_error), Snackbar.LENGTH_SHORT).show()
        } else if (amt.isNullOrBlank()) {
            Snackbar.make(view, getString(R.string.value_error), Snackbar.LENGTH_SHORT).show()
        } else if (txt.isEmpty()) {
            Snackbar.make(view, getString(R.string.name_error), Snackbar.LENGTH_SHORT).show()
        } else {
            createItem()
            updateBalance()
        }
    }

    fun updateBalance() {
        val balance = income - expenses
        tvBalance.text = balance.toString()
    }

    fun changeExpenseType() {
        if (itemType == 0) {
            btnType.text = getString(R.string.expense)
            itemType = 1
        } else {
            btnType.text = getString(R.string.income)
            itemType = 0
        }
    }

    fun createItem() {
        var itemRow = layoutInflater.inflate(R.layout.item_row, null, false)

        itemRow.tvName.text = etName.text.toString()
        val amtTxt = etAmount.text.toString()
        itemRow.tvAmount.text = amtTxt
        val amount = amtTxt.toFloat()
        var newItem: item

        if (itemType == 0) {
            income += amount
            itemRow.imgArrow.setImageResource(R.drawable.greenarrow)
            newItem = item(0, amount)
        } else {
            expenses += amount
            itemRow.imgArrow.setImageResource(R.drawable.redarrow)
            newItem = item(1, amount)
        }

        itemRow.btnDelete.setOnClickListener {
            deleteItem(newItem, itemRow)
        }

        layoutContent.addView(itemRow, 0)
        etName.setText("")
        etAmount.setText("")
    }

    fun deleteItem(newItem: item, itemRow: View?) {
        if (newItem.type == 0) {
            income -= newItem.amount
        } else {
            expenses -= newItem.amount
        }
        updateBalance()
        layoutContent.removeView(itemRow)
    }
}
