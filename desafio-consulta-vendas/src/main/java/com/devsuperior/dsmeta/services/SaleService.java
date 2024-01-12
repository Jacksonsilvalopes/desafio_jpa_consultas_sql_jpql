package com.devsuperior.dsmeta.services;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.services.util.Periods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SummaryDTO> searchSummary(String min, String max) {

		List<SummaryDTO> summaryResult = Periods.getSummaryWithoutPeriodParameters(min, max, repository);
		if (summaryResult == null) {
			LocalDate maxDate = LocalDate.parse(Periods.getMaximumDate(max));
			LocalDate minDate = LocalDate.parse(Periods.getMinimumDate(min, max));
			return repository.searchSummary(minDate, maxDate);
		}
		return summaryResult;
	}

	public Page<ReportDTO> searchReport(String min, String max, String name, Pageable pageable) {

		Page<ReportDTO> reportResult = Periods.getReportWithoutPeriodParameters(min, max, name, repository,pageable );
		if (reportResult == null) {
			LocalDate maxDate = LocalDate.parse(Periods.getMaximumDate(max));
			LocalDate minDate = LocalDate.parse(Periods.getMinimumDate(min, max));
		return repository.searchReport(minDate, maxDate,name, pageable);

		}
		return reportResult;

	}
}
