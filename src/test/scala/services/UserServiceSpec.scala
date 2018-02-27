package services

import com.twitter.util.{Await, Duration, Future}
import domain.User
import fixtures.UserFixture
import org.scalamock.scalatest.{AsyncMockFactory, MockFactory}
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, MustMatchers, WordSpec}
import persistence.repository.UserRepositoryImpl
import service.{UserService, UserServiceImpl}
import util.TwitterFutureInstances._
import util.TwitterFutureSyntax._

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
        .asScala
    }

    "find a user by Id" in {

      val userRepository = mock[UserRepositoryImpl]
      val user = UserFixture.getUserWithId
      val userRepositoryFindById = scala.concurrent.Future { Some(user) }

      (userRepository.findById _) expects (1L) returning userRepositoryFindById

      val userService = new UserServiceImpl(userRepository)

      userService.findById(1L).map(u => u mustBe user).value.asScala.map(_.get)

    }

    "bind user to account" in {

      fail()

    }

    "user cash out" in {

      fail()

    }

    "user do bank trasnfer" in {

      fail()

    }

    "find user accounts" in {

      fail()

    }

    "find user account balance" in {

      fail()

    }

    "find user total balance" in {

      fail()

    }

  }

}
