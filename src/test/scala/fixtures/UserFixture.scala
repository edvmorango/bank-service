package fixtures

import domain.User

object UserFixture {

  val getUser = User(None, "Kovac")
  val getUserWithId = getUser.copy(Some(1L))

}
