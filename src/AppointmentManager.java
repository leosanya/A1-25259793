import java.util.ArrayList;

/** Manages the clinic's appointments for the current day. */
public class AppointmentManager {
    private ArrayList<Appointment> appointments;

    /** Creates an empty appointment collection. */
    public AppointmentManager() {
        appointments = new ArrayList<Appointment>();
    }

    /**
     * Checks a booking and adds it when the information and time are valid.
     *
     * @return a success or warning message
     */
    public String addAppointment(Patient patient, HealthProfessional professional, int hour, int minute) {
        if (!hasRequiredDetails(patient, professional)) {
            return "Warning: patient details and a health professional are required.";
        }
        if (!isValidTimeSlot(hour, minute)) {
            return "Warning: choose a 30-minute slot from 08:00 to 15:30.";
        }

        for (int i = 0; i < appointments.size(); i++) {
            Appointment existing = appointments.get(i);
            if (existing.getProfessional().equals(professional)
                    && existing.getHour() == hour
                    && existing.getMinute() == minute) {
                return "Warning: this professional is already booked at " + existing.getTime() + ".";
            }
        }

        Appointment appointment = new Appointment(patient, professional, hour, minute);
        appointments.add(appointment);
        return "Appointment added for " + patient.getName() + " at " + appointment.getTime() + ".";
    }

    private boolean hasRequiredDetails(Patient patient, HealthProfessional professional) {
        if (patient == null || professional == null) {
            return false;
        }
        if (patient.getName() == null || patient.getName().trim().length() == 0) {
            return false;
        }
        if (patient.getMobilePhone() == null || patient.getMobilePhone().trim().length() == 0) {
            return false;
        }
        return true;
    }

    private boolean isValidTimeSlot(int hour, int minute) {
        return hour >= 8 && hour < 16 && (minute == 0 || minute == 30);
    }

    /** @return a copy of all appointments */
    public ArrayList<Appointment> getAllAppointments() {
        ArrayList<Appointment> result = new ArrayList<Appointment>();
        for (int i = 0; i < appointments.size(); i++) {
            result.add(appointments.get(i));
        }
        return result;
    }

    /** @return appointments for the given professional ID */
    public ArrayList<Appointment> getAppointmentsByProfessionalId(int professionalId) {
        ArrayList<Appointment> result = new ArrayList<Appointment>();
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getProfessional().getId() == professionalId) {
                result.add(appointment);
            }
        }
        return result;
    }

    /** @return appointments for the given patient phone number */
    public ArrayList<Appointment> getAppointmentsByPatientPhone(String mobilePhone) {
        ArrayList<Appointment> result = new ArrayList<Appointment>();
        if (mobilePhone == null) {
            return result;
        }
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (mobilePhone.equals(appointment.getPatient().getMobilePhone())) {
                result.add(appointment);
            }
        }
        return result;
    }

    /** @return all appointments sorted from earliest to latest */
    public ArrayList<Appointment> getSortedAppointments() {
        return sortAppointments(getAllAppointments());
    }

    /** @return appointments for a professional sorted by time */
    public ArrayList<Appointment> getSortedAppointmentsByProfessionalId(int professionalId) {
        return sortAppointments(getAppointmentsByProfessionalId(professionalId));
    }

    /** @return appointments for a phone number sorted by time */
    public ArrayList<Appointment> getSortedAppointmentsByPatientPhone(String mobilePhone) {
        return sortAppointments(getAppointmentsByPatientPhone(mobilePhone));
    }

    private ArrayList<Appointment> sortAppointments(ArrayList<Appointment> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    Appointment earlier = list.get(j + 1);
                    list.set(j + 1, list.get(j));
                    list.set(j, earlier);
                }
            }
        }
        return list;
    }

    /** Cancels an appointment by professional ID and time. */
    public String cancelAppointment(int professionalId, int hour, int minute) {
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getProfessional().getId() == professionalId
                    && appointment.getHour() == hour
                    && appointment.getMinute() == minute) {
                appointments.remove(i);
                return "Appointment cancelled for professional " + professionalId + " at "
                        + appointment.getTime() + ".";
            }
        }
        return "Warning: no appointment found for professional " + professionalId + " at "
                + formatTime(hour, minute) + ".";
    }

    /**
     * Cancels by patient phone number when exactly one appointment matches.
     * If several appointments match, the caller can use professional ID and time instead.
     */
    public String cancelAppointmentByPatientPhone(String mobilePhone) {
        if (mobilePhone == null) {
            return "Warning: a patient phone number is required.";
        }

        int matchingIndex = -1;
        int matchingCount = 0;
        for (int i = 0; i < appointments.size(); i++) {
            if (mobilePhone.equals(appointments.get(i).getPatient().getMobilePhone())) {
                matchingIndex = i;
                matchingCount++;
            }
        }

        if (matchingCount == 0) {
            return "Warning: no appointment found for phone " + mobilePhone + ".";
        }
        if (matchingCount > 1) {
            return "Warning: multiple appointments match this phone; cancel by professional ID and time.";
        }

        Appointment appointment = appointments.remove(matchingIndex);
        return "Appointment cancelled for " + appointment.getPatient().getName() + " at "
                + appointment.getTime() + ".";
    }

    private String formatTime(int hour, int minute) {
        String hourText = hour < 10 ? "0" + hour : "" + hour;
        String minuteText = minute < 10 ? "0" + minute : "" + minute;
        return hourText + ":" + minuteText;
    }
}
