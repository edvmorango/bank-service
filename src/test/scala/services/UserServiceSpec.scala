package services

import cats.data.{EitherT, OptionT}
import domain.User
import fixtures.UserFixture
import org.scalamock.scalatest.AsyncMockFactory
import org.scalatest.{AsyncWordSpec, MustMatchers}
import persistence.repository.UserRepositoryImpl
import service.UserServiceImpl
import cats.implicits.catsStdInstancesForFuture
import util.MonadTransformersSyntax._

import scala.concurrent.Future

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
        Future(Right(User(id, "Kovacs")))

      (userRepository.create _) expects (newUser) returning userRepositoryCreate
        .asMTransformer()

      val userService = new UserServiceImpl(userRepository)

      userService
        .create(newUser)
        .map(u => u mustBe User(id, "Kovacs"))
        .getOrElse(fail())

    }

    "find a user by Id" in {

      val userRepository = mock[UserRepositoryImpl]
      val user = UserFixture.getUserWithId
      val userRepositoryFindById = (Future(Some(user)))

      (userRepository.findById _) expects (1L) returning userRepositoryFindById
        .asMTransformer()

      val userService = new UserServiceImpl(userRepository)

      userService.findById(1L).map(u => u mustBe user).value.map(_.get)

    }

    "user draws money" in {

      ???

    }

    "user do bank transfer" in {

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
