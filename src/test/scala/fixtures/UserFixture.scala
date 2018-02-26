package fixtures

import br.com.six2six.fixturefactory.loader.TemplateLoader
import br.com.six2six.fixturefactory.{Fixture, Rule}
import domain.User

class UserFixture extends TemplateLoader {

  override def load(): Unit = {
    Fixture
      .of(User.getClass)
      .addTemplate("default", new Rule {
        add("id", None)
      })
  }

}
