package com.sandeep.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="STORE")
public class Store {

	
	@Id
	@Column(name="str_nbr")
	private Integer storeNbr;
	
	@Column(name="last_upd_ts")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp lastUpdTs;
	
	public Timestamp getLastUpdTs() {
		return lastUpdTs;
	}

	public void setLastUpdTs(Timestamp lastUpdTs) {
		this.lastUpdTs = lastUpdTs;
	}

	public Integer getStoreNbr() {
		return storeNbr;
	}

	public void setStoreNbr(Integer storeNbr) {
		this.storeNbr = storeNbr;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public String getStrAddress() {
		return strAddress;
	}

	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
	}

	@Column(name="str_nm")
	private String strName;
	
	@Column(name="str_addr")
	private String strAddress;
}
