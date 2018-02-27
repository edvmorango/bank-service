package fixtures

import domain.User

object UserFixture {

  def getUser = User(None, "Kovac")
  def getUserWithId = getUser.copy(Some(1L))

}
