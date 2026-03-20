package com.hallak.SentinelAI.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class LoadPayloadsServiceImpl implements LoadPayloadsService {


    @Override
    public List<String> loadPayloads(String fileName) throws IOException {

        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);


        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        return reader.lines().toList();
    }



}
