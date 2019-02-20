package com.project.parceltracking.parceltrackingdemo.project.parceltracking.controller;

import com.project.parceltracking.parceltrackingdemo.sample.project.parceltracking.DAO.ParcelTrackingDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParcelTrackingController {
    @Autowired
    ParcelTrackingDAO parcelTrackingDAO;
    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public ResponseEntity<Object> getProfiles()
    {
        return new ResponseEntity<>(parcelTrackingDAO.getProfiles(), HttpStatus.OK);
    }

}

