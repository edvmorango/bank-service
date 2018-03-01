package persistence.repository

import java.sql
import java.time.{LocalDate, LocalDateTime, ZoneId, ZoneOffset}
import java.sql.{Date, Timestamp}

import slick.jdbc.PostgresProfile.api._

object SlickMapping {

  implicit val localDateToDate =
    MappedColumnType.base[LocalDateTime, Timestamp](l => Timestamp.valueOf(l),
                                                    d => d.toLocalDateTime)

}
