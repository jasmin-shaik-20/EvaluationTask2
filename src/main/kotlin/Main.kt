import kotlinx.coroutines.runBlocking
import java.time.Duration
import java.time.LocalDateTime

val bookingSystem = FlightBookingSystem()
val methods=AdditionalMethods()

fun main() {
    // Create some flights
    val flight1 = Flight("F001", "Airport A", "Airport B",
        LocalDateTime.of(2023, 7, 1, 8, 0),
        LocalDateTime.of(2023, 7, 1, 10, 0)
    )

    val flight2 = Flight("F002", "Airport B", "Airport C",
        LocalDateTime.of(2023, 7, 1, 11, 0),
        LocalDateTime.of(2023, 7, 1, 13, 0)
    )

    val flight3 = Flight("F003", "Airport A", "Airport C",
        LocalDateTime.of(2023, 7, 2, 10, 0),
        LocalDateTime.of(2023, 7, 2, 12, 0)
    )

    // Add flights to the booking system
    bookingSystem.addFlight(flight1)
    bookingSystem.addFlight(flight2)
    bookingSystem.addFlight(flight3)

    // Create some passengers
    val passenger1 = Passenger("P001", "John Doe")
    val passenger2 = Passenger("P002", "Jane Smith")
    val passenger3 = Passenger("P003", "Alice Brown")

    // Add passengers to the booking system
    bookingSystem.addPassenger(passenger1)
    bookingSystem.addPassenger(passenger2)
    bookingSystem.addPassenger(passenger3)

    // Book flights for passengers
    passenger1.bookFlight(flight1)
    passenger1.bookFlight(flight2)
    passenger2.bookFlight(flight1)
    passenger3.bookFlight(flight3)

    // Print passenger details
    bookingSystem.printPassengerDetails("P001")

    // Print all passengers' details
    bookingSystem.printAllPassengers()

    // Print flight details
    bookingSystem.printFlightDetails("F001")

    // Get and print the shortest duration flight
    val shortestFlight = methods.getShortestFlight(bookingSystem.flights)
    if (shortestFlight != null) {
        println("Shortest duration flight: ${shortestFlight.flightNumber}")
    } else {
        println("No flights available.")
    }

    // Calculate and print the number of passengers booked for each flight
    bookingSystem.flights.forEach { flight ->
        val passengerCount = methods.getPassengerCountByFlight(flight.flightNumber)
        println("Flight ${flight.flightNumber}: $passengerCount passengers")
    }

    // Process bookings asynchronously
    val bookings = listOf("P001" to "F002", "P002" to "F003", "P003" to "F001")
    runBlocking {
        methods.processBookings(bookings)
    }
}
