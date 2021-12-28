package com.example.workflow;

import com.example.workflow.models.DnsCsv;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class Globals {
    public List<DnsCsv> linhDomains;

    public Globals() {
        populateDomains();
    }

    public DnsCsv getRandomDomain() {
        int index = ThreadLocalRandom.current().nextInt(0, linhDomains.size());
        return linhDomains.get(index);
    }

    private void populateDomains() {
        CSVReader reader;
        InputStream is = getClass().getClassLoader().getResourceAsStream("DomainList_12262021_pure.csv");
        if (is == null) return;
        reader = new CSVReader(new InputStreamReader(is, StandardCharsets.US_ASCII));
        // create a formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/u");
        try {
            // Read all rows at once
            // List<String[]> allRows = reader.readAll();
            linhDomains = new ArrayList<>();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                //"domain_name","Status","7 Day Whois Count","15 Day Whois Count","30 Day Whois Count","Expiration Date","Locked","Auto Renew","Nameserver 1","has_batch_errors"
                linhDomains.add(DnsCsv.builder()
                        .name(nextLine[0])
                        .status(nextLine[1])
                        .whois07(nextLine[2].length() > 0 ? Integer.parseInt(nextLine[2]) : 0)
                        .whois15(nextLine[3].length() > 0 ? Integer.parseInt(nextLine[3]) : 0)
                        .whois30(nextLine[4].length() > 0 ? Integer.parseInt(nextLine[4]) : 0)
                        .expiration(LocalDate.parse(nextLine[5], formatter))
//                        .locked(Boolean.parseBoolean(nextLine[6]))
                        .locked(nextLine[6].equals("Yes"))
                        .autorenew(nextLine[7].equals("On"))
                        .nameserver1(nextLine[8])
                        .batchErrors(Integer.parseInt(nextLine[9]))
                        .build());
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}
