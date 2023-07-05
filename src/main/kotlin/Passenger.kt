import java.time.Duration

class Passenger(
    var id: String,
    var name: String,
    var flights: MutableList<Flight> = mutableListOf()
) {
    fun bookFlight(flight: Flight) {
        flights.add(flight)
    }

    fun cancelFlight(flightNumber: String) {
        val flight = flights.find { it.flightNumber == flightNumber }
        if (flight != null) {
            flights.remove(flight)
        }
    }

    fun getTotalTravelTime(): Duration {
        return Duration.between(
            flights.first().departureTime,
            flights.last().arrivalTime
        )
    }
}