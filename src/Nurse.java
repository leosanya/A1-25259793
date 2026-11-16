/** Represents a nurse. */
public class Nurse extends HealthProfessional {
    private String qualificationLevel;

    /** Creates a nurse. */
    public Nurse(int id, String name, String[] availableDays, String qualificationLevel) {
        super(id, name, availableDays);
        this.qualificationLevel = qualificationLevel;
    }

    @Override
    public String getProfessionalType() {
        return "Nurse";
    }

    @Override
    protected String getSpecificDetails() {
        return "Qualification: " + qualificationLevel;
    }

    /** Describes taking a blood sample for the console demonstration. */
    public String takeBloodSample() {
        return getName() + " took a blood sample.";
    }
}
