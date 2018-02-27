package generators

import domain.User
import org.scalacheck.Gen

trait UsersGenerator {

  val userGen: Gen[User] = for {
    name <- Gen.alphaUpperStr
  } yield User(None, name)

  val userGenWithId = for {
    id <- Gen.chooseNum(1, 1000)
    user <- userGen
  } yield user.copy(id = Some(id))

}
