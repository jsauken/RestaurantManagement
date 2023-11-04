package com.example.restapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@jakarta.persistence.Table(name = "table")
@AllArgsConstructor
@NoArgsConstructor
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int tableNum;

    @Column(name = "status", nullable = false)
    String status;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt;
    @Column(name = "modifiedAt")
    private Date modifiedAt;

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
