package id.co.ionsoft.randomnumberanimation

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import id.co.ionsoft.randomnumberanimationlibrary.RandomNumberAnimation
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.*
import java.util.concurrent.TimeUnit

private const val TIME_TO_GENERATE = 3_000L // in milliseconds

/**
 * @author hendrawd on 15 Feb 2018
 */
class RandomNumberGeneratorActivity : BaseActivity() {

    private lateinit var randomNumberAnimation: RandomNumberAnimation
    private var isGeneratingRandomNumber = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RandomNumberGeneratorActivityUi().apply {
            setContentView(this@RandomNumberGeneratorActivity)
            randomNumberAnimation = RandomNumberAnimation(textView)
            buttonCreateRandomNumber.setOnClickListener {
                if (!isGeneratingRandomNumber) {
                    isGeneratingRandomNumber = true
                    randomNumberAnimation.apply {
                        start()
                        launch(UI) {
                            delay(TIME_TO_GENERATE, TimeUnit.MILLISECONDS)
                            stop(true)
                            isGeneratingRandomNumber = false
                        }
                    }
                } else {
                    toast(R.string.still_generating)
                }
            }
        }

        createBackButton()
    }

    override fun onStop() {
        randomNumberAnimation.stop(true)
        isGeneratingRandomNumber = false
        super.onStop()
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
