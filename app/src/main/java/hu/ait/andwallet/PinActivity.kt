package hu.ait.andwallet

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pin.*

class PinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        btnEnter.setOnClickListener {
            var pin = etPin.text.toString()

            val view: View = pinAct

            if (pin == "5738") {
                var intent = Intent()
                intent.setClass(this, MainActivity::class.java)
                etPin.setText("")
                startActivity(intent)
            } else {
                Snackbar.make(view, getString(R.string.incorrect_pin), Snackbar.LENGTH_SHORT).show()
                etPin.setText("")
            }
        }
    }
}