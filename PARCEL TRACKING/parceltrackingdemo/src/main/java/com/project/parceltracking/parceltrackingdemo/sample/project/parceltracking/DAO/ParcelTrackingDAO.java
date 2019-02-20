package com.project.parceltracking.parceltrackingdemo.sample.project.parceltracking.DAO;

import com.project.parceltracking.parceltrackingdemo.com.project.parceltracking.model.ParcelTrackingModel;

import java.util.List;

public interface ParcelTrackingDAO {
    public abstract List<ParcelTrackingModel> getProfiles();
}
