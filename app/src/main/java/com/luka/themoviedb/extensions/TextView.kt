package com.luka.themoviedb.extensions

import android.widget.TextView
import com.luka.themoviedb.R
import com.luka.themoviedb.utils.Constants.RATING_AVERAGE
import com.luka.themoviedb.utils.Constants.RATING_BAD
import com.luka.themoviedb.utils.Constants.RATING_GOOD

fun TextView.loadBackground(vote: Double?) {
    when {
        vote!! < 4.0 -> {
            this.text =
                context.getString(R.string.rating, RATING_BAD, vote.toString())
            this.setBackgroundResource(R.drawable.rating_background_bad)
        }
        vote >= 4.0 && vote < 7 -> {
            this.text = context.getString(
                R.string.rating,
                RATING_AVERAGE,
                vote.toString()
            )
            this.setBackgroundResource(R.drawable.rating_background_average)
        }
        vote >= 7.0 -> {
            this.text =
                context.getString(R.string.rating, RATING_GOOD, vote.toString())
            this.setBackgroundResource(R.drawable.rating_background_good)
        }
    }
}