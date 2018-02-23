package com.DAOs;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DropBoxDAO {

    private static String accessToken = "";
    private DbxClientV2 client;

    public DropBoxDAO(){
        try {
            dropBoxSetup();
        } catch (DbxException e) {
            e.printStackTrace();
        }
    }

    public void dropBoxSetup() throws DbxException {
        String line = "";
        List<String> lines = new ArrayList<>();
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
        accessToken = lines.get(2);
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        client = new DbxClientV2(config, accessToken);
    }

    public void uploadFile(String filename) throws DbxException {
        try (InputStream in = new FileInputStream("src/main/resources/test.png")) {
            FileMetadata metadata = client.files().uploadBuilder("/test.png").uploadAndFinish(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadFile(String filename) throws DbxException {
        try {
            OutputStream downloadFile = new FileOutputStream("src/main/resources/"+filename);
            try { FileMetadata metadata = client.files().downloadBuilder("/"+filename).download(downloadFile); }
            finally{ downloadFile.close(); }
        }
        catch (IOException e) {
            System.out.println("Unable to download file to local system\n Error: " + e);
        }
    }
}