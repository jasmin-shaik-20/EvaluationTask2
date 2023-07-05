import java.time.Duration

class AdditionalMethods {

    fun getShortestFlight(flights: List<Flight>): Flight? {
        return flights.minByOrNull {
            Duration.between(it.departureTime, it.arrivalTime).toMinutes()
        }
    }

    val getPassengerCountByFlight: (String) -> Int = { flightNumber ->
        val flight = bookingSystem.flights.find { it.flightNumber == flightNumber }
        if (flight != null) {
            bookingSystem.passengers.count { passenger ->
                passenger.flights.any { it.flightNumber == flightNumber }
            }
        } else {
            0
        }
    }

    suspend fun processBookings(bookings: List<Pair<String, String>>) {
        bookings.forEach { (passengerId, flightNumber) ->
            val passenger = bookingSystem.passengers.find { it.id == passengerId }
            val flight = bookingSystem.flights.find { it.flightNumber == flightNumber }

            if (passenger != null && flight != null) {
                passenger.bookFlight(flight)
                println("Booking processed: Passenger $passengerId booked Flight $flightNumber")
            } else {
                println("Booking failed: Invalid passenger ID or flight number.")
            }
        }
    }
}