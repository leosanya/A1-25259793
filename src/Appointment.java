/** Represents one appointment for the current day. */
public class Appointment implements Comparable<Appointment> {
    private Patient patient;
    private HealthProfessional professional;
    private int hour;
    private int minute;

    private Appointment(Patient patient, HealthProfessional professional, int hour, int minute) {
        this.patient = patient;
        this.professional = professional;
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Creates an appointment only when all required details and the time slot are valid.
     *
     * @return a valid appointment, or null when the supplied details are invalid
     */
    public static Appointment createIfValid(Patient patient, HealthProfessional professional,
            int hour, int minute) {
        if (patient == null || professional == null) {
            return null;
        }
        if (patient.getName() == null || patient.getName().trim().length() == 0) {
            return null;
        }
        if (patient.getMobilePhone() == null || patient.getMobilePhone().trim().length() == 0) {
            return null;
        }
        if (hour < 8 || hour >= 16 || (minute != 0 && minute != 30)) {
            return null;
        }
        return new Appointment(patient, professional, hour, minute);
    }

    /** @return the patient linked to the appointment */
    public Patient getPatient() {
        return patient;
    }

    /** @return the professional linked to the appointment */
    public HealthProfessional getProfessional() {
        return professional;
    }

    /** @return the appointment hour */
    public int getHour() {
        return hour;
    }

    /** @return the appointment minute */
    public int getMinute() {
        return minute;
    }

    /** @return the time in HH:mm format */
    public String getTime() {
        String hourText = hour < 10 ? "0" + hour : "" + hour;
        String minuteText = minute < 10 ? "0" + minute : "" + minute;
        return hourText + ":" + minuteText;
    }

    /**
     * Compares appointments by time. Appointments at the same time compare equally.
     *
     * @param other appointment being compared
     * @return a negative, zero, or positive number according to the time order
     */
    @Override
    public int compareTo(Appointment other) {
        if (hour < other.hour) {
            return -1;
        }
        if (hour > other.hour) {
            return 1;
        }
        if (minute < other.minute) {
            return -1;
        }
        if (minute > other.minute) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return getTime() + " | Patient: " + patient + " | Professional: " + professional;
    }
}
