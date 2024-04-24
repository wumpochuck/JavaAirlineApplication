package sample;

public class Flight {
    private int id;
    private String number;
    private String flightFrom;
    private String flightTo;
    private String departureTime;
    private String arrivalTime;
    private int countOfSeats;
    private String status;

    public Flight(int id, String number, String flightFrom, String flightTo, String departureTime, String arrivalTime, int countOfSeats, String status) {
        this.id = id;
        this.number = number;
        this.flightFrom = flightFrom;
        this.flightTo = flightTo;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.countOfSeats = countOfSeats;
        this.status = status;
    }

    public Flight(String number, String flightFrom, String flightTo, String departureTime, String arrivalTime, int countOfSeats, String status) {
        this.number = number;
        this.flightFrom = flightFrom;
        this.flightTo = flightTo;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.countOfSeats = countOfSeats;
        this.status = status;
    }

    // Пустой конструктор
    public Flight() { }


    // Геттеры
    public String getNumber() {
        return number;
    }

    public String getFlightFrom() {
        return flightFrom;
    }

    public String getFlightTo() {
        return flightTo;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getCountOfSeats() {
        return countOfSeats;
    }

    public String getStatus() {
        return status;
    }

    public int getId(){
        return id;
    }

    // Сеттеры
    public void setNumber(String number) {
        this.number = number;
    }

    public void setFlightFrom(String flightFrom) {
        this.flightFrom = flightFrom;
    }

    public void setFlightTo(String flightTo) {
        this.flightTo = flightTo;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setCountOfSeats(int countOfSeats) {
        this.countOfSeats = countOfSeats;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id){
        this.id = id;
    }
}
