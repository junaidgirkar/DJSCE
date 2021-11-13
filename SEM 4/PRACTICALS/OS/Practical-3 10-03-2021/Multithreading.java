class TicketCounter {
    private int availableSeats;
    
    TicketCounter(int availableSeats){
        this.availableSeats = availableSeats;
    }
    
	public synchronized void bookTicket(String pname, int numberOfSeats) {
	    System.out.println("Welcome to XYZ bus Service");
	    System.out.println("No. of seats available : "+availableSeats);
		if ((availableSeats >= numberOfSeats) && (numberOfSeats > 0)) {
			System.out.println("CONGRATS Mr./Ms. "+pname+" The number of seats requested ("+numberOfSeats +" seats) are BOOKED");
			availableSeats = availableSeats- numberOfSeats;
		} 
		else System.out.println("SORRY Mr./Ms. "+pname+" The number of seats requested ("+numberOfSeats +" seats) are not available");
		System.out.println();
	}
}

class TicketBookingThread extends Thread {

	private TicketCounter ticketCounter;
	private String passengerName;
	private int noOfSeatsToBook;

	public TicketBookingThread(TicketCounter ticketCounter,String passengerName, int noOfSeatsToBook) {
		this.ticketCounter = ticketCounter;
		this.passengerName = passengerName;
		this.noOfSeatsToBook = noOfSeatsToBook;
	}
	
	public void run() {
		ticketCounter.bookTicket(passengerName, noOfSeatsToBook);
	}
}

public class Multithreading {

    public static void main(String[] args) {
        TicketCounter ticketCounter = new TicketCounter(6);
        TicketBookingThread t1 = new TicketBookingThread(ticketCounter,"Person1",2);
        TicketBookingThread t2 = new TicketBookingThread(ticketCounter,"Person2",3);
        TicketBookingThread t3 = new TicketBookingThread(ticketCounter, "Person3", 2);
        
        t1.start();
        t2.start();
        t3.start();
    }
}