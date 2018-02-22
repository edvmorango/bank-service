import com.twitter.finagle.http.Request
import com.twitter.finatra.http.{Controller, HttpServer}
import com.twitter.finatra.http.routing.HttpRouter
import persistence.PostgresDB

object BankMain extends BankHttpServer

class BankHttpServer extends HttpServer{

  override def defaultFinatraHttpPort = ":8001"

  override def configureHttp(router: HttpRouter): Unit = {


      router.add[TestControler]

  }

}


class TestControler extends Controller{

  get("/") { req: Request =>
    "index"
  }

}
