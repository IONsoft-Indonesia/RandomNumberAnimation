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

package id.co.ionsoft.randomnumberanimationlibrary.internal

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/**
 * @author hendrawd on 16 Apr 2018
 */
internal object Util {
    /**
     * Copied and modified from
     * <a href="https://stackoverflow.com/questions/8276634/android-get-hosting-activity-from-a-view/32973351#32973351">a stackoverflow answer</a>
     * to always get an Activity from a View
     */
    fun getActivity(context: Context): Activity? {
        var mutableContext = context
        while (mutableContext is ContextWrapper) {
            if (mutableContext is Activity) {
                return mutableContext
            }
            mutableContext = mutableContext.baseContext
        }
        return null
    }
}