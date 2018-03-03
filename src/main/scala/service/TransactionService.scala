package service

import cats.data.{EitherT, OptionT}
import com.google.inject.Inject
import domain.Transaction
import persistence.repository.TransactionRepository

import scala.concurrent.Future

trait TransactionService {

  def create(transaction: Transaction): EitherT[Future, Throwable, Transaction]

  def findById(id: Long): OptionT[Future, Transaction]

}

class TransactionServiceImpl @Inject()(transactionRep: TransactionRepository)
    extends TransactionService {

  def create(
      transaction: Transaction): EitherT[Future, Throwable, Transaction] =
    transactionRep.create(transaction)

  def findById(id: Long): OptionT[Future, Transaction] =
    transactionRep.findById(id)

}
