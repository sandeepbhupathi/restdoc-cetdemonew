package com.sandeep.cache;

import java.util.HashMap;
import java.util.Map;

public class TestCacheValue {

	private static Map<String,String> dataManageMap =  new HashMap<String,String>();
	private static Map<String,Long> cacheManageMap =  new HashMap<String,Long>();
	
	private static String cacheName = "testCache";
	
	private static TestCacheValue testCache = null;
	
	public TestCacheValue() {
	}
	
	public static synchronized TestCacheValue getInstance(){
		if(testCache==null){
			testCache = new TestCacheValue();
		}
		
		return testCache;
	}

	public String getTestValue(String key){
		return dataManageMap.get(key);
	}
	
	public void loadCache(Map<String,String> testDataMap) {
		long cacheExpire = System.currentTimeMillis()+ (1000*100);
		System.out.println("===============inside load cache===================");
		setExpiration(cacheName,cacheExpire);
		dataManageMap =null;
		this.dataManageMap = testDataMap;
	}
	public boolean isCacheLive(){
		System.out.println("Current System Time "+System.currentTimeMillis());
		System.out.println("Expiration Time"+getExpiration(cacheName));
		if(System.currentTimeMillis() < getExpiration(cacheName)){
			return true;
		}
		return false;
	}
	
	public static long getExpiration(String cacheName){
		long expirationValue= 0;
		
		if(cacheManageMap.containsKey(cacheName)){
			expirationValue = cacheManageMap.get(cacheName);
		}
		return expirationValue;
	}
	
	public static void setExpiration(String cacheName,long expirationValue){
		cacheManageMap.put(cacheName, expirationValue);
	}
	
}
