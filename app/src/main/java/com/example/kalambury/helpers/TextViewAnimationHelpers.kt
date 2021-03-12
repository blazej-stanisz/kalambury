package com.example.kalambury.helpers

import android.view.View
import android.widget.TextView

fun TextView.setTextAnimation(text: String, fadeOutDuration: Long = 100, fadeInDuration: Long = 100, completion: (() -> Unit)? = null) {
    fadOutAnimation(fadeOutDuration) {
        this.text = text
        fadInAnimation(fadeInDuration) {
            completion?.let {
                it()
            }
        }
    }
}

fun View.fadOutAnimation(duration: Long = 100, visibility: Int = View.INVISIBLE, completion: (() -> Unit)? = null) {
    animate()
            .alpha(0f)
            .setDuration(duration)
            .withEndAction {
                this.visibility = visibility
                completion?.let {
                    it()
                }
            }
}

fun View.fadInAnimation(duration: Long = 100, completion: (() -> Unit)? = null) {
    alpha = 0f
    visibility = View.VISIBLE
    animate()
            .alpha(1f)
            .setDuration(duration)
            .withEndAction {
                completion?.let {
                    it()
                }
            }
}
