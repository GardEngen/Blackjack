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