/** Represents a dietitian. */
public class Dietitian extends HealthProfessional {
    private String nutritionFocus;

    /** Creates a dietitian. */
    public Dietitian(int id, String name, String[] availableDays, String nutritionFocus) {
        super(id, name, availableDays);
        this.nutritionFocus = nutritionFocus;
    }

    @Override
    public String getProfessionalType() {
        return "Dietitian";
    }

    @Override
    protected String getSpecificDetails() {
        return "Nutrition focus: " + nutritionFocus;
    }

    /** Describes nutrition advice for the console demonstration. */
    public String provideDietAdvice(String goal) {
        return getName() + " provided advice about " + goal + ".";
    }
}
