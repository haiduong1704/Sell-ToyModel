package com.mockproject.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;
	private String address;
	private String phone;
	private Timestamp createdDate;
	private HashMap<Long, CartDetailDto> listDetail = new HashMap<>();
	private Integer totalQuantity = 0;
	private Double totalPrice = 0D;


	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public HashMap<Long, CartDetailDto> getListDetail() {
		return listDetail;
	}

	public void setListDetail(HashMap<Long, CartDetailDto> listDetail) {
		this.listDetail = listDetail;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
