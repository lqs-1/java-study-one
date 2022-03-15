package com.lqs.api;

import com.lqs.entity.Result;

import java.util.Map;

public interface ReportService {
    Result getMemberReport();

    Map<String, Object> getBusinessReportData() throws Exception;
}
