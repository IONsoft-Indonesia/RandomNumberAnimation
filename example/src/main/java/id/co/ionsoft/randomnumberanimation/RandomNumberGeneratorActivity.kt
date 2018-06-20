package id.co.ionsoft.randomnumberanimation

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import id.co.ionsoft.randomnumberanimationlibrary.RandomNumberAnimation
import org.jetbrains.anko.*

private const val TIME_TO_GENERATE = 3_000L // in milliseconds

class RandomNumberGeneratorActivity : AppCompatActivity() {

    private var randomNumberAnimation: RandomNumberAnimation? = null
    private var isGeneratingRandomNumber = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val randomNumberGeneratorActivityUi = RandomNumberGeneratorActivityUi()
        randomNumberGeneratorActivityUi.setContentView(this)
        randomNumberAnimation = RandomNumberAnimation(randomNumberGeneratorActivityUi.textView)

        randomNumberGeneratorActivityUi.buttonCreateRandomNumber.setOnClickListener {
            if (!isGeneratingRandomNumber) {
                isGeneratingRandomNumber = true
                randomNumberAnimation?.start()
                Handler().postDelayed({
                    randomNumberAnimation?.stop(true)
                    isGeneratingRandomNumber = false
                }, TIME_TO_GENERATE)
            } else {
                toast(R.string.still_generating)
            }
        }

        createBackButton()
    }

    override fun onStop() {
        randomNumberAnimation?.stop(true)
        isGeneratingRandomNumber = false
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

class RandomNumberGeneratorActivityUi : AnkoComponent<RandomNumberGeneratorActivity> {

    lateinit var textView: TextView
    lateinit var buttonCreateRandomNumber: Button

    override fun createView(ui: AnkoContext<RandomNumberGeneratorActivity>): View = with(ui) {
        scrollView {
            linearLayout {
                orientation = LinearLayout.VERTICAL
                padding = dip(16)

                textView = textView {
                    textSize = 100f
                    textResource = R.string.default_value
                    gravity = Gravity.CENTER_HORIZONTAL
                }

                buttonCreateRandomNumber = button {
                    textResource = R.string.create_random_number
                }.lparams {
                    width = LinearLayout.LayoutParams.MATCH_PARENT
                    height = LinearLayout.LayoutParams.WRAP_CONTENT
                    topMargin = dip(8)
                }
            }
        }
    }
}
