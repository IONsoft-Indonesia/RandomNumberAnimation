package id.co.ionsoft.randomnumberanimationlibrary

import android.widget.TextView
import org.jetbrains.anko.runOnUiThread
import java.security.SecureRandom
import java.util.*

/**
 * Create random number animation from a TextView
 * @author hendrawd on 1/4/18
 */

class RandomNumberAnimation(private val textView: TextView) {
    private val random = SecureRandom()
    private var timer: Timer? = null
    private var defaultValue: CharSequence = textView.text

    /**
     * Takes a number CharSequence and return a random number String with the same length of the input
     */
    private fun randomize(numbers: CharSequence): String {
        return numbers
                .map {
                    if (it in '0'..'9') {
                        getRandomNumberChar()
                    } else {
                        it
                    }
                }
                .joinToString("")
    }

    /**
     * Get random Char from '0'..'9'
     */
    private fun getRandomNumberChar(): Char {
        return Character.forDigit(random.nextInt(10), 10)
    }

    fun stop(keepChange: Boolean = false) {
        timer?.cancel()
        timer = null
        if (!keepChange) {
            textView.text = defaultValue
        }
    }

    fun start() {
        if (timer != null) {
            stop()
        }
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                textView.context.runOnUiThread({
                    if (timer != null) {
                        textView.text = randomize(textView.text)
                    }
                })
            }
        }, 1, 16) // 1000 / 16 = 62.5FPS
    }
}
