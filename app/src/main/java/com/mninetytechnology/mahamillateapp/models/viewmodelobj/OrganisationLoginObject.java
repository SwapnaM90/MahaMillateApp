package com.mninetytechnology.mahamillateapp.models.viewmodelobj;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Swapna Thakur on 3/1/2022.
 */
public class OrganisationLoginObject {
    private String _id;
    private String name;
    private String reg_number;
    private String contact_person;
    private String password;
    private boolean isVerified;
    private String contact_person_email;
    private String contact_person_phone_number;
    private String state;
    private String division;
    private String district;
    private String taluka;
    private String village;
    private Date createdAt;
    private Date updatedAt;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReg_number() {
        return reg_number;
    }

    public void setReg_number(String reg_number) {
        this.reg_number = reg_number;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getContact_person_email() {
        return contact_person_email;
    }

    public void setContact_person_email(String contact_person_email) {
        this.contact_person_email = contact_person_email;
    }

    public String getContact_person_phone_number() {
        return contact_person_phone_number;
    }

    public void setContact_person_phone_number(String contact_person_phone_number) {
        this.contact_person_phone_number = contact_person_phone_number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
