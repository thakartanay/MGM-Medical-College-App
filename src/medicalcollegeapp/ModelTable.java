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
public class ModelTable {
    String Sr, first, middle, last, ling, birthdate, mail, phone, post, branch, appt;

    public ModelTable(String Sr, String first, String middle, String last, String ling, String birthdate, String mail, String phone, String post, String branch, String appt) {
        this.Sr = Sr;
        this.first = first;
        this.middle = middle;
        this.last = last;
        this.ling = ling;
        this.birthdate = birthdate;
        this.mail = mail;
        this.phone = phone;
        this.post = post;
        this.branch = branch;
        this.appt = appt;
    }

    public String getSr() {
        return Sr;
    }

    public void setSr(String Sr) {
        this.Sr = Sr;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLing() {
        return ling;
    }

    public void setLing(String ling) {
        this.ling = ling;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAppt() {
        return appt;
    }

    public void setAppt(String appt) {
        this.appt = appt;
    }
    
    
}
