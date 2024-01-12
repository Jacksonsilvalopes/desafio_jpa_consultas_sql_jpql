package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("select new com.devsuperior.dsmeta.dto.SummaryDTO(obj.seller.name, sum(obj.amount)) " +
            " from Sale obj " +
            " where obj.date between :min and :max " +
            " group by (obj.seller.name) ")
    List<SummaryDTO> searchSummary(LocalDate min, LocalDate max);

    @Query("select new com.devsuperior.dsmeta.dto.ReportDTO(obj.id, obj.date, obj.amount, obj.seller.name) " +
            " from Sale obj " +
            " where upper(obj.seller.name) like concat('%',upper(:name), '%') and " +
            " obj.date between :min and :max ")
    Page<ReportDTO> searchReport(LocalDate min, LocalDate max, String name, Pageable pageable);

}
