package id.co.ionsoft.randomnumberanimation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import org.jetbrains.anko.*

/**
 * @author hendrawd on 2/15/18
 */
class ChooserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val chooserActivityUi = ChooserActivityUi()
        chooserActivityUi.setContentView(this)
        chooserActivityUi.buttonRandomNumberGenerator.setOnClickListener {
            val intent = Intent(this, RandomNumberGeneratorActivity::class.java)
            startActivity(intent)
        }
        chooserActivityUi.buttonChangeNumbersInAText.setOnClickListener {
            val intent = Intent(this, ChangeNumbersInATextActivity::class.java)
            startActivity(intent)
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