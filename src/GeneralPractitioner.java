/** Represents a general practitioner. */
public class GeneralPractitioner extends HealthProfessional {
    private String practiceFocus;

    /** Creates a general practitioner. */
    public GeneralPractitioner(int id, String name, String[] availableDays, String practiceFocus) {
        super(id, name, availableDays);
        this.practiceFocus = practiceFocus;
    }

    @Override
    public String getProfessionalType() {
        return "General Practitioner";
    }

    @Override
    protected String getSpecificDetails() {
        return "Practice focus: " + practiceFocus;
    }

    /** Describes a prescription action for the console demonstration. */
    public String prescribeMedication(String medication) {
        return getName() + " prescribed " + medication + ".";
    }
}
