package m2y.centennial.healthowl.appointment;

/**
 * Created by yesha on 2016-11-23.
 */

public class appointmentModel {

    private String mAppointmentName;
    private String mSetDate;
    private String mSetTime;
    private  String mComments;

    public String getOhip() {
        return mOhip;
    }

    public void setOhip(String ohip) {
        mOhip = ohip;
    }

    private String mOhip;

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
