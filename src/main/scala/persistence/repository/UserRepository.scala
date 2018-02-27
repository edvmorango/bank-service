package persistence.repository

import cats.data.EitherT
import com.google.inject.ImplementedBy
import domain.User
import persistence.{PostgresDB, UserTable}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait UserRepository extends {

  def findById(id: Long): Future[Option[User]]

  def create(obj: User): Future[Either[Throwable, User]]

}

class UserRepositoryImpl extends UserRepository {

  private val db = PostgresDB.db
  private val userQuery = UserTable.query

  override def findById(id: Long): Future[Option[User]] =
    db.run(userQuery.filter(_.id === id).result.headOption)

  override def create(obj: User): Future[Either[Throwable, User]] = {
    db.run {
        (userQuery returning userQuery.map(_.id) into (
            (c,
             id) => c.copy(id = Some(id))) += obj).map(Right(_))
      }
      .recover { case e: Throwable => Left(e) }
  }

}
