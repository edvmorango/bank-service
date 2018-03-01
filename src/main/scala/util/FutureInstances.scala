package util

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

  implicit val scalaFutureInstance: Functor[SFuture] = {
    new Functor[SFuture] {
      override def map[A, B](fa: SFuture[A])(f: A => B): SFuture[B] = fa.map(f)
    }
  }

}