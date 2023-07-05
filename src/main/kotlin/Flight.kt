import java.time.LocalDateTime

class Flight(
    var flightNumber: String,
    var source: String,
    var destination: String,
    var departureTime: LocalDateTime,
    var arrivalTime: LocalDateTime
)