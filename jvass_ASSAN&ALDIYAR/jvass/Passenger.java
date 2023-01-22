import java.sql.*;
import java.util.Scanner;

class Passenger {
    public int id;
    public String name;
    public int age;
    public String trainNumber;

    public Passenger(int id, String name, int age, String trainNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.trainNumber = trainNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getTrainNumber() {
        return trainNumber;
    }
}