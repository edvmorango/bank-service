package domain

import java.time.LocalDateTime

import EntityType.Id

case class User(id: Id = None, name: String)

case class Account(id: Id = None, account: Long)

case class UserAccount(id: Id = None, userId: Long, accountId: Long)

case class TransactionType(id: Id = None, description: String)

case class TransactionStatus(id: Id = None, description: String)

case class Transaction(id: Id = None,
                       date: Option[LocalDateTime] = None,
                       value: Double,
                       accountId: Long,
                       transactionTypeId: Long,
                       transactionStatusId: Long,
                       transactionUserId: Option[Long] = None)
