package com.flooringmastery.dao;

import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
@Repository
public class ExportDaoImpl implements ExportDao{
    @Override
    public void exportData() throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("Backup/DataExport.txt"));

        File folder = new File("./Orders/");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String fileName = file.getPath() ;
                String fileDate =  fileName.format(String.valueOf(DateTimeFormatter
                        .ofPattern("MMddyyyy")));
                System.out.println(fileName);
                try {
                    File currentFile = new File(fileName);
                    Scanner myReader = new Scanner(currentFile);
                    String header = myReader.nextLine();
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        out.write(data+","+ fileDate);
                        out.newLine();
                    }
                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
            out.flush();
        }
        out.close();


    }
}
