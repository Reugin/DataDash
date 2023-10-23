package com.datadash.app.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.datadash.app.model.Report;

public interface ReportRepository extends MongoRepository<Report, String> {
	List<Report> findAllByCreatedBy(String emailId);
}
