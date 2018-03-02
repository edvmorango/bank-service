package util

import cats.Monad

object FutureInstances {

  import cats.{Functor}
  import com.twitter.util.{Future => TFuture}
  import scala.concurrent.{Future => SFuture}
  import scala.concurrent.ExecutionContext.Implicits.global

  implicit val twitterFutureInstance: Functor[TFuture] = {
    new Functor[TFuture] {
      override def map[A, B](fa: TFuture[A])(f: A => B): TFuture[B] = fa.map(f)
    }
  }

}
