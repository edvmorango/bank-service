import com.twitter.finagle.http.Request
import com.twitter.finatra.http.{Controller, HttpServer}
import com.twitter.finatra.http.routing.HttpRouter
import domain.User
import persistence.repository.ClientRepositoryImpl

import scala.concurrent.ExecutionContext.Implicits.global
import util.TwitterFutureSyntax
import util.TwitterFutureSyntax.RichSFuture

object BankMain extends BankHttpServer

class BankHttpServer extends HttpServer {

  override def defaultFinatraHttpPort = ":8001"

  override def configureHttp(router: HttpRouter): Unit = {

    router.add[TestController]

  }

}

class TestController extends Controller {

  get("/") { req: Request =>
    val client = User(name = "José Eduardo")

    val rep = new ClientRepositoryImpl()

    rep.create(client).asTwitter

  }

}
