package id.co.ionsoft.randomnumberanimation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import id.co.ionsoft.randomnumberanimationlibrary.RandomNumberAnimation
import org.jetbrains.anko.*

class ChangeNumbersInATextActivity : AppCompatActivity() {

    private var randomNumberAnimation: RandomNumberAnimation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val changeNumbersInATextActivityUi = ChangeNumbersInATextActivityUi()
        changeNumbersInATextActivityUi.setContentView(this)
        randomNumberAnimation = RandomNumberAnimation(changeNumbersInATextActivityUi.textView)
        randomNumberAnimation!!.delay = 1000
        changeNumbersInATextActivityUi.buttonStart.setOnClickListener {
            randomNumberAnimation?.start()
        }
        changeNumbersInATextActivityUi.buttonStop.setOnClickListener {
            randomNumberAnimation?.stop()
        }

        createBackButton()
    }

    override fun onStop() {
        randomNumberAnimation?.stop()
        super.onStop()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createBackButton() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}

class ChangeNumbersInATextActivityUi : AnkoComponent<ChangeNumbersInATextActivity> {

    lateinit var textView: TextView
    lateinit var buttonStart: Button
    lateinit var buttonStop: Button

    override fun createView(ui: AnkoContext<ChangeNumbersInATextActivity>): View = with(ui) {
        scrollView {
            linearLayout {
                orientation = LinearLayout.VERTICAL
                padding = dip(16)

                textView = textView {
                    textResource = R.string.change_number_in_a_text_content
                }

                buttonStart = button {
                    textResource = R.string.start
                }.lparams {
                    width = LinearLayout.LayoutParams.MATCH_PARENT
                    height = LinearLayout.LayoutParams.WRAP_CONTENT
                    topMargin = dip(8)
                }

                buttonStop = button {
                    textResource = R.string.stop
                }.lparams {
                    width = LinearLayout.LayoutParams.MATCH_PARENT
                    height = LinearLayout.LayoutParams.WRAP_CONTENT
                    topMargin = dip(8)
                }
            }
        }
    }
}
