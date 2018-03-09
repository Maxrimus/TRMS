package com.TRMS;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionUtil {
    private static ConnectionUtil connectionUtil = new ConnectionUtil();

    private ConnectionUtil() {
        super();
    }

    public static ConnectionUtil getConnectionUtil() {
        return connectionUtil;
    }

    public Connection getConnection() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Class.forName("org.postgresql.Driver");
            String serverURL = "jdbc:postgresql://rev-pg-test.cyj6am5rzlko.us-east-2.rds.amazonaws.com:5432/rev_db";
            Properties props = new Properties();
            String username = "";
            String password = "";
            List<String> lines = new ArrayList<String>();
            String line = "";
            BufferedReader bufferedReader = null;

            InputStream credentialsStream = classLoader.getResourceAsStream("credentials.txt");
            Reader reader = new InputStreamReader(credentialsStream, "UTF-8");
            bufferedReader = new BufferedReader(reader);
            while((line = bufferedReader.readLine()) != null) lines.add(line);
            bufferedReader.close();

            username = lines.get(0);
            password = lines.get(1);
            props.setProperty("user",username);
            props.setProperty("password",password);
            return DriverManager.getConnection(serverURL,props);
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
