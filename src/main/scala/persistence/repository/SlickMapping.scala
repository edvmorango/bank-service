package persistence.repository

import java.sql
import java.time.{LocalDate, LocalDateTime, ZoneId, ZoneOffset}
import java.sql.Date

import slick.jdbc.PostgresProfile._

object SlickMapping {

  implicit val localDateToDate = MappedColumnType.base[LocalDateTime, Date](
    l => Date.valueOf(l.toLocalDate),
    d => LocalDateTime.ofInstant(d.toInstant, ZoneId.systemDefault()))
}
