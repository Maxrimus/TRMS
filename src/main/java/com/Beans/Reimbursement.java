package com.Beans;

public class Reimbursement {
    private int id;
    private double amountAwarded;
    private double amountPending;
    private boolean inDatabase;

    public boolean isInDatabase() { return inDatabase; }

    public void setInDatabase(boolean inDatabase) { this.inDatabase = inDatabase; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmountAwarded() {
        return amountAwarded;
    }

    public void setAmountAwarded(double amountAwarded) {
        this.amountAwarded = amountAwarded;
    }

    public double getAmountPending() {
        return amountPending;
    }

    public void setAmountPending(double amountPending) {
        this.amountPending = amountPending;
    }

    public Reimbursement(){}
}
