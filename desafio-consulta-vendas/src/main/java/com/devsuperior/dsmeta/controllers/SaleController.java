package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;


import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<ReportDTO>> getReport(
			@RequestParam (name = "minDate", required = false) String min,
			@RequestParam (name = "maxDate", required = false) String max,
			@RequestParam (name = "name", defaultValue = "") String name,
			Pageable pageable
	)
	{
		Page<ReportDTO> dto = service.searchReport(min,max,name,pageable);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummaryDTO>> getSummary(
			@RequestParam (name = "minDate", required = false) String min,
			@RequestParam (name = "maxDate", required = false) String max
			)
	{
		List<SummaryDTO> dto = service.searchSummary(min,max);
		return ResponseEntity.ok(dto);
	}


}
