
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.launchcode.techjobs.console;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class JobData {
    private static final String DATA_FILE = "resources/job_data.csv";
    private static Boolean isDataLoaded = false;
    private static ArrayList<HashMap<String, String>> allJobs;

    public JobData() {
    }

    public static ArrayList<String> findAll(String field) {
        loadData();
        ArrayList<String> values = new ArrayList();
        Iterator var2 = allJobs.iterator();

        while(var2.hasNext()) {
            HashMap<String, String> row = (HashMap)var2.next();
            String aValue = (String)row.get(field);
            if (!values.contains(aValue)) {
                values.add(aValue);
            }
        }

        return values;
    }

    public static ArrayList<HashMap<String, String>> findAll() {
        loadData();
        return allJobs;
    }

    public static ArrayList<HashMap<String, String>> findByColumnAndValue(String column, String value) {
        loadData();
        ArrayList<HashMap<String, String>> jobs = new ArrayList();
        Iterator var3 = allJobs.iterator();

        while(var3.hasNext()) {
            HashMap<String, String> row = (HashMap)var3.next();
            String aValue = (String)row.get(column);
            if (aValue.contains(value)) {
                jobs.add(row);
            }
        }

        return jobs;
    }

    private static void loadData() {
        if (!isDataLoaded) {
            try {
                Reader in = new FileReader("resources/job_data.csv");
                CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
                List<CSVRecord> records = parser.getRecords();
                Integer numberOfColumns = ((CSVRecord)records.get(0)).size();
                String[] headers = (String[])parser.getHeaderMap().keySet().toArray(new String[numberOfColumns]);
                allJobs = new ArrayList();
                Iterator var5 = records.iterator();

                while(var5.hasNext()) {
                    CSVRecord record = (CSVRecord)var5.next();
                    HashMap<String, String> newJob = new HashMap();
                    String[] var8 = headers;
                    int var9 = headers.length;

                    for(int var10 = 0; var10 < var9; ++var10) {
                        String headerLabel = var8[var10];
                        newJob.put(headerLabel, record.get(headerLabel));
                    }

                    allJobs.add(newJob);
                }

                isDataLoaded = true;
            } catch (IOException var12) {
                System.out.println("Failed to load job data");
                var12.printStackTrace();
            }

        }
    }
}
