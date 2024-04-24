package sample;

public class Ticket {
    private int id;
    private int userId;
    private String flightNumber;
    private String passengerName;
    private String passengerSurname;
    private String seatNumber;
    private double price;

    // Конструктор с параметрами
    public Ticket(int userId, String flightNumber, String passengerName, String passengerSurname, String seatNumber, double price) {
        this.userId = userId;
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
        this.passengerSurname = passengerSurname;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public Ticket(int id, int userId, String flightNumber, String passengerName, String passengerSurname, String seatNumber, double price) {
        this.id = id;
        this.userId = userId;
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
        this.passengerSurname = passengerSurname;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    // Пустой конструктор
    public Ticket() { }

    // Геттеры
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getPassengerSurname() {
        return passengerSurname;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return price;
    }

    // Сеттеры
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public void setPassengerSurname(String passengerSurname) {
        this.passengerSurname = passengerSurname;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
