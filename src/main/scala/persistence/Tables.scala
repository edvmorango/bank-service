package persistence

import domain.User
import slick.lifted.{TableQuery, Tag}
import slick.jdbc.PostgresProfile.api._

object UserTable {

  val query = TableQuery[UserTable]

}

class UserTable(tag: Tag) extends Table[User](tag, "bnk_user") {

  def id = column[Long]("user_id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("user_name")

  def * = (id.?, name) <> (User.tupled, User.unapply)

}
