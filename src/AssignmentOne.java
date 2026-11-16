import java.util.ArrayList;

/** Runs a console demonstration of the appointment system. */
public class AssignmentOne {
    /** Starts the scripted assessment demonstration. */
    public static void main(String[] args) {
        GeneralPractitioner gpOne = new GeneralPractitioner(1001, "Alex Chen",
                new String[] {"Monday", "Wednesday", "Friday"}, "family care");
        GeneralPractitioner gpTwo = new GeneralPractitioner(1002, "Mia Patel",
                new String[] {"Monday", "Tuesday", "Thursday"}, "community health");
        GeneralPractitioner gpThree = new GeneralPractitioner(1003, "Noah Kim",
                new String[] {"Tuesday", "Wednesday", "Friday"}, "preventive care");
        Specialist specialist = new Specialist(2001, "Olivia Wang",
                new String[] {"Monday", "Thursday"}, "cardiology");
        Nurse nurse = new Nurse(3001, "Ethan Liu",
                new String[] {"Monday", "Wednesday", "Friday"}, "registered nurse");
        Dietitian dietitian = new Dietitian(4001, "Grace Zhou",
                new String[] {"Tuesday", "Thursday"}, "sports nutrition");

        // Process different professional types through their shared parent type.
        ArrayList<HealthProfessional> professionals = new ArrayList<HealthProfessional>();
        professionals.add(gpOne);
        professionals.add(gpTwo);
        professionals.add(gpThree);
        professionals.add(specialist);
        professionals.add(nurse);
        professionals.add(dietitian);

        System.out.println("=== Health Professionals ===");
        for (int i = 0; i < professionals.size(); i++) {
            System.out.println(professionals.get(i));
        }

        System.out.println("\n=== Professional Actions ===");
        System.out.println(gpOne.prescribeMedication("Medication A"));
        System.out.println(specialist.provideConsultation("heart health"));
        System.out.println(nurse.takeBloodSample());
        System.out.println(dietitian.provideDietAdvice("balanced meals"));

        AppointmentManager manager = new AppointmentManager();
        Patient patientOne = new Patient("Taylor Brown", "0400 000 101");
        Patient patientTwo = new Patient("Jordan Smith", "0400 000 102");
        Patient patientThree = new Patient("Casey Nguyen", "0400 000 103");
        Patient patientFour = new Patient("Riley Jones", "0400 000 104");
        Patient patientFive = new Patient("Avery Wilson", "0400 000 105");
        Patient patientSix = new Patient("Morgan Davis", "0400 000 106");

        System.out.println("\n=== Add Appointments ===");
        System.out.println(manager.addAppointment(patientOne, gpOne, 9, 0));
        System.out.println(manager.addAppointment(patientTwo, specialist, 9, 0));
        System.out.println(manager.addAppointment(patientThree, nurse, 8, 30));
        System.out.println(manager.addAppointment(patientFour, gpTwo, 11, 30));
        System.out.println(manager.addAppointment(patientFive, dietitian, 13, 0));
        System.out.println(manager.addAppointment(patientSix, gpThree, 10, 0));

        // Demonstrate a duplicate booking and an invalid time.
        System.out.println(manager.addAppointment(new Patient("Sam Lee", "0400 000 107"), gpOne, 9, 0));
        System.out.println(manager.addAppointment(new Patient("Jamie Park", "0400 000 108"), gpTwo, 16, 0));
        System.out.println(manager.addAppointment(new Patient("", "0400 000 109"), gpTwo, 12, 0));

        printAppointments("All Appointments", manager.getAllAppointments());
        printAppointments("Sorted by Time", manager.getSortedAppointments());
        printAppointments("GP 1001 Appointments Sorted by Time",
                manager.getSortedAppointmentsByProfessionalId(1001));
        printAppointments("Patient 0400 000 104 Appointments Sorted by Time",
                manager.getSortedAppointmentsByPatientPhone("0400 000 104"));

        System.out.println("\n=== Cancellation ===");
        System.out.println(manager.cancelAppointment(1001, 9, 0));
        System.out.println(manager.cancelAppointmentByPatientPhone("0400 000 105"));
        System.out.println(manager.cancelAppointment(9999, 14, 0));
        System.out.println(manager.cancelAppointmentByPatientPhone("0400 999 999"));

        printAppointments("Appointments After Cancellation", manager.getSortedAppointments());
    }

    private static void printAppointments(String heading, ArrayList<Appointment> appointments) {
        System.out.println("\n=== " + heading + " ===");
        if (appointments.size() == 0) {
            System.out.println("No appointments found.");
        } else {
            for (int i = 0; i < appointments.size(); i++) {
                System.out.println(appointments.get(i));
            }
        }
    }
}
