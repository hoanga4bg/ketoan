package com.htttql.service;

import java.util.List;

import com.htttql.entity.Report;
import com.htttql.entity.SampleReport;

public interface ReportDAO extends GeneralService<Report>{
    public List<String> findAllName();
    public SampleReport findByName(String name);
    public List<Report> findByType(SampleReport sample);
}
