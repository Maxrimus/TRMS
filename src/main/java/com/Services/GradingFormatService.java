package com.Services;

import com.DAOs.GradingFormatDAO;
import com.TRMS.ConnectionUtil;

import java.sql.SQLException;

public class GradingFormatService {

    GradingFormatDAO gradingFormatDAO = new GradingFormatDAO(ConnectionUtil.getConnectionUtil());

    public int getIdByName(String name){
        try {
            return gradingFormatDAO.readByName(name).getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getNameById(int id){
        try {
            return gradingFormatDAO.readById(id).getGradingFormat();
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }
}
