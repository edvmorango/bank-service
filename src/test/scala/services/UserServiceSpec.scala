package services

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import domain.User
import fixtures.UserFixture
import org.scalatest.{BeforeAndAfterAll, FunSpec, MustMatchers, WordSpec}

class UserServiceSpec
    extends WordSpec
    with MustMatchers
    with BeforeAndAfterAll {

  override def beforeAll() {
    FixtureFactoryLoader.loadTemplates("fixtures")
  }

  "User service" should {
    "create a User " in {

      true mustBe true
    }

    "get a user by Id" in {
      true mustBe true
    }

  }

}
