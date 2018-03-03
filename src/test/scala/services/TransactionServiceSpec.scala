package services

import cats.data.{EitherT, OptionT}
import fixtures.TransactionFixture
import org.scalamock.scalatest.AsyncMockFactory
import org.scalatest.{AsyncWordSpec, MustMatchers}
import persistence.repository.{TransactionRepository, TransactionRepositoryImpl}
import service.TransactionServiceImpl
import util.MonadTransformersSyntax._

import scala.concurrent.Future
import cats.implicits.catsStdInstancesForFuture

class TransactionServiceSpec
    extends AsyncWordSpec
    with MustMatchers
    with AsyncMockFactory {

  "Transaction Service" should {

    "create transaction" in {

      val transactionRepository = mock[TransactionRepository]

      val transaction = TransactionFixture.transaction
      val insertedTransaction = Future(Right(transaction.copy(Some(1L))))

      (transactionRepository.create _) expects transaction returning insertedTransaction
        .asMTransformer()

      val transactionService = new TransactionServiceImpl(transactionRepository)

      transactionService
        .create(transaction)
        .map(a => a mustBe transaction.copy(id = Some(1L)))
        .getOrElse(fail())

    }

    "find transaciton by id" in {

      val transactionRepository = mock[TransactionRepository]

      val transaction = TransactionFixture.transaction.copy(Some(1L))
      val insertedTransaction = Future(Some(transaction)).asMTransformer()

      (transactionRepository.findById _) expects 1L returning insertedTransaction

      val transactionService = new TransactionServiceImpl(transactionRepository)

      transactionService
        .findById(1L)
        .map(a => a mustBe transaction)
        .getOrElse(fail())

    }

    "approve pending transaction " in {
      ???
    }

    "reprove pending transaction" in {
      ???
    }

    "block transaction" in {
      ???
    }

    "reverse transaction" in {
      ???
    }

    "find transactions by account" in {
      ???
    }

    "find transactions by user" in {
      ???
    }

  }

}
