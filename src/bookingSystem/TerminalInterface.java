package bookingSystem;

import java.io.IOException;
import java.util.Scanner;

public class TerminalInterface {

	public static Scanner scanner;
	
	public static void entry() {
		try {
			parseInput();
		}
		catch(Exception e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
		finally {
			scanner.close();
		}
	}
	
	/*
	 * parse user input
	 */
	public static void parseInput() throws IOException {

		scanner = new Scanner(System.in);
		SeatContainer container = createContainer();

		boolean quit = false;
		options();

		while (!quit) {
			System.out.println("Enter option: ");
			int option = Integer.parseInt(scanner.next());

			switch (option) {
			case 1:
				System.out.println("Booking Seat: enter seat number");
				String seatNr = scanner.next();
				container.bookSeat(seatNr);
				break;
			case 2:
				System.out.println("Cancel booking: enter seat number");
				String number = scanner.next();
				container.cancelBooking(number);
				break;
			case 3:
				container.getSeats();
				break;
			case 4:
				System.out.println("Leaving App");
				quit = true;
				break;
			default:
				System.out.println("Entry not valid");
				options();
				break;
			}
		}

	}

	public static void options() {
		System.out.println("Press: ");
		System.out.println("1 -- book Seat");
		System.out.println("2 -- cancel booking");
		System.out.println("3 -- show all seats");
		System.out.println("4 -- Quit");
	}

	/*
	 * create a new container object
	 */
	public static SeatContainer createContainer() throws IOException {
		System.out.println("Enter the name of the new Booking System");
		String name = scanner.next();
		System.out.println("Enter the number of rows");
		int rows = scanner.nextInt();
		System.out.println("Enter the number of columns");
		int cols = scanner.nextInt();
		SeatContainer container = new SeatContainer(name, rows, cols);
		System.out.println("Created new Bookingsystem -- name: " + name + ", rows: " + rows + ", columns: " + cols);
		return container;
	}

}
