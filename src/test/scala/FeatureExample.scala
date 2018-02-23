import com.twitter.finagle.http.Status._
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

//import com.twitter.inject.server.FeatureTest

class FeatureExample extends FeatureTest {

  override val server = new EmbeddedHttpServer(new BankHttpServer)

  test("Started server") {
    server.httpGet("/", andExpect = Ok)
  }

}
