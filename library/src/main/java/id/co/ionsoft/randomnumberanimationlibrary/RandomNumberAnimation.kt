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

import android.arch.lifecycle.LifecycleOwner
import android.widget.TextView
import id.co.ionsoft.randomnumberanimationlibrary.internal.RandomNumberAnimationStopper
import id.co.ionsoft.randomnumberanimationlibrary.internal.Randomizer
import id.co.ionsoft.randomnumberanimationlibrary.internal.Util
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.cancel
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.AnkoLogger
import java.util.concurrent.TimeUnit

/**
 * Create random number animation from a TextView
 * @author hendrawd on 1 Apr 2018
 */
class RandomNumberAnimation(private var textView: TextView) : AnkoLogger {

    private val randomizer = Randomizer()
    private val infinityTask = InfinityTask()
    private var isRunning = false
    private var defaultValue: CharSequence
    /**
     * Delay in milliseconds
     */
    var delay = 16L

    init {
        observeActivityLifeCycle()
        defaultValue = textView.text
    }

    /**
     * Helper for running periodic change text task with new random number
     * infinitely until it stopped
     */
    private inner class InfinityTask {
        internal fun start() {
            launch(UI) {
                delay(this@RandomNumberAnimation.delay, TimeUnit.MILLISECONDS)
                if (isRunning) {
                    textView.text = randomizer.randomize(textView.text)
                    start()
                } else {
                    coroutineContext.cancel()
                }
            }
        }
    }

    private fun observeActivityLifeCycle() {
        val activity = Util.getActivity(textView.context)
        if (activity != null) {
            if (activity is LifecycleOwner) {
                (activity as LifecycleOwner).lifecycle.addObserver(
                        RandomNumberAnimationStopper(this)
                )
            }
        }
    }

    fun setFPS(fps: Int) {
        delay = 1000L / fps
    }

    fun getFPS(): Int {
        return (1000L / delay).toInt()
    }

    /**
     * Stop the animation
     */
    fun stop(keepChange: Boolean = false) {
        if (isRunning) {
            isRunning = false
            if (!keepChange) {
                textView.text = defaultValue
            }
        }
    }

    /**
     * Start the animation
     */
    fun start() {
        if (!isRunning) {
            infinityTask.start()
            isRunning = true
        }
    }
}
