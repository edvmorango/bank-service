import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.{Controller, HttpServer}
import com.twitter.finatra.http.routing.HttpRouter
import domain.User
import service.UserServiceImpl
import util.TwitterFutureSyntax.RichSFuture
import scala.concurrent.ExecutionContext.Implicits.global
import cats.implicits.catsStdInstancesForFuture

object BankMain extends BankHttpServer

class BankHttpServer extends HttpServer {

  override def defaultFinatraHttpPort = ":8001"

  override def configureHttp(router: HttpRouter): Unit = {
    router.add[TestController]
  }

}

class TestController @Inject()(service: UserServiceImpl) extends Controller {
  get("/") { req: Request =>
    val user = User(name = "Kovacs")

    service
      .create(user)
      .fold(l => l.getMessage, r => r)
      .asTwitter
  }

}
