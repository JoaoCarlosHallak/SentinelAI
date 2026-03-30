package com.hallak.SentinelAI.services.xss;


import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

import java.util.List;

public interface XssScanService {

    List<HttpResponseDataDTO> scan(String target) throws Exception;
}
