package br.com.missao.javavskotlin.kotlin.extensions

import android.content.Context
import android.widget.Toast

/**
 * General Extensions Functions
 */

/**
 * Shows a toast
 * @param message message displayed
 * @param duration time the message will be displayed, default value is @link{Toast.LENGTH_LONG}
 */
fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}