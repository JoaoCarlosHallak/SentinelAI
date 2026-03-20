package com.hallak.SentinelAI.dtos;

import java.util.List;

public record ScanRequest(String target, List<ScanType> scanTypeList) {
}