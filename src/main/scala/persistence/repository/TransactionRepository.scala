package persistence.repository

import cats.data.{EitherT, OptionT}
import domain.Transaction
import persistence.{PostgresDB, TransactionTable}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait TransactionRepository extends {

  def findById(id: Long): OptionT[Future, Transaction]

  def create(obj: Transaction): EitherT[Future, Throwable, Transaction]
}

class TransactionRepositoryImpl extends TransactionRepository {

  private val db = PostgresDB.db
  private val transactionQuery = TransactionTable.query

  override def findById(id: Long): OptionT[Future, Transaction] =
    OptionT(db.run(transactionQuery.filter(_.id === id).result.headOption))

  override def create(
      obj: Transaction): EitherT[Future, Throwable, Transaction] = {
    EitherT(
      db.run {
          (transactionQuery returning transactionQuery
            .map(_.id) into ((c, id) => c.copy(id = Some(id))) += obj)
            .map(Right(_))
        }
        .recover { case e: Throwable => Left(e) })
  }

}
