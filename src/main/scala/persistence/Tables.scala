package persistence

import java.time.LocalDateTime
import domain._
import persistence.repository.SlickMapping.localDateToDate
import slick.lifted.{TableQuery, Tag}
import slick.jdbc.PostgresProfile.api._

object UserTable {

  val query = TableQuery[UserTable]

}

class UserTable(tag: Tag) extends Table[User](tag, "bnk_user") {

  def id = column[Long]("user_id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("user_name")

  def * = (id.?, name) <> (User.tupled, User.unapply)

}

class AccountTable(tag: Tag) extends Table[Account](tag, "bnk_account") {

  def id = column[Long]("account_id", O.PrimaryKey, O.AutoInc)

  def account = column[Long]("account_account")

  def * = (id.?, account) <> (Account.tupled, Account.unapply)

}

class UserAccountTable(tag: Tag)
    extends Table[UserAccount](tag, "bnk_user_account") {

  def id = column[Long]("user_account_id", O.PrimaryKey, O.AutoInc)

  def userId = column[Long]("user_account_user_id")

  def accountId = column[Long]("user_account_account_id")

  def * = (id.?, userId, accountId) <> (UserAccount.tupled, UserAccount.unapply)

}

class TransactionTypeTable(tag: Tag)
    extends Table[TransactionType](tag, "bnk_transaction_type") {

  def id = column[Long]("transaction_type_id", O.PrimaryKey, O.AutoInc)

  def description = column[String]("transaction_type_description")

  def * =
    (id.?, description) <> (TransactionType.tupled, TransactionType.unapply)

}

class TransactionStatusTable(tag: Tag)
    extends Table[TransactionStatus](tag, "bnk_transaction_status") {

  def id = column[Long]("transaction_status_id", O.PrimaryKey, O.AutoInc)

  def description = column[String]("transaction_status_description")

  def * =
    (id.?, description) <> (TransactionStatus.tupled, TransactionStatus.unapply)

}

class TransactionTable(tag: Tag)
    extends Table[Transaction](tag, "bnk_transaction") {

  def id = column[Long]("transaction_id", O.PrimaryKey, O.AutoInc)

  def date = column[LocalDateTime]("transaction_date")

  def value = column[Double]("transaction_value")

  def accountId = column[Long]("transaction_account_id")

  def transactionTypeId = column[Long]("transaction_type_id")

  def transactionStatusId = column[Long]("transaction_status_id")

  def transactionUserId = column[Long]("transaction_user_id")

  def * =
    (id.?,
     date.?,
     value,
     accountId,
     transactionTypeId,
     transactionStatusId,
     transactionUserId.?) <> (Transaction.tupled, Transaction.unapply)

}
