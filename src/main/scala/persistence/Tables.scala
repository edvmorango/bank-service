package persistence

import domain.Client
import slick.lifted.{TableQuery, Tag}
import slick.jdbc.PostgresProfile.api._

object ClientTable {

  val query = TableQuery[ClientTable]

}

class ClientTable(tag: Tag) extends Table[Client](tag, "bnk_client") {

  def id = column[Long]("client_id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("client_name")

  def * = (id.?, name) <> (Client.tupled, Client.unapply)

}
