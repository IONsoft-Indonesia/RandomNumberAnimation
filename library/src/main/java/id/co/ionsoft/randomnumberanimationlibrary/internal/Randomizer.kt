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

import java.security.SecureRandom

/**
 * @author hendrawd on 16 Apr 2018
 */
internal class Randomizer {

    private val random = SecureRandom()

    /**
     * Takes a CharSequence and returns a String with the same length of the input,
     * but randomize the numbers inside it
     */
    internal fun randomize(numbers: CharSequence): String {
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
}