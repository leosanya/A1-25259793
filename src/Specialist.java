/** Represents a specialist health professional. */
public class Specialist extends HealthProfessional {
    private String specialty;

    /** Creates a specialist. */
    public Specialist(int id, String name, String[] availableDays, String specialty) {
        super(id, name, availableDays);
        this.specialty = specialty;
    }

    @Override
    public String getProfessionalType() {
        return "Specialist";
    }

    @Override
    protected String getSpecificDetails() {
        return "Specialty: " + specialty;
    }

    /** Describes a specialist consultation for the console demonstration. */
    public String provideConsultation(String topic) {
        return getName() + " provided a " + specialty + " consultation about " + topic + ".";
    }
}
