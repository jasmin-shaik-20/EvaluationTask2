import java.time.LocalDateTime

class FlightBookingSystem {
    var flights: MutableList<Flight> = mutableListOf()
    var passengers: MutableList<Passenger> = mutableListOf()

    fun addFlight(flight: Flight) {
        flights.add(flight)
    }

    fun removeFlight(flightNumber: String) {
        val flight = flights.find { it.flightNumber == flightNumber }
        if (flight != null) {
            flights.remove(flight)
        }
    }

    fun addPassenger(passenger: Passenger) {
        passengers.add(passenger)
    }

    fun removePassengerById(passengerId: String) {
        val passenger = passengers.find { it.id == passengerId }
        if (passenger != null) {
            passengers.remove(passenger)
        }
    }

    fun searchFlights(source: String, destination: String, departureTime: LocalDateTime): List<Flight> {
        return flights.filter { it.source == source && it.destination == destination && it.departureTime == departureTime
        }
    }

    fun printPassengerDetails(passengerId: String) {
        val passenger = passengers.find { it.id == passengerId }
        if (passenger != null) {
            println("Passenger ID: ${passenger.id}")
            println("Name: ${passenger.name}")
            println("Booked Flights:")
            passenger.flights.forEachIndexed { index, flight ->
                println("Flight ${index + 1}: ${flight.flightNumber}")
            }
        } else {
            println("Passenger not found.")
        }
    }

    fun printAllPassengers() {
        passengers.forEach { passenger ->
            println("Passenger ID: ${passenger.id}")
            println("Name: ${passenger.name}")
            println("Booked Flights:")
            passenger.flights.forEachIndexed { index, flight ->
                println("Flight ${index + 1}: ${flight.flightNumber}")
            }
            println("-----------------------")
        }
    }

    fun printFlightDetails(flightNumber: String) {
        val flight = flights.find { it.flightNumber == flightNumber }
        if (flight != null) {
            println("Flight Number: ${flight.flightNumber}")
            println("Source: ${flight.source}")
            println("Destination: ${flight.destination}")
            println("Departure Time: ${flight.departureTime}")
            println("Arrival Time: ${flight.arrivalTime}")
        } else {
            println("Flight not found.")
        }
    }
}