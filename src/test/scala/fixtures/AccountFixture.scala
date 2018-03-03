package fixtures

import domain.Account

object AccountFixture {

  val getAccount = Account(None, 11111111)
  val getAccount2 = Account(Some(2L), 11111112)
  val getAccount3 = Account(Some(3L), 11111113)

  val getAccounts = List(getAccount.copy(Some(1L)), getAccount2, getAccount3)

}
