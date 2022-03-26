package vn.dongtrieu.app.githubtest.utils

import android.content.Context
import androidx.annotation.Px
import kotlin.math.roundToInt

fun dip(context: Context, @Px dips: Int): Int {
    val resources = context.resources
    val scale = resources.displayMetrics.density
    return (dips * scale).roundToInt()
}