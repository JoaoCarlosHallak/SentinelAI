package com.hallak.SentinelAI.services;

import java.util.List;

public interface LoadPayloadsService {
    List<String> loadPayloads(String fileName) throws Exception;
}
