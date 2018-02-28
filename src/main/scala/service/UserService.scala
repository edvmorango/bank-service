package service

import cats.data.{EitherT, OptionT}
import com.google.inject.Inject
import scala.concurrent.Future
import domain.User
import persistence.repository.{UserRepositoryImpl}
trait UserService {

  def create(user: User): EitherT[Future, Throwable, User]

  def findById(id: Long): OptionT[Future, User]

}

class UserServiceImpl @Inject()(userRep: UserRepositoryImpl)
    extends UserService {

  override def create(user: User): EitherT[Future, Throwable, User] =
    EitherT.apply(userRep.create(user))

  override def findById(id: Long): OptionT[Future, User] =
    OptionT.apply(userRep.findById(id))

}
