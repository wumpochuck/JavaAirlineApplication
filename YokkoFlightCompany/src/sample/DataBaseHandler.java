package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler {
    Connection connection;

    public DataBaseHandler(){
        try {
            this.connection = getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String urlConnection = "jdbc:mysql://127.0.0.1:3306/FlightCompany";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(urlConnection, "root", "a32gDF%b23");
        return connection;
    }

    // Создание таблицы Users
    public void createUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "login VARCHAR(50) NOT NULL," +
                    "password VARCHAR(50) NOT NULL," +
                    "name VARCHAR(50)," +
                    "surname VARCHAR(50)," +
                    "money DOUBLE DEFAULT 0," +
                    "role VARCHAR(50) NOT NULL" +
                    ")";
            statement.executeUpdate(createTableQuery);
            System.out.println("createUsersTable(): Table created/consist!");
        }
    }

    // USERS - Добавление человека в таблицу
    public void addUser(User user) throws SQLException {
        String addUserQuery = "INSERT INTO users (login, password, name, surname, role) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(addUserQuery)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.executeUpdate();
            System.out.println("addUser(): User added successfully!");
        }
    }

    // USERS - Метод для извлечения всех пользователей из таблицы
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int money = resultSet.getInt("money");
                String role = resultSet.getString("role");
                User user = new User(id, login, password, name, surname, money, role);
                users.add(user);
            }
        }
        return users;
    }

    public void updateUserById(User user) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE users SET login = ?, password = ?, name = ?, surname = ?, money = ?, role = ? WHERE id = ?")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setDouble(5,user.getMoney());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.setInt(7, user.getUserId());
            preparedStatement.executeUpdate();
            System.out.println("User with ID '" + user.getUserId() + "' has been updated successfully.");
        }
    }

    public void deleteUserByLogin(String login) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE login = ?")) {
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
            System.out.println("User with login '" + login + "' has been deleted successfully.");
        }
    }

    // USERS - Получение человека по логину
    public User getUserByLogin(String login) throws SQLException {
        User user = null;
        String getUserQuery = "SELECT * FROM users WHERE login = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getUserQuery)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getDouble("money"),
                            resultSet.getString("role")
                    );
                }
            }
        }
        return user;
    }

    // USERS - Обновление информации о пользователе по логину
    public void updateUserByLogin(User newuser) throws SQLException {
        String updateUserQuery = "UPDATE users SET login = ?, password = ?, name = ?, surname = ?, money = ?, role = ? WHERE login = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateUserQuery)) {
            preparedStatement.setString(1,newuser.getLogin());
            preparedStatement.setString(2, newuser.getPassword());
            preparedStatement.setString(3, newuser.getName());
            preparedStatement.setString(4, newuser.getSurname());
            preparedStatement.setDouble(5, newuser.getMoney());
            preparedStatement.setString(6, newuser.getRole());
            preparedStatement.setString(7, newuser.getLogin());
            preparedStatement.executeUpdate();
            System.out.println("updateUserByLogin(): User updated successfully!");
        }
    }

    // Создание таблица flights
    public void createFlightTable() {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS flights (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "number VARCHAR(50)," +
                    "flightFrom VARCHAR(50)," +
                    "flightTo VARCHAR(50)," +
                    "departureTime VARCHAR(50)," +
                    "arrivalTime VARCHAR(50)," +
                    "countOfSeats INT," +
                    "status VARCHAR(50)" +
                    ")";
            statement.executeUpdate(sql);
            System.out.println("Table 'flights' created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // FLIGHTS - Вытащить всю таблицу
    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM flights";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Flight flight = new Flight(
                        resultSet.getInt("id"),
                        resultSet.getString("number"),
                        resultSet.getString("flightFrom"),
                        resultSet.getString("flightTo"),
                        resultSet.getString("departureTime"),
                        resultSet.getString("arrivalTime"),
                        resultSet.getInt("countOfSeats"),
                        resultSet.getString("status")
                );
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    // FLIGHTS - Добавление рейса в таблицу
    public void addFlightToTable(Flight flight) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO flights (number, flightFrom, flightTo, departureTime, arrivalTime, countOfSeats, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, flight.getNumber());
            preparedStatement.setString(2, flight.getFlightFrom());
            preparedStatement.setString(3, flight.getFlightTo());
            preparedStatement.setString(4, flight.getDepartureTime());
            preparedStatement.setString(5, flight.getArrivalTime());
            preparedStatement.setInt(6, flight.getCountOfSeats());
            preparedStatement.setString(7, flight.getStatus());
            preparedStatement.executeUpdate();
            System.out.println("Flight added successfully to the 'flights' table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // FLIGHTS - Удаление рейса из таблицы по номеру
    public void deleteFlightByNumber(String flightNumber) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM flights WHERE number = ?")) {
            preparedStatement.setString(1, flightNumber);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Flight with number " + flightNumber + " deleted successfully from the 'flights' table");
            } else {
                System.out.println("No flight found with number " + flightNumber + " in the 'flights' table");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // FLIGHTS - Обновление данных рейса в таблице
    public void updateFlight(Flight flight) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE flights SET number = ?, flightFrom = ?, flightTo = ?, departureTime = ?, arrivalTime = ?, countOfSeats = ?, status = ? WHERE id = ?")) {
            preparedStatement.setString(1, flight.getNumber());
            preparedStatement.setString(2, flight.getFlightFrom());
            preparedStatement.setString(3, flight.getFlightTo());
            preparedStatement.setString(4, flight.getDepartureTime());
            preparedStatement.setString(5, flight.getArrivalTime());
            preparedStatement.setInt(6,flight.getCountOfSeats());
            preparedStatement.setString(7, flight.getStatus());
            preparedStatement.setInt(8, flight.getId());
            preparedStatement.executeUpdate();
            System.out.println("Flight with number " + flight.getNumber() + " updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // FLIGHTS - Метод для получения времени отправления по номеру рейса
    public String getDepartureTimeByFlightNumber(String flightNumber) {
        String departureTime = null;
        String query = "SELECT departureTime FROM flights WHERE number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, flightNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                departureTime = resultSet.getString("departureTime");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departureTime;
    }

    // FLIGHTS - Метод для получения времени прибытия по номеру рейса
    public String getArrivalTimeByFlightNumber(String flightNumber) {
        String arrivalTime = null;
        String query = "SELECT arrivalTime FROM flights WHERE number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, flightNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                arrivalTime = resultSet.getString("arrivalTime");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrivalTime;
    }

    // FLIGHTS - Метод для получения информации о рейсе по его номеру
    public Flight getFlightByFlightNumber(String flightNumber) {
        String query = "SELECT * FROM flights WHERE number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, flightNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String flightFrom = resultSet.getString("flightFrom");
                    String flightTo = resultSet.getString("flightTo");
                    String departureTime = resultSet.getString("departureTime");
                    String arrivalTime = resultSet.getString("arrivalTime");
                    int countOfSeats = resultSet.getInt("countOfSeats");
                    String status = resultSet.getString("status");
                    Flight flight = new Flight(id,flightNumber, flightFrom, flightTo , departureTime, arrivalTime, countOfSeats, status);
                    return flight;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Создание таблицы Tickets
    public void createTicketsTable() {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS tickets (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "userId INT," +
                    "flightNumber VARCHAR(50)," +
                    "passengerName VARCHAR(50)," +
                    "passengerSurname VARCHAR(50)," +
                    "seatNumber VARCHAR(50)," +
                    "price DOUBLE" +
                    ")";
            statement.executeUpdate(sql);
            System.out.println("Table 'tickets' created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TICKETS - Метод для получения списка мест по номеру рейса
    public List<String> getSeatNumbersByFlightNumber(String flightNumber) {
        List<String> seatNumbers = new ArrayList<>();
        String query = "SELECT seatNumber FROM tickets WHERE flightNumber = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, flightNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String seatNumber = resultSet.getString("seatNumber");
                seatNumbers.add(seatNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seatNumbers;
    }

    // TICKETS - Метод для добавления билета в таблицу
    public void addTicket(Ticket newTicket) {
        String query = "INSERT INTO tickets (userId, flightNumber, passengerName, passengerSurname, seatNumber, price) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, newTicket.getUserId());
            preparedStatement.setString(2, newTicket.getFlightNumber());
            preparedStatement.setString(3, newTicket.getPassengerName());
            preparedStatement.setString(4, newTicket.getPassengerSurname());
            preparedStatement.setString(5, newTicket.getSeatNumber());
            preparedStatement.setDouble(6, newTicket.getPrice());
            preparedStatement.executeUpdate();
            System.out.println("Ticket added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TICKETS - Метод для получения всех билетов из таблицы
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM tickets";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String flightNumber = resultSet.getString("flightNumber");
                String passengerName = resultSet.getString("passengerName");
                String passengerSurname = resultSet.getString("passengerSurname");
                String seatNumber = resultSet.getString("seatNumber");
                double price = resultSet.getDouble("price");
                Ticket ticket = new Ticket(id, userId, flightNumber, passengerName, passengerSurname, seatNumber, price);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    // TICKETS - Метод для получения всех билетов из таблицы по ID
    public List<Ticket> getAllTicketsByUserId(String userID) {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM tickets WHERE userId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
             preparedStatement.setString(1, userID);
             try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int userId = resultSet.getInt("userId");
                    String flightNumber = resultSet.getString("flightNumber");
                    String passengerName = resultSet.getString("passengerName");
                    String passengerSurname = resultSet.getString("passengerSurname");
                    String seatNumber = resultSet.getString("seatNumber");
                    double price = resultSet.getDouble("price");
                    Ticket ticket = new Ticket(id, userId, flightNumber, passengerName, passengerSurname, seatNumber, price);
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    // TICKETS - Метод для получения количества занятых мест на рейсе по его номеру
    public int getOccupiedSeatsByFlightNumber(String flightNumber) {
        int occupiedSeats = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM tickets WHERE flightNumber = ?")) {
            preparedStatement.setString(1, flightNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    occupiedSeats = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return occupiedSeats;
    }

    // FLIGHTS - Метод для получения общего количества мест на рейсе по его номеру
    public int getTotalSeatsByFlightNumber(String flightNumber) {
        int totalSeats = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT countOfSeats FROM flights WHERE number = ?")) {
            preparedStatement.setString(1, flightNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    totalSeats = resultSet.getInt("countOfSeats");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalSeats;
    }
}
