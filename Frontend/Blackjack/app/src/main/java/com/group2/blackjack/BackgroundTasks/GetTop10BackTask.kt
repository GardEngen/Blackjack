package com.group2.blackjack.BackgroundTasks

import android.os.AsyncTask
import com.group2.blackjack.Communication.RestClient
import com.group2.blackjack.Entities.Highscore

/**
 * Created by Gard on 11.11.2017.
 */
class GetTop10BackTask : AsyncTask<Void, Void, Array<Highscore>?>() {
    var restClient = RestClient()
    override fun doInBackground(vararg p0: Void?): Array<Highscore>? {
       return restClient.getTop10()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun onPostExecute(result: Array<Highscore>?) {
        if (result != null) {
            result[0].name
        }
    }

}