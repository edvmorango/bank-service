package persistence.repository

import cats.data.{EitherT, OptionT}
import domain.User
import persistence.{PostgresDB, UserTable}
import slick.jdbc.PostgresProfile.api._
import util.MonadTransformersSyntax._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait UserRepository extends {

  def findById(id: Long): OptionT[Future, User]

  def create(obj: User): EitherT[Future, Throwable, User]

}

class UserRepositoryImpl extends UserRepository {

  private val db = PostgresDB.db
  private val userQuery = UserTable.query

  def findById(id: Long): OptionT[Future, User] =
    db.run(userQuery.filter(_.id === id).result.headOption).asMTransformer()

  def create(obj: User): EitherT[Future, Throwable, User] =
    db.run {
        (userQuery returning userQuery
          .map(_.id) into ((c, id) => c.copy(id = Some(id))) += obj)
          .map(Right(_))
      }
      .recover { case e: Throwable => Left(e) }
      .asMTransformer()

}
