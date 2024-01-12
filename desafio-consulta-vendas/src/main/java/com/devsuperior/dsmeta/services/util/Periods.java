package com.devsuperior.dsmeta.services.util;



import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class Periods {
    public static String getMaximumDate(String max) {
        if (max == null) {
            LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
            max = String.valueOf(today);
        }
        return max;
    }

    public static String getMinimumDate(String min, String max) {
        if (min == null) {
            LocalDate maxDate = LocalDate.parse(max);
            LocalDate result = maxDate.minusYears(1L);
            min = String.valueOf(result);
        }
        return min;
    }

    public static List<SummaryDTO> getSummaryWithoutPeriodParameters(String min, String max, SaleRepository repository) {
        if (min == null & max == null) {
            max = getMaximumDate(max);
            min = getMinimumDate(min, max);
            return repository.searchSummary(LocalDate.parse(min), LocalDate.parse(max));
        }
        return null;
    }


    public static Page<ReportDTO> getReportWithoutPeriodParameters(String min, String max, String name, SaleRepository repository, Pageable pageable) {
        if (min == null & max == null) {
            max = getMaximumDate(max);
            min = getMinimumDate(min, max);
            return repository.searchReport(LocalDate.parse(min), LocalDate.parse(max), name, pageable);

        }
        return null;
    }
}
