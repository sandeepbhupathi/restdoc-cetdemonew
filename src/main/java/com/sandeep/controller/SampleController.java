package com.sandeep.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandeep.domain.Store;
import com.sandeep.repo.StroeRepo;


@RestController
public class SampleController {

	@Autowired
	private StroeRepo strRepo;
	
	@RequestMapping("/strList")
	public HttpEntity<PagedResources<Store>> strList(Pageable pageable,
			PagedResourcesAssembler assembler){
		Page<Store> strs= strRepo.findAll(pageable);
		
		return new ResponseEntity<>(assembler.toResource(strs),HttpStatus.OK);
		
	}
}
