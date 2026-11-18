package hospital;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class HospitalManagementSystem {

    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===== Hospital Management System =====");

        loadData();

        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    displayAllPatients();
                    break;
                case 3:
                    searchPatient();
                    break;
                case 4:
                    removePatient();
                    break;
                case 5:
                    addDoctor();
                    break;
                case 6:
                    displayAllDoctors();
                    break;
                case 7:
                    createAppointment();
                    break;
                case 8:
                    displayAllAppointments();
                    break;
                case 9:
                    saveData();
                    break;
                case 10:
                    System.out.println("Saving data before exit...");
                    saveData();
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 10.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("1. Add Patient");
        System.out.println("2. Display Patients");
        System.out.println("3. Search Patient");
        System.out.println("4. Remove Patient");
        System.out.println("5. Add Doctor");
        System.out.println("6. Display Doctors");
        System.out.println("7. Create Appointment");
        System.out.println("8. Display Appointments");
        System.out.println("9. Save Data");
        System.out.println("10. Exit");
        System.out.println("=====================================");
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // ===================== عمليات المريض (Patient Operations) =====================
    private static void addPatient() {
        System.out.println("\n--- Add New Patient ---");

        String id;
        while (true) {
            id = getStringInput("Enter Patient ID: ");
            if (findPatientById(id) != null) {
                System.out.println("Error: Patient ID already exists! Please enter a unique ID.");
            } else {
                break;
            }
        }

        String name = getStringInput("Enter Patient Name: ");

        int age = 0;
        while (true) {
            try {
                age = getIntInput("Enter Age: ");
                if (age < 0) {
                    System.out.println("Invalid age! Age cannot be negative. Please try again.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number for age.");
                scanner.nextLine();
            }
        }

        String gender = getStringInput("Enter Gender (Male/Female): ");
        String disease = getStringInput("Enter Disease: ");

        int roomNumber = 0;
        while (true) {
            try {
                roomNumber = getIntInput("Enter Room Number: ");
                if (roomNumber < 0) {
                    System.out.println("Invalid room number! Cannot be negative.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number for room.");
                scanner.nextLine();
            }
        }

        Patient newPatient = new Patient(id, name, age, gender, disease, roomNumber);
        patients.add(newPatient);

        System.out.println(" Patient added successfully!");
    }

    private static void displayAllPatients() {
        System.out.println("\n--- All Patients ---");

        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }


        for (Patient p : patients) {
            Person personRef = p; 
            personRef.displayInfo(); 
            System.out.println("------------------------");
        }
    }

    private static Patient findPatientById(String id) {
        for (Patient p : patients) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
    // ===================== عمليات الطبيب (Doctor Operations) =====================

    private static void addDoctor() {
        System.out.println("\n--- Add New Doctor ---");

        String id;
        while (true) {
            id = getStringInput("Enter Doctor ID: ");
            if (findDoctorById(id) != null) {
                System.out.println("Error: Doctor ID already exists! Please enter a unique ID.");
            } else {
                break;
            }
        }

        String name = getStringInput("Enter Doctor Name: ");

        int age = 0;
        while (true) {
            age = getIntInput("Enter Age: ");
            if (age < 0) {
                System.out.println("Invalid age! Age cannot be negative. Please try again.");
            } else {
                break;
            }
        }

        String gender = getStringInput("Enter Gender (Male/Female): ");
        String specialization = getStringInput("Enter Specialization: ");

        double salary = 0;
        while (true) {
            try {
                System.out.print("Enter Salary: ");
                salary = scanner.nextDouble();
                scanner.nextLine();
                if (salary < 0) {
                    System.out.println("Invalid salary! Salary cannot be negative. Please try again.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number for salary.");
                scanner.nextLine();
            }
        }

        Doctor newDoctor = new Doctor(id, name, age, gender, specialization, salary);
        doctors.add(newDoctor);

        System.out.println(" Doctor added successfully!");
    }

    // 10. عرض جميع الأطباء (تطبيق تعدد الأشكال Polymorphism)
    private static void displayAllDoctors() {
        System.out.println("\n--- All Doctors ---");

        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }

        for (Doctor d : doctors) {
            Person personRef = d; 
            personRef.displayInfo(); 
            System.out.println("------------------------");
        }
    }

    private static Doctor findDoctorById(String id) {
        for (Doctor d : doctors) {
            if (d.getId().equals(id)) {
                return d;
            }
        }
        return null;
    }
    // ===================== البحث والحذف للمرضى =====================

    private static void searchPatient() {
        System.out.println("\n--- Search Patient ---");
        String id = getStringInput("Enter Patient ID to search: ");

        Patient found = findPatientById(id);
        if (found != null) {
            System.out.println("\n Patient found:");
            found.displayInfo(); 
        } else {
            System.out.println(" Patient not found.");
        }
    }

    private static void removePatient() {
        System.out.println("\n--- Remove Patient ---");
        String id = getStringInput("Enter Patient ID to remove: ");

        Patient found = findPatientById(id);
        if (found != null) {
            patients.remove(found);
            System.out.println(" Patient removed successfully!");
        } else {
            System.out.println(" Patient not found. Deletion cancelled.");
        }
    }
    // ===================== عمليات المواعيد (Appointment Operations) =====================

    private static Appointment findAppointmentById(String id) {
        for (Appointment app : appointments) {
            if (app.getAppointmentID().equals(id)) {
                return app;
            }
        }
        return null;
    }

    private static void createAppointment() {
        System.out.println("\n--- Create New Appointment ---");

        String appointmentID;
        while (true) {
            appointmentID = getStringInput("Enter Appointment ID: ");
            if (findAppointmentById(appointmentID) != null) {
                System.out.println("Error: Appointment ID already exists! Please enter a unique ID.");
            } else {
                break;
            }
        }

        Patient patient = null;
        while (true) {
            String patientId = getStringInput("Enter Patient ID: ");
            patient = findPatientById(patientId);
            if (patient == null) {
                System.out.println(" Error: Patient not found! Please enter an existing Patient ID.");
            } else {
                break; 
            }
        }

        Doctor doctor = null;
        while (true) {
            String doctorId = getStringInput("Enter Doctor ID: ");
            doctor = findDoctorById(doctorId);
            if (doctor == null) {
                System.out.println(" Error: Doctor not found! Please enter an existing Doctor ID.");
            } else {
                break;
            }
        }

        String date = getStringInput("Enter Date (e.g., 20/06/2026): ");
        String time = getStringInput("Enter Time (e.g., 3:00 PM): ");

        Appointment newAppointment = new Appointment(appointmentID, patient, doctor, date, time);
        appointments.add(newAppointment);

        System.out.println(" Appointment created successfully!");
    }

    private static void displayAllAppointments() {
        System.out.println("\n--- All Appointments ---");

        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        for (Appointment app : appointments) {
            app.displayAppointment();
            System.out.println("------------------------");
        }
    }
    // ===================== عمليات إدارة الملفات (File Handling Operations) =====================

    private static void saveData() {
        System.out.println("\n--- Saving Data... ---");
        try {
            PrintWriter patientWriter = new PrintWriter(new FileWriter("patients.txt"));
            for (Patient p : patients) {

                patientWriter.println(p.getId() + "," + p.getName() + "," + p.getAge() + ","
                        + p.getGender() + "," + p.getDisease() + "," + p.getRoomNumber());
            }
            patientWriter.close();

            PrintWriter doctorWriter = new PrintWriter(new FileWriter("doctors.txt"));
            for (Doctor d : doctors) {
                doctorWriter.println(d.getId() + "," + d.getName() + "," + d.getAge() + ","
                        + d.getGender() + "," + d.getSpecialization() + "," + d.getSalary());
            }
            doctorWriter.close();

            PrintWriter appWriter = new PrintWriter(new FileWriter("appointments.txt"));
            for (Appointment app : appointments) {
                appWriter.println(app.getAppointmentID() + "," + app.getPatient().getId() + ","
                        + app.getDoctor().getId() + "," + app.getDate() + "," + app.getTime());
            }
            appWriter.close();

            System.out.println(" All data saved successfully to files (patients.txt, doctors.txt, appointments.txt)!");
        } catch (IOException e) {
            System.out.println(" Error while saving data: " + e.getMessage());
        }
    }

    private static void loadData() {

        File patientFile = new File("patients.txt");
        if (patientFile.exists()) {
            try (Scanner reader = new Scanner(patientFile)) {
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    if (line.trim().isEmpty()) {
                        continue;
                    }
                    String[] data = line.split(",");
                    if (data.length == 6) {
                        patients.add(new Patient(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], Integer.parseInt(data[5])));
                    }
                }
            } catch (Exception e) {
                System.out.println("Warning: Could not load patients data.");
            }
        }

        File doctorFile = new File("doctors.txt");
        if (doctorFile.exists()) {
            try (Scanner reader = new Scanner(doctorFile)) {
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    if (line.trim().isEmpty()) {
                        continue;
                    }
                    String[] data = line.split(",");
                    if (data.length == 6) {
                        doctors.add(new Doctor(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], Double.parseDouble(data[5])));
                    }
                }
            } catch (Exception e) {
                System.out.println("Warning: Could not load doctors data.");
            }
        }

        File appFile = new File("appointments.txt");
        if (appFile.exists()) {
            try (Scanner reader = new Scanner(appFile)) {
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    if (line.trim().isEmpty()) {
                        continue;
                    }
                    String[] data = line.split(",");
                    if (data.length == 5) {
                        String appID = data[0];
                        String patientId = data[1];
                        String doctorId = data[2];
                        String date = data[3];
                        String time = data[4];

                        Patient p = findPatientById(patientId);
                        Doctor d = findDoctorById(doctorId);

                        if (p != null && d != null) {
                            appointments.add(new Appointment(appID, p, d, date, time));
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Warning: Could not load appointments data.");
            }
        }
    }

}
