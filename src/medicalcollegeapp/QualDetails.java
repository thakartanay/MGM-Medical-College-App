/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalcollegeapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

/**
 *
 * @author Tanay Thakar
 */
public class QualDetails {

    private final SimpleStringProperty Qualification;
    private TextField College;
    private TextField University;
    private TextField PassingYear;
    private TextField RegNo;
    private TextField State;

    public QualDetails(String Qual, String Coll, String Univ, String PsngYr, String Reg, String St) {
        this.Qualification = new SimpleStringProperty(Qual);
        this.College = new TextField(Coll);
        this.University = new TextField(Univ);
        this.PassingYear = new TextField(PsngYr);
        this.RegNo = new TextField(Reg);
        this.State = new TextField(St);
    }

    public String getQualification() {
        return Qualification.get();
    }

    public void setQualification(String Quali) {
        Qualification.set(Quali);
    }

    public TextField getCollege() {
        return College;
    }

    public void setCollege(TextField College) {
        this.College = College;
    }

    public TextField getUniversity() {
        return University;
    }

    public void setUniversity(TextField University) {
        this.University = University;
    }

    public TextField getPassingYear() {
        return PassingYear;
    }

    public void setPassingYear(TextField PassingYear) {
        this.PassingYear = PassingYear;
    }

    public TextField getRegNo() {
        return RegNo;
    }

    public void setRegNo(TextField RegNo) {
        this.RegNo = RegNo;
    }

    public TextField getState() {
        return State;
    }

    public void setState(TextField State) {
        this.State = State;
    }

}
