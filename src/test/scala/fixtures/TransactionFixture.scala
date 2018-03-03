package fixtures

import domain.Transaction

object TransactionFixture {

  val transaction = Transaction(None, None, 250.0, 1, 1, 1, None)
  val transaction2 = Transaction(Some(2L), None, 50.0, 1, 2, 1, None)
  val transaction3 = Transaction(Some(3L), None, 100.0, 1, 1, 1, None)

  val transactions =
    List(transaction.copy(Some(1L)), transaction2, transaction3)

}
