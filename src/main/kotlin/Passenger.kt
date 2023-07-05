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
        val flight = flights.firstOrNull { it.flightNumber == flightNumber }
        flight?.let { flights.remove(it) }
    }

    fun getTotalTravelTime(): Duration {
        return flights.fold(Duration.ZERO) { acc, flight ->
            acc.plus(Duration.between(flight.departureTime, flight.arrivalTime))
        }
    }
}