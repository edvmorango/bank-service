package persistence

import slick.jdbc.PostgresProfile.api._

object PostgresDB {

  val connectionUrl =
    "jdbc:postgresql://192.168.99.100:5432/bank?user=bank&password=bank123!@#"

  lazy val db = Database.forURL(connectionUrl)

}
