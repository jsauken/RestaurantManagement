package com.example.restapi.model;

public class Table {
    int tableNum;
    String status;

    public Table(int tableNum, String status) {
        this.tableNum = tableNum;
        this.status = status;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
