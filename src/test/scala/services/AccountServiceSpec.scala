package services

import domain.{User, UserAccount}
import fixtures.{AccountFixture, UserFixture}
import org.scalamock.scalatest.AsyncMockFactory
import org.scalatest.{AsyncWordSpec, MustMatchers}
import persistence.repository.{AccountRepositoryImpl, UserRepositoryImpl}
import service.{AccountServiceImpl, UserServiceImpl}

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
      val foundUser = Future(Some(user))

      val insertedAccount = Future(Right(account.copy(Some(1L))))
      val foundAccount = Future(Some(account.copy(Some(1L))))

      val userAccount = new UserAccount(None, 1L, 1L)

      val createdUserAccount = Future(Right(userAccount.copy(id = Some(1L))))

      (accountRepository.create _) expects (account) returning insertedAccount

      (userRepository.findById _) expects (1L) returning foundUser
      (accountRepository.findById _) expects (1L) returning foundAccount
      (accountRepository.bindUser _) expects * returning createdUserAccount

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
