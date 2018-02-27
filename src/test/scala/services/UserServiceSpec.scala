package services

import domain.User
import fixtures.UserFixture
import org.scalatest.{BeforeAndAfterAll, FunSpec, MustMatchers, WordSpec}

class UserServiceSpec
    extends WordSpec
    with MustMatchers
    with BeforeAndAfterAll {

  "User service" should {
    "create a User " in {

      true mustBe true
    }

    "get a user by Id" in {
      true mustBe true
    }

  }

}
