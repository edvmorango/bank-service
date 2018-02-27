package generators

import org.scalacheck.Gen

trait TransactionsGenerator {

  val transactionValue: Gen[Double] =
    for {
      value <- Gen.chooseNum[Double](0, 100000000)
    } yield value

}
