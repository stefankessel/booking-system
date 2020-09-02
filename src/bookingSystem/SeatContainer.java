package bookingSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * @author Stefan
 * class SeatContainer implements a booking system 
 * available Seats are Objects in an ArrayList
 * possible to book Seats or cancel bookings
 * seat objects derive from inner class seat
 */

public class SeatContainer {
	
	private List<Seat> seats = new ArrayList<>();
	private String name;
	
	/*
	 * constructer fills ArrayList
	 */
	public SeatContainer(String name, int numRows, int numCols) {
		this.name = name;
		
		int maxRow = 'A' + (numRows - 1);
		for(char row = 'A'; row <= maxRow; row++) {
			for(int col = 1; col <= numCols; col++) {
				seats.add(new Seat(row + String.format("%02d", col)));
			}
		}
		
	}
	/*
	 * looks for seat in the arraylist - binary search
	 * calls inner class method to check if seat is still available and make the booking
	 * @return true if booking is successfull
	 */
	public boolean bookSeat(String number) {
		Seat reserveSeat = new Seat(number);
		int foundAt = Collections.binarySearch(seats, reserveSeat);
		
		if(foundAt >= 0) {
			return seats.get(foundAt).reserveSeat();
		}
		System.out.println("Seat does not exist: " + number);
		return false;
	}
	/*
	 * cancel existing booking
	 */
	public boolean cancelBooking(String number) {
		Seat cancelSeat = new Seat(number);
		int foundAt = Collections.binarySearch(seats, cancelSeat);
		
		if(foundAt >= 0) {
			return seats.get(foundAt).cancelSeat();
		}
		System.out.println("Seat does not exist: " + number);
		return false;
	}

	/*
	 * returns all seats
	 */
	public void getSeats() {
		for(Seat seat: seats) {
			System.out.println(seat.getSeatNr() + " -- " + seat.showStatus());
		}
	}
	/*
	 * returns Name of seatContainerObject
	 */
	public String getName() {
		return name;
	}
	/*
	 * returns Number of seats
	 */
	public int getSeatsNr() {
		return seats.size();
	}
	
	/*
	 * inner class
	 */
	private class Seat implements Comparable<Seat>{
		private String seatNr;
		private boolean reserved = false;
		
		Seat(String seatNr){
			this.seatNr = seatNr;
		}
		
		public String getSeatNr() {
			return seatNr;
		}
		
		public String showStatus() {
			if(this.reserved) {
				return "booked";
			}
			else {
				return "free";
			}
		}
		/*
		 * checks if seat is still available, if yes: sets seat state to reserved
		 * returns boolean, true if booking is successfull
		 */
		public boolean reserveSeat() {
			if(!this.reserved) {
				this.reserved = true;
				System.out.println("successfully booked seat: " + this.getSeatNr());
				return true;
			}
			else {
				System.out.println("Seat already reserved: " + this.getSeatNr());
				return false;
			}
		}
		/*
		 * changes seat state, reserved to false
		 * returns boolean, true if cancelation is successfull
		 */
		public boolean cancelSeat() {
			if(this.reserved) {
				this.reserved = false;
				System.out.println("successfully canceled booking for seat: " + this.getSeatNr());
				return true;
			}
			System.out.println("No prior booking for seat: " + this.getSeatNr());
			return false;
		}
		
		@Override
		public int compareTo(Seat newSeat) {
			return this.getSeatNr().compareToIgnoreCase(newSeat.getSeatNr());
		}
		
	}
}

