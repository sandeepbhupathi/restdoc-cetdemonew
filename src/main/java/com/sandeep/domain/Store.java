package com.sandeep.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STORE")
public class Store {

	
	@Id
	@Column(name="str_nbr")
	private Integer storeNbr;
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
