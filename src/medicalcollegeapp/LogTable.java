/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalcollegeapp;

/**
 *
 * @author Tanay Thakar
 */
public class LogTable {
    String col_Sr, col_LI, col_LO , col_AP;

    public LogTable(String col_Sr, String col_LI, String col_LO , String col_AP) {
        this.col_Sr = col_Sr;
        this.col_LI = col_LI;
        this.col_LO = col_LO;
        this.col_AP = col_AP;
    }

    public String getCol_Sr() {
        return col_Sr;
    }

    public void setCol_Sr(String col_Sr) {
        this.col_Sr = col_Sr;
    }

    public String getCol_LI() {
        return col_LI;
    }

    public void setCol_LI(String col_LI) {
        this.col_LI = col_LI;
    }

    public String getCol_LO() {
        return col_LO;
    }

    public void setCol_LO(String col_LO) {
        this.col_LO = col_LO;
    }

    public String getCol_AP() {
        return col_AP;
    }

    public void setCol_AP(String col_AP) {
        this.col_AP = col_AP;
    }

}
