package com.Beans;

import java.sql.Date;

public class ApplicationApproval {
    private int id;
    private int applicationId;
    private int approverId;
    private Date approvalDate;
    private String approverType;
    private boolean approved;
    private boolean inDatabase;

    public boolean isInDatabase() { return inDatabase; }

    public void setInDatabase(boolean inDatabase) { this.inDatabase = inDatabase; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getApplicationId() { return applicationId; }

    public void setApplicationId(int applicationId) { this.applicationId = applicationId; }

    public int getApproverId() { return approverId; }

    public void setApproverId(int approverId) { this.approverId = approverId; }

    public Date getApprovalDate() { return approvalDate; }

    public void setApprovalDate(Date approvalDate) { this.approvalDate = approvalDate; }

    public String getApproverType() { return approverType; }

    public void setApproverType(String approverType) { this.approverType = approverType; }

    public boolean isApproved() { return approved; }

    public void setApproved(boolean approved) { this.approved = approved; }
}
