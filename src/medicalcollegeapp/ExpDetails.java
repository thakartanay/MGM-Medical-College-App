/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalcollegeapp;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author Tanay Thakar
 */
public class ExpDetails {

    private final SimpleStringProperty Designation;
    private TextField Department;
    private TextField Institute;
    private DatePicker FromDate;
    private DatePicker ToDate;
    private TextField ExpYears;

    public ExpDetails(String Desg, String Dept, String Inst, LocalDate FrmDt, LocalDate ToDt, String ExpYr) {
        this.Designation = new SimpleStringProperty(Desg);
        this.Department = new TextField(Dept);
        this.Institute = new TextField(Inst);
        this.FromDate = new DatePicker(FrmDt);
        this.ToDate = new DatePicker(ToDt);
        this.ExpYears = new TextField(ExpYr);
    }

    public String getDesignation() {
        return Designation.get();
    }

    public void setDesignation(String Desig) {
        Designation.set(Desig);
    }

    public TextField getDepartment() {
        return Department;
    }

    public void setDepartment(TextField Department) {
        this.Department = Department;
    }

    public TextField getInstitute() {
        return Institute;
    }

    public void setInstitute(TextField Institution) {
        this.Institute = Institution;
    }

    public DatePicker getFromDate() {
        return FromDate;
    }

    public void setFromDate(DatePicker FromDate) {
        this.FromDate = FromDate;
    }

    public DatePicker getToDate() {
        return ToDate;
    }

    public void setToDate(DatePicker ToDate) {
        this.ToDate = ToDate;
    }

    public TextField getExpYears() {
        return ExpYears;
    }

    public void setExpYears(TextField ExpYears) {
        this.ExpYears = ExpYears;
    }

}
