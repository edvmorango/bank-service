package persistence.repository

import cats.data.{EitherT, OptionT}
import domain.{Account, UserAccount}
import persistence.{AccountTable, PostgresDB, UserAccountTable}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait AccountRepository extends {

  def findById(id: Long): OptionT[Future, Account]

  def create(obj: Account): EitherT[Future, Throwable, Account]

  def bindUser(obj: UserAccount): EitherT[Future, Throwable, UserAccount]

}

class AccountRepositoryImpl extends AccountRepository {

  private val db = PostgresDB.db
  private val accountQuery = AccountTable.query
  private val userAccountQuery = UserAccountTable.query

  override def findById(id: Long): OptionT[Future, Account] =
    OptionT(db.run(accountQuery.filter(_.id === id).result.headOption))

  override def create(obj: Account): EitherT[Future, Throwable, Account] = {
    EitherT(
      db.run {
          (accountQuery returning accountQuery
            .map(_.id) into ((c, id) => c.copy(id = Some(id))) += obj)
            .map(Right(_))
        }
        .recover { case e: Throwable => Left(e) })
  }

  override def bindUser(
      obj: UserAccount): EitherT[Future, Throwable, UserAccount] = {

    EitherT(
      db.run {
          (userAccountQuery returning userAccountQuery
            .map(_.id) into ((c, id) => c.copy(id = Some(id))) += obj)
            .map(Right(_))
        }
        .recover { case e: Throwable => Left(e) })

  }

}
