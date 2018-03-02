package services

import domain.User
import fixtures.UserFixture
import org.scalamock.scalatest.{AsyncMockFactory}
import org.scalatest.{AsyncWordSpec, MustMatchers}
import persistence.repository.UserRepositoryImpl
import service.{UserServiceImpl}
import util.FutureInstances._

class UserServiceSpec
    extends AsyncWordSpec
    with MustMatchers
    with AsyncMockFactory {

  "User service" should {

    "create a User " in {

      val userRepository = mock[UserRepositoryImpl]
      val newUser = UserFixture.getUser
      val id = Some(1L)

      val userRepositoryCreate =
        scala.concurrent.Future[Either[Throwable, User]] {
          Right(User(id, "Kovacs"))
        }

      (userRepository.create _) expects (newUser) returning userRepositoryCreate

      val userService = new UserServiceImpl(userRepository)

      userService
        .create(newUser)
        .map(u => u mustBe User(id, "Kovacs"))
        .getOrElse(fail())

    }

    "find a user by Id" in {

      val userRepository = mock[UserRepositoryImpl]
      val user = UserFixture.getUserWithId
      val userRepositoryFindById = scala.concurrent.Future { Some(user) }

      (userRepository.findById _) expects (1L) returning userRepositoryFindById

      val userService = new UserServiceImpl(userRepository)

      userService.findById(1L).map(u => u mustBe user).value.map(_.get)

    }

    "bind user to account" in {
      ???
    }

    "user draws money" in {

      ???

    }

    "user do bank transfer" in {

      ???

    }

    "find user accounts" in {

      ???
    }

    "find user account balance" in {

      ???
    }

    "find user total balance" in {

      ???
    }

  }

}
