package util

import cats.{Alternative, Functor}
import com.twitter.util.Future

object TwitterFutureInstances {

  implicit val twitterFutureInstance: Functor[Future] = {
    new Functor[Future] {
      override def map[A, B](fa: Future[A])(f: A => B): Future[B] = fa.map(f)
    }
  }

}
