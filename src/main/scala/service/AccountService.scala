package service

import cats.data.{EitherT, OptionT}
import com.google.inject.Inject

import scala.concurrent.Future
import domain.{Account, User, UserAccount}
import persistence.repository.AccountRepositoryImpl
import cats.implicits.catsStdInstancesForFuture

import scala.concurrent.ExecutionContext.Implicits.global

trait AccountService {

  def create(obj: Account, user: User): EitherT[Future, Throwable, Account]

  def findById(id: Long): OptionT[Future, Account]

  def bindUser(accountId: Long,
               userId: Long): EitherT[Future, Throwable, UserAccount]

}

class AccountServiceImpl @Inject()(accountRep: AccountRepositoryImpl,
                                   userService: UserService)
    extends AccountService {

  def create(account: Account,
             user: User): EitherT[Future, Throwable, Account] = {
    for {
      account <- EitherT(accountRep.create(account))
      binded <- bindUser(account.id.get, user.id.get)
    } yield account

  }

  def findById(id: Long): OptionT[Future, Account] =
    OptionT.apply(accountRep.findById(id))

  def bindUser(accountId: Long, userId: Long) = {

    for {
      user <- EitherT.fromOptionF[Future, Throwable, User](
        userService.findById(userId).value,
        throw new Throwable("user"))

      account <- EitherT.fromOptionF[Future, Throwable, Account](
        findById(userId).value,
        throw new Throwable("account"))

      usrAcc = UserAccount(None, user.id.get, account.id.get)
      userAccount <- EitherT.apply(accountRep.bindUser(usrAcc))
    } yield userAccount
  }

}
