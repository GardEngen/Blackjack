package com.group2.blackjack.Callbacks

import com.group2.blackjack.Entities.Highscore

/**
 * Created by Gard on 11.11.2017.
 */
interface AsyncResponseCallback {
    fun getTop10FinishProcess(output: Array<Highscore>)
}