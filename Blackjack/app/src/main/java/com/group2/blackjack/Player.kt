/**
 * Created by Åsmund Hammer on 19.10.17.
 */
class Player constructor(name: String, wallet: Int){
    val name = name
    var wallet = wallet
    var cards = ArrayList<Card>()

    fun collect(card: Card){
        cards.add(card)
    }

    fun ditchHand(){
        cards.clear()
    }



}