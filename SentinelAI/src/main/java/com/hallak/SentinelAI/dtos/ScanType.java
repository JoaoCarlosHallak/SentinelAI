package com.hallak.SentinelAI.dtos;

public enum ScanType {
    XSS,
    LFI,
    SQL_INJECTION,
    OPEN_REDIRECT,
    INSECURE_HEADERS,
    COMMAND_INJECTION,
    CSRF,
    IDOR,
}
