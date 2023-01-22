class Train {
    private String trainNumber;
    private int capacity;
    private int numPassengers;

    public Train(String trainNumber, int capacity) {
        this.trainNumber = trainNumber;
        this.capacity = capacity;
        this.numPassengers = 0;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public boolean registerPassenger(Passenger passenger) {
        if (numPassengers < capacity) {
            numPassengers++;
            return true;
        } else {
            return false;
        }
    }
}