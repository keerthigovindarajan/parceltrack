package com.demo.parceltrackcheck.parceltrackingcheck.parceltrackcheck.dao;

import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.ParcelRegistermodel;
import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.Parceltrackcheckmodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Random;

/**
 * Created by mahithaa.
 */
@Repository
public class ParcelcheckDAOImplementation implements ParcelcheckDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //LOGIN VALIDATION
    public boolean  loginValidation(String username,String password) {
        try {
            Parceltrackcheckmodel username2 = (Parceltrackcheckmodel) jdbcTemplate.queryForObject("select * from login where username=?",
                    new Object[]{username}, new BeanPropertyRowMapper(Parceltrackcheckmodel.class));
            Parceltrackcheckmodel encrypass= (Parceltrackcheckmodel)jdbcTemplate.queryForObject("select password from login where username=?",
                    new Object[]{username},new BeanPropertyRowMapper(Parceltrackcheckmodel.class));
            String salt = "1p/RCrsiRPZxFSDgg2aT6GPjX9SbHEEoIkbz4Zhv4bA=";
            boolean passwordMatch = PasswordUtils.verifyUserPassword(password,encrypass.getPassword(), salt);

            if (passwordMatch) {
                return true;
            }
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
        return false;
    }

    //USER REGISTRATION
    public int createProfile(Parceltrackcheckmodel parceltrackcheckmodel) {
        KeyHolder keyHolder=new GeneratedKeyHolder();
        String user= parceltrackcheckmodel.getUsername();
        String pass= usercheck(parceltrackcheckmodel.getPassword());
        //System.out.println(pass);
        jdbcTemplate.update((Connection connection)->{

            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("insert into login(username,password)values(?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,parceltrackcheckmodel.getUsername());
            preparedStatement.setString(2,pass);

            return preparedStatement;
        },keyHolder);
        return keyHolder.getKey().intValue();
    }

    //PASSWORD ENCRYPTION
    public String usercheck(String pass)
    {

        String salt = "1p/RCrsiRPZxFSDgg2aT6GPjX9SbHEEoIkbz4Zhv4bA=";
        String mySecurePassword = PasswordUtils.generateSecurePassword(pass, salt);
        return mySecurePassword;
    }



    //REGISTRATION FOR NEW PARCEL
    public Object createNewParcel(ParcelRegistermodel parcelRegistermodel) {

        //TRACK ID GENERATION
        int m = (int)Math.pow(10,10-1);
        int b= m  + new Random().nextInt(9 * m);
        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection)->{

            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("insert into register_parcel(track_id,name,source_address,source_city,destination_address,destination_city,phone_number,kilometer,weight,priority,amount)values(?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(2, parcelRegistermodel.getName());
            preparedStatement.setString(3, parcelRegistermodel.getSource_address());
            preparedStatement.setString(4, parcelRegistermodel.getSource_city());
            preparedStatement.setString(5, parcelRegistermodel.getDestination_address());


            //APPENDING DESTINATION CITY
            String str=parcelRegistermodel.getDestination_city();
            preparedStatement.setString(6, str);
            String substr=str.substring(0,4);
            String a=substr+String.valueOf(b);
            preparedStatement.setString(1, a);
            preparedStatement.setString(7, parcelRegistermodel.getPhone_number());
            preparedStatement.setInt( 8, parcelRegistermodel.getKilometer());
            preparedStatement.setInt(9, parcelRegistermodel.getWeight());
            preparedStatement.setBoolean(10, parcelRegistermodel.isPriority());

            //PRICE CALCULATION
            int weight=parcelRegistermodel.getWeight();
            int distance =parcelRegistermodel.getKilometer();
            boolean priority=parcelRegistermodel.isPriority();
            int price=0;
            if(weight <=3.0)
                price+=10;
            else if(weight >3.0 && weight <=5.0)
                price+=20;
            else
                price+=30;


            if(distance<=200)
                price+=30;
            else if(distance >200 && distance <=1000)
                price+=45;
            else
                price+=60;

            if(priority)
                price+=10;
            preparedStatement.setInt(11,price);
            return preparedStatement;
        },keyHolder);
        return keyHolder.getKey()==null?null:keyHolder.getKey().intValue();
    }






}

