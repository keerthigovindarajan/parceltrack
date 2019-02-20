package com.demo.parceltrackcheck.parceltrackingcheck.demo.parceltrackingcheck;

import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.ParcelRegistermodel;
import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.Parceltrackcheckmodel;
import com.demo.parceltrackcheck.parceltrackingcheck.parceltrackcheck.dao.ParcelcheckDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * Created by mahithaa.
 */
@RestController
public class Parcelcheckcontroller {
    @Autowired
    ParcelcheckDAO parcelcheckDAO;
    @RequestMapping(path="/check",method = RequestMethod.GET )
    @ResponseBody
    public boolean  loginValidation(@RequestParam("username") String username,@RequestParam("password") String password)
    {
        boolean username1=parcelcheckDAO.method9(username,password);

        return username1;

    }
    @RequestMapping(path="/profiles",method = RequestMethod.POST)
    public ResponseEntity<Object> createProfile(@RequestBody  Parceltrackcheckmodel parceltrackcheckmodel)
    {
        parcelcheckDAO.createProfile(parceltrackcheckmodel);
        return new ResponseEntity<>("Profile is created successfully", HttpStatus.OK);
    }
    @RequestMapping(path="/registerparcel",method = RequestMethod.POST)
    public ResponseEntity<Object> createNewParcel(@RequestBody ParcelRegistermodel parcelRegistermodel)
    {
        parcelcheckDAO.createNewParcel(parcelRegistermodel);
        return new ResponseEntity<>("Registered new parcel successfully", HttpStatus.OK);
    }




}
