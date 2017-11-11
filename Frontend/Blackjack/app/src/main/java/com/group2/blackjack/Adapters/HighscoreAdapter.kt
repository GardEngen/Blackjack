package com.group2.blackjack.Adapters
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.widget.ArrayAdapter
import com.group2.blackjack.Entities.Highscore
import com.group2.blackjack.R


/**
 * Created by Gard on 10.11.2017.
 */
class HighscoreAdapter(context: Context?, resource: Int, highscores: Array<Highscore>?) : ArrayAdapter<Highscore>(context, resource, highscores) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView
        if (v == null) {
            val vi: LayoutInflater
            vi = LayoutInflater.from(context)
            v = vi.inflate(R.layout.highscore_listview_item, null)
        }
        val p = getItem(position)
        if (p != null) {
            println("jeg kommer inn i sett navn")
            val name = v!!.findViewById(R.id.name) as TextView

            name.text = p.name
        }
        return v!!
    }

}