import java.time.Duration
import java.time.LocalDateTime

class FlightBookingSystem {
    var flights: MutableList<Flight> = mutableListOf()
    var passengers: MutableList<Passenger> = mutableListOf()

    fun addFlight(flight: Flight) {
        flights.add(flight)
    }

    fun removeFlight(flightNumber: String) {
        val flight = flights.firstOrNull { it.flightNumber == flightNumber }
        flight?.let { flights.remove(it) }
    }

    fun addPassenger(passenger: Passenger) {
        passengers.add(passenger)
    }

    fun removePassengerById(passengerId: String) {
        val passenger = passengers.firstOrNull { it.id == passengerId }
        passenger?.let { passengers.remove(it) }
    }

    fun searchFlights(
        source: String,
        destination: String,
        departureTime: LocalDateTime
    ): List<Flight> {
        return flights.filter {
            it.source == source && it.destination == destination &&
                    it.departureTime == departureTime
        }
    }

    fun printPassengerDetails(passengerId: String) {
        val passenger = passengers.firstOrNull { it.id == passengerId }
        passenger?.let {
            println("Passenger Details:")
            println("ID: ${it.id}")
            println("Name: ${it.name}")
            println("Flights:")
            it.flights.forEach { flight ->
                println("Flight Number: ${flight.flightNumber}")
                println("Source: ${flight.source}")
                println("Destination: ${flight.destination}")
                println("Departure Time: ${flight.departureTime}")
                println("Arrival Time: ${flight.arrivalTime}")
            }
        }
    }

    fun printAllPassengers() {
        println("Passenger Details:")
        passengers.forEach { passenger ->
            println("ID: ${passenger.id}")
            println("Name: ${passenger.name}")
            println("Flights:")
            passenger.flights.forEach { flight ->
                println("Flight Number: ${flight.flightNumber}")
                println("Source: ${flight.source}")
                println("Destination: ${flight.destination}")
                println("Departure Time: ${flight.departureTime}")
                println("Arrival Time: ${flight.arrivalTime}")
            }
        }
    }

    fun printFlightDetails(flightNumber: String) {
        val flight = flights.firstOrNull { it.flightNumber == flightNumber }
        flight?.let {
            println("Flight Details:")
            println("Flight Number: ${it.flightNumber}")
            println("Source: ${it.source}")
            println("Destination: ${it.destination}")
            println("Departure Time: ${it.departureTime}")
            println("Arrival Time: ${it.arrivalTime}")
        }
    }
    fun getShortestFlight(flights: List<Flight>): Flight? {
        return flights.minByOrNull {
            Duration.between(it.departureTime, it.arrivalTime)
        }
    }

    val getPassengerCountByFlight: (String) -> Int = { flightNumber ->
        passengers.count { passenger ->
            passenger.flights.any { it.flightNumber == flightNumber }
        }
    }

    suspend fun processBookings(bookings: List<Pair<String, String>>) {
        bookings.forEach { (passengerId, flightNumber) ->
            val passenger = passengers.firstOrNull { it.id == passengerId }
            val flight = flights.firstOrNull { it.flightNumber == flightNumber }
            if (passenger != null && flight != null) {
                passenger.bookFlight(flight)
                println("Booking successful for Passenger ID: $passengerId, Flight Number: $flightNumber")
            } else {
                println("Booking failed for Passenger ID: $passengerId, Flight Number: $flightNumber")
            }
        }
    }
}
