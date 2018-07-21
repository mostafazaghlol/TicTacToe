package com.mostafa.android.tictactoe

import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by mostafa salah zaghloul
 *you can reach me on 01148295140 on 7/20/18.
 */
class HelloKotlin (private var message:String) {
    constructor():this("Hello")
    fun displayKotlinMessage(view: View) {
        Snackbar.make(view, ""+message,
                Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }
}