import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/traindbass";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;
        Statement stmt = null;

        try {
            // Connect to database
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();

            // Get train information from user
            System.out.print("Enter train number: ");
            String trainNumber = scanner.nextLine();
            System.out.print("Enter train capacity: ");
            int capacity = Integer.parseInt(scanner.nextLine());

            // Create new train and add to database
            Train train = new Train(trainNumber, capacity);
            String sql = "INSERT INTO trains (train_number, capacity) VALUES ('" + trainNumber + "', " + capacity + ")";
            stmt.executeUpdate(sql);

            // Register passengers for train
            boolean registered = false;
            while (!registered) {
                System.out.print("Enter passenger name: ");
                String name = scanner.nextLine();
                System.out.print("Enter passenger age: ");
                int age = Integer.parseInt(scanner.nextLine());
                //create new passenger and add to database
                Passenger passenger = new Passenger(0, name, age, trainNumber);
                sql = "INSERT INTO passengers (name, age, train_number) VALUES ('" + name + "', " + age + ", '" + trainNumber + "')";
                stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    passenger.id = rs.getInt(1);
                }
                //register passenger for train
                registered = train.registerPassenger(passenger);
                if (!registered) {
                    System.out.println("Train is full. Please choose another train.");
                } else {
                    System.out.println("Passenger registered successfully. Passenger ID: " + passenger.getId());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}