package m2y.centennial.healthowl.patient;

/**
 * Created by margot on 2016-11-21.
 */

public class patientModel {

    private String mFName;
    private String mLName;
    private String mPhone;
    private String mAddress;
    private String mOhip;

    private String mGender;
    private String mMonth;
    private String mDay;
    private String mYear;
    private String mDob;


    public String getFName() {
        return mFName;
    }

    public void setFName(String FName) {
        mFName = FName;
    }

    public String getLName() {
        return mLName;
    }

    public void setLName(String LName) {
        mLName = LName;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getOhip() {
        return mOhip;
    }

    public void setOhip(String ohip) {
        mOhip = ohip;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getMonth() {
        return mMonth;
    }

    public void setMonth(String month) {
        mMonth = month;
    }

    public String getDay() {
        return mDay;
    }

    public void setDay(String day) {
        mDay = day;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public String getDob() {
        return mDob;
    }

    public void setDob(String dob) {
        mDob = dob;
    }
}
