package m2y.centennial.healthowl.patient;

/**
 * Created by margot on 2016-11-21.
 */

public class patientModel {
    private String mPatientName;
    private String mCountry;
    private String mTwitter;


    public String getPatientName() {
        return mPatientName;
    }

    public void setPatientName(String patientName) {
        mPatientName = patientName;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getTwitter() {
        return mTwitter;
    }

    public void setTwitter(String twitter) {
        mTwitter = twitter;
    }
}
