
package doctor;



import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Doctor Class 
class Doctor {
    private String name;
    private String specialization;
    private List<String> availableSlots;

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
        this.availableSlots = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void addAvailableSlot(String slot) {
        availableSlots.add(slot);
    }

    public List<String> getAvailableSlots() {
        return availableSlots;
    }

    // Method to display availability 
    public void displayAvailability() {
        System.out.println("Doctor's available slots:");
        for (String slot : availableSlots) {
            System.out.println(slot);
        }
    }

 
    public void saveToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("doctors.txt", true));
        writer.write(name + "," + specialization + "\n");
        writer.close();
    }
}

// General Practitioner Class
class GeneralPractitioner extends Doctor {

    public GeneralPractitioner(String name) {
        super(name, "General Practitioner");
    }

    // Override displayAvailability
    @Override
    public void displayAvailability() {
        System.out.println("General Practitioner " + getName() + " available for walk-ins.");
        super.displayAvailability();
    }
}

// Specialist Class 
class Specialist extends Doctor {
    public Specialist(String name, String specialization) {
        super(name, specialization);
    }

    // Override displayAvailability 
    @Override
    public void displayAvailability() {
        System.out.println("Specialist " + getName() + " (Specialization: " + getSpecialization() + ") available by appointment.");
        super.displayAvailability();
    }
}

// Patient Class
class Patient {
    private String name;
    private String contact;

    public Patient(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public void saveToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("patients.txt", true));
        writer.write(name + "," + contact + "\n");
        writer.close();
    }
}

class Appointment {
    private Doctor doctor;
    private Patient patient;
    private String timeSlot;

    public Appointment(Doctor doctor, Patient patient, String timeSlot) {
        this.doctor = doctor;
        this.patient = patient;
        this.timeSlot = timeSlot;
    }

    
    public void saveToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("appointments.txt", true));
        writer.write("Patient: " + patient.getName() + ", Doctor: " + doctor.getName() + ", Time Slot: " + timeSlot + "\n");
        writer.close();
    }
}

public class ClinicManagementSystem {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Sample doctors
        GeneralPractitioner gp1 = new GeneralPractitioner("Dr. Shahin");
        gp1.addAvailableSlot("9:00 AM");
        gp1.addAvailableSlot("10:00 AM");

        Specialist sp1 = new Specialist("Dr. Jahid", "Dermatology");
        sp1.addAvailableSlot("11:00 AM");
        sp1.addAvailableSlot("1:00 PM");

        // Register doctors to file
        gp1.saveToFile();
        sp1.saveToFile();

        // Display doctors
        System.out.println("Available Doctors:");
        gp1.displayAvailability();
        sp1.displayAvailability();


// Register a patient
        System.out.println("Registering a new patient:");
        System.out.print("Enter Patient Name: ");
        String patientName = scanner.nextLine();
        System.out.print("Enter Patient Contact: ");
        String patientContact = scanner.nextLine();
        Patient patient = new Patient(patientName, patientContact);
        patient.saveToFile();

        // Book an appointment
        System.out.println("Booking an appointment...");
        System.out.print("Enter Doctor Name (e.g., Dr. Shahin): ");
        String doctorName = scanner.nextLine();
        System.out.print("Enter Appointment Time Slot: ");
        String timeSlot = scanner.nextLine();

        Doctor chosenDoctor = doctorName.equals("Dr. Shahin") ? gp1 : sp1;
        Appointment appointment = new Appointment(chosenDoctor, patient, timeSlot);
        appointment.saveToFile();

        System.out.println("Appointment successfully booked!");
    }
}


