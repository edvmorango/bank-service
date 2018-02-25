package fixtures

import br.com.six2six.fixturefactory.{Fixture, Rule}
import domain.User

class UserFixture {

  val default = Fixture
    .of(User.getClass)
    .addTemplate("default", new Rule {
      add("id", None)
    })

}
