package hospital;

public class Patient extends Person {

    private String disease;
    private int roomNumber;

    public Patient(String id, String name, int age, String gender, String disease, int roomNumber) {
        super(id, name, age, gender);
        this.disease = disease;
        this.roomNumber = roomNumber;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Disease: " + disease);
        System.out.println("Room Number: " + roomNumber);
    }
}
