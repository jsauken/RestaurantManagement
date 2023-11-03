package com.example.restapi.model;

public class TableAssignment {
    private int tableNum;
    private int waiterId;

    public TableAssignment(int tableNum, int waiterId) {
        this.tableNum = tableNum;
        this.waiterId = waiterId;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public int getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(int waiterId) {
        this.waiterId = waiterId;
    }
}
