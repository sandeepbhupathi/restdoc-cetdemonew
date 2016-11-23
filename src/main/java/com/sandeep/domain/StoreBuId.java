package com.sandeep.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="STORE_BUID")
public class StoreBuId {

	@Id
	@Column(name="str_nbr")
	private Integer storeNbr;
	
	@Column(name="bu_id")
	private String buId;

	/*@OneToOne(targetEntity=Store.class)
	@JoinColumns({@JoinColumn(name="str_nbr",referencedColumnName="str_nbr",insertable=false,updatable=false)})
	private Store str;*/
	
	/*public Store getStr() {
		return str;
	}

	public void setStr(Store str) {
		this.str = str;
	}*/

	public Integer getStoreNbr() {
		return storeNbr;
	}

	public void setStoreNbr(Integer storeNbr) {
		this.storeNbr = storeNbr;
	}

	public String getBuId() {
		return buId;
	}

	public void setBuId(String buId) {
		this.buId = buId;
	}
	
	
}
