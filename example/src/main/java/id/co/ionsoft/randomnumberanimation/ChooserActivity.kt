package id.co.ionsoft.randomnumberanimation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import org.jetbrains.anko.*

/**
 * @author hendrawd on 15 Feb 2018
 */
class ChooserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChooserActivityUi().apply {
            setContentView(this@ChooserActivity)
            buttonRandomNumberGenerator.setOnClickListener {
                startActivity<RandomNumberGeneratorActivity>()
            }
            buttonChangeNumbersInAText.setOnClickListener {
                startActivity<ChangeNumbersInATextActivity>()
            }
        }
    }
}

class ChooserActivityUi : AnkoComponent<ChooserActivity> {

    lateinit var buttonRandomNumberGenerator: Button
    lateinit var buttonChangeNumbersInAText: Button

    override fun createView(ui: AnkoContext<ChooserActivity>): View = with(ui) {
        linearLayout {
            orientation = LinearLayout.VERTICAL
            padding = dip(16)

            buttonRandomNumberGenerator = button {
                textResource = R.string.random_number_generator
            }.lparams(matchParent, wrapContent) {
                bottomMargin = dip(8)
            }

            buttonChangeNumbersInAText = button {
                textResource = R.string.change_number_in_a_text
            }.lparams(matchParent, wrapContent) {
                bottomMargin = dip(8)
            }
        }
    }
}