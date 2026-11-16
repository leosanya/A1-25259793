/** Stores the basic details needed to identify a patient for an appointment. */
public class Patient {
    private String name;
    private String mobilePhone;

    /** Creates a patient. */
    public Patient(String name, String mobilePhone) {
        this.name = name;
        this.mobilePhone = mobilePhone;
    }

    /** @return the patient's name */
    public String getName() {
        return name;
    }

    /** @return the patient's mobile phone number */
    public String getMobilePhone() {
        return mobilePhone;
    }

    @Override
    public String toString() {
        return name + " (mobile: " + mobilePhone + ")";
    }
}
