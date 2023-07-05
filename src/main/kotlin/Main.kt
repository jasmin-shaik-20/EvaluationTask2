import java.time.Duration
import java.time.LocalDateTime
import java.util.Scanner



suspend fun main() {
    val bookingSystem = FlightBookingSystem()
    val scanner = Scanner(System.`in`)
    var exit = false

    while (!exit) {
        println("\nFlight Booking System")
        println("1. Add Flight")
        println("2. Add Passenger")
        println("3. Remove Flight")
        println("4. Remove Passenger")
        println("5. Book Flight for Passenger")
        println("6. Search Flights")
        println("7. Cancel flight")
        println("8. TotalTravelTime")
        println("9. Print Passenger Details")
        println("10. Print All Passengers")
        println("11. Print Flight Details")
        println("12. Print Shortest Duration Flight")
        println("13. Print Passenger Count for Each Flight")
        println("14. Exit")
        print("Enter your choice: ")
        val choice = scanner.nextInt()

        when (choice) {
            1 -> {
                println("\nAdd Flight")
                print("Flight Number: ")
                val flightNumber = readln()
                print("Source Airport: ")
                val sourceAirport =readln()
                print("Destination Airport: ")
                val destinationAirport =readln()
                print("Departure Date and Time (yyyy-MM-dd HH:mm): ")
                val departureDateTime = LocalDateTime.parse(readln())
                print("Arrival Date and Time (yyyy-MM-dd HH:mm): ")
                val arrivalDateTime = LocalDateTime.parse(readln())
                val flight = Flight(flightNumber, sourceAirport, destinationAirport, departureDateTime, arrivalDateTime)
                bookingSystem.addFlight(flight)
                println("Flight added successfully.")
            }
            2 -> {
                println("\nAdd Passenger")
                print("Passenger ID: ")
                val passengerId = readln()
                print("Passenger Name: ")
                val passengerName =readln()
                val passenger = Passenger(passengerId, passengerName)
                bookingSystem.addPassenger(passenger)
                println("Passenger added successfully.")
            }
            3 -> {
                println("\nRemove Flight")
                print("Enter flight number: ")
                val flightNumber = readln()
                bookingSystem.removeFlight(flightNumber)
                println("Flight removed successfully.")
            }
            4 -> {
                println("\nRemove Passenger")
                print("Enter passenger ID: ")
                val passengerId = readln()
                bookingSystem.removePassengerById(passengerId)
                println("Passenger removed successfully.")
            }
            5 -> {
                println("\nBook Flight for Passenger")
                print("Enter passenger ID: ")
                val passengerId = readln()
                print("Enter flight number: ")
                val flightNumber = readln()
                bookingSystem.processBookings(listOf(Pair(passengerId, flightNumber)))
                println("Booking processed successfully.")
            }
            6 -> {
                println("\nSearch Flights")
                print("Enter source airport: ")
                val sourceAirport = readln()
                print("Enter destination airport: ")
                val destinationAirport = readln()
                print("Enter departure date and time (yyyy-MM-dd HH:mm): ")
                val departureDateTime = LocalDateTime.parse(readln())
                val searchResults = bookingSystem.searchFlights(sourceAirport, destinationAirport, departureDateTime)
                if (searchResults.isEmpty()) {
                    println("No flights found for the given criteria.")
                } else {
                    println("Search Results:")
                    searchResults.forEach {
                        println("Flight Number: ${it.flightNumber}")
                        println("Source: ${it.source}")
                        println("Destination: ${it.destination}")
                        println("Departure Time: ${it.departureTime}")
                        println("Arrival Time: ${it.arrivalTime}")
                        println()
                    }
                }
            }

            7 ->{
                println("Enter passengerid:")
                val id=readln()
                println("Enter passenger name:")
                val name= readln()
                val passenger=Passenger(id,name)
                println("\nCancel Flight")
                println("Enter flight number:")
                val flightnumber= readln()
                passenger.cancelFlight(flightnumber)
                println("Flight has been cancelled")
            }

            8->{
                println("Enter passenger ID: ")
                val passengerId = scanner.next()

                val passenger = bookingSystem.passengers.find { it.id == passengerId }

                if (passenger != null) {
                    val totalTravelTime = passenger.getTotalTravelTime()
                    val hours = totalTravelTime.toHours()
                    val minutes = totalTravelTime.toMinutes() % 60
                    println("Total travel time for passenger $passengerId: $hours hours $minutes minutes")
                } else {
                    println("Passenger not found.")
                }
            }


            9 -> {
                println("\nPrint Passenger Details")
                print("Enter passenger ID: ")
                val passengerId = readln()
                bookingSystem.printPassengerDetails(passengerId)
            }
            10 -> {
                println("\nPrint All Passengers")
                bookingSystem.printAllPassengers()
            }
            11 -> {
                println("\nPrint Flight Details")
                print("Enter flight number: ")
                val flightNumber = readln()
                bookingSystem.printFlightDetails(flightNumber)
            }
            12 -> {
                val shortestFlight = bookingSystem.getShortestFlight(bookingSystem.flights)
                if (shortestFlight != null) {
                    println("\nShortest Duration Flight:")
                    println("Flight Number: ${shortestFlight.flightNumber}")
                    println("Source: ${shortestFlight.source}")
                    println("Destination: ${shortestFlight.destination}")
                    println("Departure Time: ${shortestFlight.departureTime}")
                    println("Arrival Time: ${shortestFlight.arrivalTime}")
                } else {
                    println("\nNo flights available.")
                }
            }
            13 -> {
                println("\nPassenger count for each flight:")
                bookingSystem.flights.forEach { flight ->
                    val passengerCount = bookingSystem.getPassengerCountByFlight(flight.flightNumber)
                    println("Flight Number: ${flight.flightNumber}, Passenger Count: $passengerCount")
                }
            }
            14 -> {
                exit = true
                println("\nExiting the program.")
            }
            else -> {
                println("Invalid choice. Please try again.")
            }
        }
    }
}
