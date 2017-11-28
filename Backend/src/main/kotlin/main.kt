import RestAPI.module
import io.ktor.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

/**
 * Created by Gard on 11.11.2017.
 */
fun main(args: Array<String>) {
    embeddedServer(Netty, 5000, watchPaths = listOf("HighscoreKt"), module = Application::module).start()
}