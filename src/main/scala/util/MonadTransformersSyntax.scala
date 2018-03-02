package util

import cats.data.{EitherT, OptionT}

import scala.concurrent.Future

object MonadTransformersSyntax {

  implicit class OptionTSyntax[A](f: Future[Option[A]]) {

    def asMTransformer() = OptionT.apply(f)

  }

  implicit class EitherTSyntax[A, B](f: Future[Either[A, B]]) {

    def asMTransformer() = EitherT.apply(f)

  }

}
