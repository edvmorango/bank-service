package persistence.repository

import domain.Client
import persistence.{ClientTable, PostgresDB}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait ClientRepository extends {

  def findById(id: Long): Future[Option[Client]]

  def create(obj: Client): Future[Client]

}

class ClientRepositoryImpl extends ClientRepository {

  private val db = PostgresDB.db
  private val clientQuery = ClientTable.query

  override def findById(id: Long): Future[Option[Client]] =
    db.run(clientQuery.filter(_.id === id).result.headOption)

  override def create(obj: Client): Future[Client] = {
    db.run {
      clientQuery returning clientQuery.map(_.id) into (
          (c,
           id) => c.copy(clientId = Some(id))) += obj
    }

  }

}
