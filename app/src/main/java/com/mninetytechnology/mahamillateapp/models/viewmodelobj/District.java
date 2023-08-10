package com.mninetytechnology.mahamillateapp.models.viewmodelobj;

import java.util.Date;

public class District {
    public String _id;
    public String dvncode;
    public String divname11;
    public String divname11_marathi;
    public String district_code_2011;
    public String district;
    public String district_marathi;
    public String taluka_code;
    public String taluka;
    public String taluka_marathi;
    public String village_code_2011;
    public String village;
    public String remarks;
    public Date createdAt;
    public Date updatedAt;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDvncode() {
        return dvncode;
    }

    public void setDvncode(String dvncode) {
        this.dvncode = dvncode;
    }

    public String getDivname11() {
        return divname11;
    }

    public void setDivname11(String divname11) {
        this.divname11 = divname11;
    }

    public String getDivname11_marathi() {
        return divname11_marathi;
    }

    public void setDivname11_marathi(String divname11_marathi) {
        this.divname11_marathi = divname11_marathi;
    }

    public String getDistrict_code_2011() {
        return district_code_2011;
    }

    public void setDistrict_code_2011(String district_code_2011) {
        this.district_code_2011 = district_code_2011;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict_marathi() {
        return district_marathi;
    }

    public void setDistrict_marathi(String district_marathi) {
        this.district_marathi = district_marathi;
    }

    public String getTaluka_code() {
        return taluka_code;
    }

    public void setTaluka_code(String taluka_code) {
        this.taluka_code = taluka_code;
    }

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getTaluka_marathi() {
        return taluka_marathi;
    }

    public void setTaluka_marathi(String taluka_marathi) {
        this.taluka_marathi = taluka_marathi;
    }

    public String getVillage_code_2011() {
        return village_code_2011;
    }

    public void setVillage_code_2011(String village_code_2011) {
        this.village_code_2011 = village_code_2011;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    @Override
    public String toString() {
        return district;
    }
}
