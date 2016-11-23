package com.sandeep.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sandeep.cache.TestCacheValue;
import com.sandeep.domain.Store;
import com.sandeep.repo.StroeRepo;
import com.sandeep.util.MessageTestUtil;

@RefreshScope
@RestController
public class SampleController {

	@Autowired
	private StroeRepo strRepo;
	
	@Autowired
	private JdbcTemplate jdbcTemp;
	
	Logger logger = Logger.getLogger(getClass());
	
	
	@RequestMapping("/stream")
	public Stream<Store> findAllStoreStream(){
		return strRepo.findAllByCustomQueryAndStream();
	}
	@RequestMapping(path="/strList",produces=MediaType.APPLICATION_JSON_VALUE)
	public Page<Store> strList(@RequestParam("page") int page,
			@RequestParam(name="size", defaultValue="1") int size){
		Page<Store> strs= strRepo.findAll(new PageRequest(page, size));
		
		return strs;
		
	}
	@RequestMapping("/listStores")
	public List<Store> listStores(@RequestParam("page") int page){
		
		List<Store> strList = new ArrayList<Store>();
		
		
		/*List<Store> strList = new ArrayList<Store>();
		strList = jdbcTemp.query("select * from STORE", new RowMapper<Store>(){

			@Override
			public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
				Store str = new Store();
				str.setStoreNbr(rs.getInt(1));
				str.setStrName(rs.getString(2));
				str.setLastUpdTs(rs.getTimestamp(4));
				str.setStrAddress(rs.getString(3));
				return str;
			}
			
		});
		
		Map<String,String> strMap = new HashMap<>();
		
		jdbcTemp.query("select * from STORE", new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				strMap.put(rs.getInt(1)+"|"+rs.getString(2), rs.getString(3));
				
			}
			
		});*/
		
		strList =strRepo.findAll();
		System.out.println("==========================="+strList);
		return strList;
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
    
    @RequestMapping("/utilMsg")
    String getUtilMsg(){
    	Map<String,String> datTest = null;
    	if(!TestCacheValue.getInstance().isCacheLive()){
    		datTest = new HashMap<String, String>();
    		datTest.put("TestNew", "New Data");
    		TestCacheValue.getInstance().loadCache(datTest);
    	}
    	String sstrValue = TestCacheValue.getInstance().getTestValue("TestNew");
    	return MessageTestUtil.
    			convertString(sstrValue);
    }
	
}
