package persistence

import slick.jdbc.PostgresProfile.api._


object PostgresDB {

  lazy val db = Database.forConfig("db.default")

}
