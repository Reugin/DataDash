package com.datadash.app.services;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.datadash.app.exception.CustomException;
import com.datadash.app.model.Report;
import com.datadash.app.repositories.ReportRepository;
import com.datadash.app.repositories.UserRepository;
import com.datadash.app.wrapper.ResponseWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

@Service
public class ReportService {

	private static final Log logger = LogFactory.getLog(ReportService.class);

	@Autowired
	ReportRepository reportRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	Token myToken;

	public List<Report> getAllReports(String token) {

		try {
			String emailId = myToken.decodeToken(token);

			boolean userExists = userRepo.existsByEmail(emailId);

			if (!userExists) {
				logger.warn("Authentication Failed : " + emailId);
				throw new CustomException("Authentication Failed");
			}

			return reportRepo.findAllByCreatedBy(emailId);

		} catch (Exception e) {
			throw e;
		}

	}

	public void uploadFile(MultipartFile file, String token) {
		try {

			String emailId = myToken.decodeToken(token);

			boolean userExists = userRepo.existsByEmail(emailId);

			if (!userExists) {
				logger.warn("Authentication Failed : " + emailId);
				throw new CustomException("Authentication Failed");
			}

			String originalFilename = file.getOriginalFilename();

			if (originalFilename != null) {
				if (originalFilename.endsWith(".csv")) {
					parseCSV(file, emailId);
				} else if (originalFilename.endsWith(".xlsx")) {
					parseExcel(file, emailId);
				} else if (originalFilename.endsWith(".json")) {
					parseJSON(file, emailId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Unsupported file format");
		}
	}

	private void parseCSV(MultipartFile file, String emailId) {
		try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
			List<Report> reports = new ArrayList<>();
			List<String[]> tableData = csvReader.readAll();
			for (String[] row : tableData) {
				if (row.length >= 3) { // Ensure at least 3 columns in CSV (userId, title, body)
					Report report = new Report(row[0], row[1], row[2], row[3], emailId);
					reports.add(report);
				}
			}
			reportRepo.saveAll(reports);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Error parsing CSV");
		}
	}

	private void parseExcel(MultipartFile file, String emailId) {
		try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
			Sheet sheet = workbook.getSheetAt(0);
			List<Report> reports = new ArrayList<>();
			for (Row row : sheet) {
				if (row.getPhysicalNumberOfCells() >= 3) {
					Report report = new Report(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),
							row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue(), emailId);
					reports.add(report);
				}
			}
			reportRepo.saveAll(reports);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Error parsing Excel");
		}
	}

	private void parseJSON(MultipartFile file, String emailId) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			List<Report> reports = new ArrayList<>();
			List<Map<String, Object>> jsonData = objectMapper.readValue(file.getInputStream(),
					new TypeReference<List<Map<String, Object>>>() {
					});

			for (Map<String, Object> data : jsonData) {
				if (data.containsKey("userId") && data.containsKey("title") && data.containsKey("body")) {
					Report report = new Report(data.get("id").toString(), data.get("userId").toString(),
							data.get("title").toString(), data.get("body").toString(), emailId);
					reports.add(report);
				}
			}
			reportRepo.saveAll(reports);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Error parsing JSON");
		}
	}
}
