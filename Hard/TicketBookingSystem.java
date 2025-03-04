import java.util.*;

class TicketBookingSystem {
    private final int totalSeats;
    private final boolean[] seats;  

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.seats = new boolean[totalSeats];
    }
    public synchronized boolean bookSeat(int seatNumber, String user) {
        if (seatNumber < 0 || seatNumber >= totalSeats) {
            System.out.println(user + " tried to book an invalid seat: " + seatNumber);
            return false;
        }
        if (seats[seatNumber]) {
            System.out.println("❌ Seat " + (seatNumber + 1) + " is already booked! (" + user + ")");
            return false;
        } else {
            seats[seatNumber] = true;
            System.out.println("✅ Seat " + (seatNumber + 1) + " successfully booked by " + user);
            return true;
        }
    }
}

class UserThread extends Thread {
    private final TicketBookingSystem bookingSystem;
    private final int seatNumber;

    public UserThread(TicketBookingSystem bookingSystem, int seatNumber, String userName, int priority) {
        super(userName);
        this.bookingSystem = bookingSystem;
        this.seatNumber = seatNumber;
        this.setPriority(priority);  // Set thread priority
    }

    @Override
    public void run() {
        bookingSystem.bookSeat(seatNumber, getName());
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        int totalSeats = 10;  // Total number of seats
        TicketBookingSystem system = new TicketBookingSystem(totalSeats);

        Thread vipUser = new UserThread(system, 2, "VIP_User", Thread.MAX_PRIORITY);
        Thread normalUser1 = new UserThread(system, 2, "Normal_User1", Thread.NORM_PRIORITY);
        Thread normalUser2 = new UserThread(system, 5, "Normal_User2", Thread.NORM_PRIORITY);
        Thread normalUser3 = new UserThread(system, 5, "Normal_User3", Thread.NORM_PRIORITY);
        Thread vipUser2 = new UserThread(system, 7, "VIP_User2", Thread.MAX_PRIORITY);

        vipUser.start();
        normalUser1.start();
        normalUser2.start();
        normalUser3.start();
        vipUser2.start();
    }
}
