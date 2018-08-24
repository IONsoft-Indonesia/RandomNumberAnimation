package id.co.ionsoft.randomnumberanimation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import id.co.ionsoft.randomnumberanimationlibrary.RandomNumberAnimation
import org.jetbrains.anko.*

/**
 * @author hendrawd on 15 Feb 2018
 */
class ChangeNumbersInATextActivity : BaseActivity() {

    private lateinit var randomNumberAnimation: RandomNumberAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChangeNumbersInATextActivityUi().apply {
            setContentView(this@ChangeNumbersInATextActivity)
            randomNumberAnimation = RandomNumberAnimation(textView)
            randomNumberAnimation.apply {
                delay = 1_000L
                buttonStart.setOnClickListener {
                    start()
                }
                buttonStop.setOnClickListener {
                    stop()
                    alert(R.string.alert_numbers_back_to_original) {
                        positiveButton(R.string.cool) {}
                    }.show()
                }
            }
        }
        createBackButton()
    }

    override fun onStop() {
        randomNumberAnimation.stop()
        super.onStop()
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
