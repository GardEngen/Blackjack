/**
 * Created by Åsmund Hammer on 19.10.17.
 */


fun main(args: Array<String>) {
    var deck = Deck()

    val dealer = Player("dealer", Int.MAX_VALUE);

    val player = Player("you", 0)

    println("Make bet:")

    var line = readLine()!!.toInt()

    var pot = line*2

    player.wallet =- line;



    dealer.collect(deck.draw())
    player.collect(deck.draw())
    dealer.collect(deck.draw())
    player.collect(deck.draw())

    println("dealer has a " + dealer.cards[0].color + dealer.cards[0].value)

    return
}



/**
 * Created by Åsmund Hammer on 19.10.17.
 */

class Card constructor(color : Color, value: Int){
    val color = color
    val value = value

}




import java.util.*

/**
 * Created by Åsmund Hammer on 19.10.17.
 */
class Deck constructor(){
    var cards = ArrayList<Card>()


    fun reShuffle(){
        cards.clear()
        for(i in 1 until 14){
            cards.add(Card(Color.SPADES, i))
            cards.add(Card(Color.HEARTHS, i))
            cards.add(Card(Color.CLUBS, i))
            cards.add(Card(Color.DIAMONDS, i))
        }
        shuffle()
    }

    init {
        reShuffle()
    }

    fun shuffle() {
        Collections.shuffle(cards)
    }

    fun draw() : Card{
        return cards.removeAt(0)
    }



}




/**
 * Created by Åsmund Hammer on 19.10.17.
 */
enum class Color {
    SPADES, DIAMONDS, HEARTHS, CLUBS
}



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