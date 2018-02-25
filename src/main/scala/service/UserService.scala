package service

import cats.data.{EitherT, OptionT}
import com.google.inject.Inject
import com.twitter.util.Future
import domain.User
import persistence.repository
import persistence.repository.{UserRepository, UserRepositoryImpl}
import util.TwitterFutureSyntax._
import scala.concurrent.ExecutionContext.Implicits.global
trait UserService {

  def create(user: User): EitherT[Future, Throwable, User]

  def findById(id: Long): OptionT[Future, User]

}

class UserServiceImpl @Inject()(userRep: UserRepositoryImpl)
    extends UserService {

  override def create(user: User): EitherT[Future, Throwable, User] =
    userRep.create(user).asTFEitherT

  override def findById(id: Long): OptionT[Future, User] =
    userRep.findById(id).asTFOptionT

}
