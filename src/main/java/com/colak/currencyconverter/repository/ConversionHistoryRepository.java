package com.colak.currencyconverter.repository;

import com.colak.currencyconverter.repository.entity.ConversionHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author AhmetColak date 8.07.2022
 **/
public interface ConversionHistoryRepository extends MongoRepository<ConversionHistory, String> {

	@Async
	<S extends ConversionHistory> S save(S entity);

	Optional<List<ConversionHistory>> findAllByCreateDateBetween(LocalDateTime startDate, LocalDateTime endDate);

	List<ConversionHistory> findAllByConversionHistoryId(String transactionId);
}
