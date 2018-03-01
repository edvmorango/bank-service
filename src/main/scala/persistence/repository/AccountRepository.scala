package persistence.repository

import domain.Account
import persistence.{AccountTable, PostgresDB}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait AccountRepository extends {

  def findById(id: Long): Future[Option[Account]]

  def create(obj: Account): Future[Either[Throwable, Account]]

}

class AccountRepositoryImpl extends AccountRepository {

  private val db = PostgresDB.db
  private val accountQuery = AccountTable.query

  override def findById(id: Long): Future[Option[Account]] =
    db.run(accountQuery.filter(_.id === id).result.headOption)

  override def create(obj: Account): Future[Either[Throwable, Account]] = {
    db.run {
        (accountQuery returning accountQuery.map(_.id) into (
            (c,
             id) => c.copy(id = Some(id))) += obj).map(Right(_))
      }
      .recover { case e: Throwable => Left(e) }
  }

}
