package service

import cats.data.{EitherT, OptionT}
import com.google.inject.Inject

import scala.concurrent.Future
import domain.{Account, User}
import persistence.repository.AccountRepositoryImpl
trait AccountService {

  def create(userId: Long,
             account: Account): EitherT[Future, Throwable, Account]

  def findById(id: Long): OptionT[Future, Account]

}

class AccountServiceImpl @Inject()(accountRep: AccountRepositoryImpl)
    extends AccountService {

  override def create(userId: Long,
                      account: Account): EitherT[Future, Throwable, Account] =
    EitherT.apply(accountRep.create(account))

  override def findById(id: Long): OptionT[Future, Account] =
    OptionT.apply(accountRep.findById(id))

}
