package com.mninetytechnology.mahamillateapp.models.viewmodelobj;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Swapna Thakur on 3/1/2022.
 */
public class UserLoginObject {
    private String _id;
    private int organization_points;
    private int referral_earn_points;
    private int firstLevel;
    private int secondLevel;
    private int thirdLevel;
    private String name;
    private String email;
    private String phone_number;
    private String state;
    private String division;
    private String district;
    private String taluka;
    private String village;
    @SerializedName("class")
    private String myclass;
    private String referral_code;
    private double referral_points;
    private int referral_userId;
    private String organization_id;
    private String password;
    private Date createdAt;
    private Date updatedAt;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getOrganization_points() {
        return organization_points;
    }

    public void setOrganization_points(int organization_points) {
        this.organization_points = organization_points;
    }

    public int getReferral_earn_points() {
        return referral_earn_points;
    }

    public void setReferral_earn_points(int referral_earn_points) {
        this.referral_earn_points = referral_earn_points;
    }

    public int getFirstLevel() {
        return firstLevel;
    }

    public void setFirstLevel(int firstLevel) {
        this.firstLevel = firstLevel;
    }

    public int getSecondLevel() {
        return secondLevel;
    }

    public void setSecondLevel(int secondLevel) {
        this.secondLevel = secondLevel;
    }

    public int getThirdLevel() {
        return thirdLevel;
    }

    public void setThirdLevel(int thirdLevel) {
        this.thirdLevel = thirdLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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

    public String getMyclass() {
        return myclass;
    }

    public void setMyclass(String myclass) {
        this.myclass = myclass;
    }

    public String getReferral_code() {
        return referral_code;
    }

    public void setReferral_code(String referral_code) {
        this.referral_code = referral_code;
    }

    public double getReferral_points() {
        return referral_points;
    }

    public void setReferral_points(double referral_points) {
        this.referral_points = referral_points;
    }

    public int getReferral_userId() {
        return referral_userId;
    }

    public void setReferral_userId(int referral_userId) {
        this.referral_userId = referral_userId;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUserLevel() {
        if (firstLevel != 0 && secondLevel == 0 && thirdLevel == 0) {
            return "Level1";
        } else if (firstLevel != 0 && secondLevel != 0 && thirdLevel == 0) {
            return "Level2";
        } else if (firstLevel != 0 && secondLevel != 0 && thirdLevel != 0) {
            return "Level3";
        } else if (firstLevel != 0) {
            return "Level1";
        } else if (secondLevel != 0) {
            return "Level2";
        } else if (thirdLevel != 0) {
            return "Level3";
        } else {
            return "Level1";
        }
    }

    public String getScore() {
        if (firstLevel != 0 && secondLevel == 0 && thirdLevel == 0) {
            return String.valueOf(firstLevel);
        } else if (firstLevel != 0 && secondLevel != 0 && thirdLevel == 0) {
            return String.valueOf(secondLevel);
        } else if (firstLevel != 0 && secondLevel != 0) {
            return String.valueOf(thirdLevel);
        } else if (firstLevel != 0) {
            return String.valueOf(firstLevel);
        } else if (secondLevel != 0) {
            return String.valueOf(secondLevel);
        } else if (thirdLevel != 0) {
            return String.valueOf(thirdLevel);
        } else {
            return String.valueOf(firstLevel);
        }
    }
}
