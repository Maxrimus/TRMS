package com.TRMS;

import com.DAOs.DropBoxDAO;
import com.dropbox.core.DbxException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

    public static Connection conn;

    public static void main(String[] args) {
        Main m = new Main();
        try {
            m.setupDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setupDatabase() throws SQLException {
        String serverURL = "jdbc:postgresql://rev-pg-test.cyj6am5rzlko.us-east-2.rds.amazonaws.com:5432/rev_db";
        Properties props = new Properties();
        String username = "";
        String password = "";
        List<String> lines = new ArrayList<String>();
        String line = "";
        String dataFile = "src/main/resources/credentials.txt";
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(dataFile));
            while((line = bufferedReader.readLine()) != null) lines.add(line);
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            dataFile + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + dataFile + "'");
        }
        username = lines.get(0);
        password = lines.get(1);
        props.setProperty("user",username);
        props.setProperty("password",password);
        conn = DriverManager.getConnection(serverURL,props);
    }

    public void setupS3(){

    }
}
