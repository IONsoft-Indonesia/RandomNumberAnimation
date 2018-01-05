/*
 * MIT License
 *
 * Copyright (c) 2018 PT. IONsoft
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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

    /**
     * Stop the animation
     */
    fun stop(keepChange: Boolean = false) {
        timer?.cancel()
        timer = null
        if (!keepChange) {
            textView.text = defaultValue
        }
    }

    /**
     * Start the animation
     */
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
