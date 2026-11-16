/**
 * Stores information shared by all health professionals in the clinic.
 */
public abstract class HealthProfessional {
    private int id;
    private String name;
    private String[] availableDays;

    /**
     * Creates a health professional with shared details.
     *
     * @param id numeric professional identifier
     * @param name professional name
     * @param availableDays days on which the professional works
     */
    protected HealthProfessional(int id, String name, String[] availableDays) {
        this.id = id;
        this.name = name;
        this.availableDays = copyDays(availableDays);
    }

    private String[] copyDays(String[] days) {
        String[] copiedDays = new String[days.length];
        for (int i = 0; i < days.length; i++) {
            copiedDays[i] = days[i];
        }
        return copiedDays;
    }

    /** @return the numeric identifier */
    public int getId() {
        return id;
    }

    /** @return the professional's name */
    public String getName() {
        return name;
    }

    /** @return the available days as readable text */
    public String getAvailableDays() {
        String result = "";
        for (int i = 0; i < availableDays.length; i++) {
            if (i > 0) {
                result += ", ";
            }
            result += availableDays[i];
        }
        return result;
    }

    /** @return the professional type shown in the details */
    public abstract String getProfessionalType();

    /** @return type-specific details */
    protected abstract String getSpecificDetails();

    /**
     * Returns the shared and type-specific details in readable form.
     *
     * @return professional details
     */
    @Override
    public String toString() {
        return getProfessionalType() + " | ID: " + id + " | Name: " + name
                + " | Works: " + getAvailableDays() + " | " + getSpecificDetails();
    }

    /**
     * Professionals are considered equal when their IDs match.
     *
     * @param object object being compared
     * @return true when the other professional has the same ID
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof HealthProfessional)) {
            return false;
        }
        HealthProfessional other = (HealthProfessional) object;
        return id == other.id;
    }

    /** @return a hash code based on the professional ID */
    @Override
    public int hashCode() {
        return id;
    }
}
