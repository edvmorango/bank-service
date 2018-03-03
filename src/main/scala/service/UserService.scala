package service

import cats.data.{EitherT, OptionT}
import com.google.inject.Inject

import scala.concurrent.Future
import domain.User
import persistence.repository.{
  AccountRepository,
  UserRepository,
  UserRepositoryImpl
}

trait UserService {

  def create(user: User): EitherT[Future, Throwable, User]

  def findById(id: Long): OptionT[Future, User]

}

class UserServiceImpl @Inject()(userRep: UserRepository) extends UserService {

  def create(user: User): EitherT[Future, Throwable, User] =
    userRep.create(user)

  def findById(id: Long): OptionT[Future, User] =
    userRep.findById(id)

}
