package com.sandeep.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sandeep.domain.Store;
import com.sandeep.repo.StroeRepo;

@RefreshScope
@RestController
public class SampleController {

	@Autowired
	private StroeRepo strRepo;
	
	@Autowired
	private JdbcTemplate jdbcTemp;
	
	Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping("/strList")
	public HttpEntity<PagedResources<Store>> strList(@RequestParam("page") int page,
			@RequestParam("size") int size,
			PagedResourcesAssembler assembler){
		Page<Store> strs= strRepo.findAll(new PageRequest(page, size));
		
		return new ResponseEntity<>(assembler.toResource(strs),HttpStatus.OK);
		
	}
	
	@RequestMapping("/str")
	public HttpEntity<Store> store(@RequestParam("storeNbr") int storeNbr){
		Store strs= strRepo.findOne(storeNbr);
		logger.debug(jdbcTemp.queryForMap("select str_nm from STORE where str_nbr=1"));
		return new ResponseEntity<>(strs,HttpStatus.OK);
		
	}
	
	@Value("${repo_key:Hello default}")
    private String message;

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }
	
}
