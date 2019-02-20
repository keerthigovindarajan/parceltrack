package com.demo.parceltrackcheck.parceltrackingcheck.parceltrackcheck.dao;

import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.ParcelRegisterSucessfulmodel;
import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.ParcelRegistermodel;
import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.Parceltrackcheckmodel;

import java.util.List;

/**
 * Created by mahithaa.
 */
public interface ParcelcheckDAO {
    public abstract boolean  loginValidation(String username,String password);
    public abstract int createProfile(Parceltrackcheckmodel parceltrackcheckmodel);
    public abstract Object createNewParcel(ParcelRegistermodel parcelRegistermodel);

    public abstract List<ParcelRegisterSucessfulmodel> getProfiles();
    public abstract List<ParcelRegisterSucessfulmodel> getTrackStatus(String track_id);

}
