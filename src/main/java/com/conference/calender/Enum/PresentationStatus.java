package com.conference.calender.Enum;

public enum PresentationStatus {

    ACTIVATED(1),
    DEACTIVATED(2),
    SUSPENDED(3);

    int statusId;

    private PresentationStatus(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }


    public boolean isPresentable(PresentationStatus status) {
            if (status == PresentationStatus.ACTIVATED) {
                return true;
            }
            return false;
        }

}
