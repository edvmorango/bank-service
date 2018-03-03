package service

import cats.data.{EitherT, OptionT}
import com.google.inject.Inject

import scala.concurrent.Future
import domain.{Account, User, UserAccount}
import persistence.repository.{AccountRepository, AccountRepositoryImpl}
import cats.implicits.catsStdInstancesForFuture

import scala.concurrent.ExecutionContext.Implicits.global

trait AccountService {

  def create(obj: Account, user: User): EitherT[Future, Throwable, Account]

  def findById(id: Long): OptionT[Future, Account]

  def bindUser(accountId: Long,
               userId: Long): EitherT[Future, Throwable, UserAccount]

  def findUserAccounts(userId: Long): Future[Seq[Account]]

}

class AccountServiceImpl @Inject()(accountRep: AccountRepository,
                                   userService: UserService)
    extends AccountService {

  def create(account: Account,
             user: User): EitherT[Future, Throwable, Account] = {
    for {
      account <- accountRep.create(account)
      binded <- bindUser(account.id.get, user.id.get)
    } yield account

  }

  def findById(id: Long): OptionT[Future, Account] =
    accountRep.findById(id)

  def bindUser(accountId: Long, userId: Long) = {

    for {
      user <- EitherT.fromOptionF[Future, Throwable, User](
        userService.findById(userId).value,
        throw new Throwable("user"))

      account <- EitherT.fromOptionF[Future, Throwable, Account](
        findById(userId).value,
        throw new Throwable("account"))

      usrAcc = UserAccount(None, user.id.get, account.id.get)
      userAccount <- accountRep.bindUser(usrAcc)
    } yield userAccount
  }

  def findUserAccounts(userId: Long) = {
    for {
      _ <- userService.findById(userId).value
      accounts <- accountRep.findUserAccounts(userId)
    } yield accounts
  }

}
