package m2y.centennial.healthowl.appointment;

/**
 * Created by yesha on 2016-12-15.
 */

public class appointmentDetailsModel {

    public String getEmergency_state() {
        return emergency_state;
    }

    public void setEmergency_state(String emergency_state) {
        this.emergency_state = emergency_state;
    }

    public String getAreaOfPain() {
        return areaOfPain;
    }

    public void setAreaOfPain(String areaOfPain) {
        this.areaOfPain = areaOfPain;
    }

    public String getLevelOfPain() {
        return levelOfPain;
    }

    public void setLevelOfPain(String levelOfPain) {
        this.levelOfPain = levelOfPain;
    }

    public String getDeptSelected() {
        return deptSelected;
    }

    public void setDeptSelected(String deptSelected) {
        this.deptSelected = deptSelected;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    private String emergency_state, areaOfPain, levelOfPain, deptSelected, temp, blood, heart, reason;

}

