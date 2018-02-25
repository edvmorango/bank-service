package persistence.repository

import domain.User
import persistence.{UserTable, PostgresDB}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait ClientRepository extends {

  def findById(id: Long): Future[Option[User]]

  def create(obj: User): Future[User]

}

class ClientRepositoryImpl extends ClientRepository {

  private val db = PostgresDB.db
  private val userQuery = UserTable.query

  override def findById(id: Long): Future[Option[User]] =
    db.run(userQuery.filter(_.id === id).result.headOption)

  override def create(obj: User): Future[User] = {
    db.run {
      userQuery returning userQuery.map(_.id) into (
          (c,
           id) => c.copy(id = Some(id))) += obj
    }

  }

}
