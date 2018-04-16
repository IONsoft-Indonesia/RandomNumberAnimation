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

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Handler
import android.util.Log
import android.widget.TextView

/**
 * Create random number animation from a TextView
 * @author hendrawd on 1/4/18
 */

private val TAG by lazy {
    RandomNumberAnimation::class.simpleName
}

class RandomNumberAnimation(private var textView: TextView) {

    private val randomizer by lazy {
        Randomizer()
    }

    private val handler by lazy {
        Handler()
    }

    private val runnable by lazy {
        object : Runnable {
            override fun run() {
                textView.text = randomizer.randomize(textView.text)
                handler.postDelayed(this, delay)
            }
        }
    }

    private var isRunning = false
    private var defaultValue: CharSequence
    var delay = 16L

    init {
        val activity = Util.getActivity(textView.context)
        if (activity != null) {
            if (activity is LifecycleOwner) {
                (activity as LifecycleOwner).lifecycle.addObserver(StopObserver())
                Log.d(TAG, "activity is lifecycle owner")
            }
            Log.d(TAG, "activity from init is not null")
        } else {
            Log.d(TAG, "activity from init is null")
        }
        this.defaultValue = textView.text
    }

    private inner class StopObserver : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onLifecycleOwnerDestroy() {
            Log.d(TAG, "onDestroy of lifecycle owner")
            stop()
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
            handler.removeCallbacks(runnable)
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
            handler.postDelayed(runnable, delay)
            isRunning = true
        }
    }
}
