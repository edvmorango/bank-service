package generators

import domain.{Account, User}
import org.scalacheck.Gen

trait AccountsGenerator {

  val accountGen: Gen[Account] = for {
    account <- Gen.chooseNum(1000, 99999999)
  } yield Account(None, account)

  val accountGenWithId: Gen[Account] = for {
    id <- Gen.chooseNum(1, 1000)
    account <- accountGen
  } yield account.copy(id = Some(id))

}
