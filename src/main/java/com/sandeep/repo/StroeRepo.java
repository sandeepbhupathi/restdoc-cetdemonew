package com.sandeep.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sandeep.domain.Store;

@Repository
public interface StroeRepo extends PagingAndSortingRepository<Store, Integer>{

}
