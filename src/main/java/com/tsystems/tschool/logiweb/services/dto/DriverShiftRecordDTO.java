package com.tsystems.tschool.logiweb.services.dto;

public class DriverShiftRecordDTO {

    private String shiftBeggined;
    private String shiftEnded;

    public DriverShiftRecordDTO(String shiftBeggined, String shiftEnded) {
        this.shiftBeggined = shiftBeggined;
        this.shiftEnded = shiftEnded;
    }

    public String getShiftBeggined() {
        return shiftBeggined;
    }

    public void setShiftBeggined(String shiftBeggined) {
        this.shiftBeggined = shiftBeggined;
    }

    public String getShiftEnded() {
        return shiftEnded;
    }

    public void setShiftEnded(String shiftEnded) {
        this.shiftEnded = shiftEnded;
    }
}
