package com.project.parceltracking.parceltrackingdemo.sample.project.parceltracking.DAO;

import com.project.parceltracking.parceltrackingdemo.com.project.parceltracking.model.ParcelTrackingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
@Repository
public class ParcelTrackingDAOImplementation implements ParcelTrackingDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<ParcelTrackingModel> getProfiles() {
        List<ParcelTrackingModel> profileList = new ArrayList<>();
            Collection<Map<String,Object>> rows=null;
            rows=jdbcTemplate.queryForList("select username,password,id from login");
            rows.stream().map((row)-> {
                ParcelTrackingModel parcelTrackingModel = new ParcelTrackingModel();
                parcelTrackingModel.setUsername((String) row.get("USERNAME"));
                parcelTrackingModel.setPassword((String) row.get("PASSWORD"));
                parcelTrackingModel.setId(String.valueOf(row.get("ID")));
                return parcelTrackingModel;
            }).forEach((ss)->{
                //CricketersProfileModel ss;
                profileList.add(ss);
            });

            return profileList;
    }
}
