package persistence

import java.sql.Timestamp
import java.time.LocalDateTime

import slick.jdbc.PostgresProfile.api._

object SlickMapping {

  implicit val localDateToDate =
    MappedColumnType.base[LocalDateTime, Timestamp](l => Timestamp.valueOf(l),
                                                    d => d.toLocalDateTime)

}
