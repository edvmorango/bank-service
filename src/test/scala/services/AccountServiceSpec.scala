package services

import cats.data.{EitherT, OptionT}
import domain.{Account, User, UserAccount}
import fixtures.{AccountFixture, UserFixture}
import org.scalamock.scalatest.AsyncMockFactory
import org.scalatest.{AsyncWordSpec, MustMatchers}
import persistence.repository.{AccountRepositoryImpl, UserRepositoryImpl}
import service.{AccountServiceImpl, UserServiceImpl}
import util.MonadTransformersSyntax._
import scala.concurrent.Future
import cats.implicits.catsStdInstancesForFuture

class AccountServiceSpec
    extends AsyncWordSpec
    with MustMatchers
    with AsyncMockFactory {

  "Account Service" should {

    "create account" in {

      val userRepository = mock[UserRepositoryImpl]
      val accountRepository = mock[AccountRepositoryImpl]

      val account = AccountFixture.getAccount

      val user = UserFixture.getUserWithId
      val foundUser = Future(Option(user))

      val insertedAccount = Future(Right(account.copy(Option(1L))))
      val foundAccount = Future(Option(account.copy(Option(1L))))

      val userAccount = new UserAccount(None, 1L, 1L)

      val createdUserAccount = Future(Right(userAccount.copy(id = Option(1L))))

      (accountRepository.create _) expects (account) returning insertedAccount
        .asMTransformer()

      (userRepository.findById _) expects (1L) returning
        foundUser.asMTransformer()

      (accountRepository.findById _) expects (1L) returning
        foundAccount.asMTransformer()

      (accountRepository.bindUser _) expects * returning
        createdUserAccount.asMTransformer()

      val userService = new UserServiceImpl(userRepository)

      val accountService =
        new AccountServiceImpl(accountRepository, userService)

      accountService
        .create(account, user)
        .map(a => a mustBe account.copy(id = Some(1L)))
        .getOrElse(fail())

    }

    "find account by id" in {
      ???
    }

    "find account balance" in {
      ???
    }

    "bind a new user to account" in {
      ???
    }

  }

}
