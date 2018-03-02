package services

import org.scalamock.scalatest.AsyncMockFactory
import org.scalatest.{AsyncWordSpec, MustMatchers}

class TransactionServiceSpec
    extends AsyncWordSpec
    with MustMatchers
    with AsyncMockFactory {

  "Transaction Service" should {

    "approve pending transaction " in {
      ???
    }

    "reprove pending transaction" in {
      ???
    }

    "block transaction" in {
      ???
    }

    "reverse transaction" in {
      ???
    }

    "find transactions by account" in {
      ???
    }

    "find transactions by user" in {
      ???
    }

  }

}
