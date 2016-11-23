package com.sandeep.repo;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sandeep.domain.Store;

@Repository
public interface StroeRepo extends PagingAndSortingRepository<Store, Integer>{

	List<Store> findAll();
	
	@Query("select s from Store s")
	Stream<Store> findAllByCustomQueryAndStream();
}
