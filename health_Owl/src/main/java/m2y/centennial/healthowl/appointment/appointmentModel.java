package m2y.centennial.healthowl.appointment;

/**
 * M2Y
 */

public class appointmentModel {

    private String mAppointmentName;
    private String mSetDate;
    private String mSetTime;
    private  String mComments;
    private String mOhip;
    private String mAreaOfPain;
    private int mLevelOfPain;
    private String mDeptSelected;
    //private String mInsurance;
    private String mReason;

    public String getmAreaOfPain() {
        return mAreaOfPain;
    }

    public void setmAreaOfPain(String mAreaOfPain) {
        this.mAreaOfPain = mAreaOfPain;
    }

    public int getmLevelOfPain() {
        return mLevelOfPain;
    }

    public void setmLevelOfPain(int mLevelOfPain) {
        this.mLevelOfPain = mLevelOfPain;
    }

    public String getmDeptSelected() {
        return mDeptSelected;
    }

    public void setmDeptSelected(String mDeptSelected) {
        this.mDeptSelected = mDeptSelected;
    }

    public String getmReason() {
        return mReason;
    }

    public void setmReason(String mReason) {
        this.mReason = mReason;
    }

    public String getmHeart() {
        return mHeart;
    }

    public void setmHeart(String mHeart) {
        this.mHeart = mHeart;
    }

    public String getmBlood() {
        return mBlood;
    }

    public void setmBlood(String mBlood) {
        this.mBlood = mBlood;
    }

    public String getMtemp() {
        return mtemp;
    }

    public void setMtemp(String mtemp) {
        this.mtemp = mtemp;
    }

    private String mHeart;

    public String getmOhip() {
        return mOhip;
    }

    public void setmOhip(String mOhip) {
        this.mOhip = mOhip;
    }

    private String mBlood;
    private String mtemp;


    public String getmAppointmentName() {
        return mAppointmentName;
    }

    public void setmAppointmentName(String mAppointmentName) {
        this.mAppointmentName = mAppointmentName;
    }


    public String getmComments() {
        return mComments;
    }

    public void setmComments(String mComments) {
        this.mComments = mComments;
    }

    public String getmSetDate() {
        return mSetDate;
    }

    public void setmSetDate(String mSetDate) {
        this.mSetDate = mSetDate;
    }

    public String getmSetTime() {
        return mSetTime;
    }

    public void setmSetTime(String mSetTime) {
        this.mSetTime = mSetTime;
    }

}
