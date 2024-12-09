package kr.ac.kopo.model;

import java.util.Date;
import java.util.List;

public class Orders {
	private Long id;
	private Long custId;
	private Long addressId;
	private Date orderdate;
	private String code;

	private List<OrdersDetail> details;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public List<OrdersDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrdersDetail> details) {
		this.details = details;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", custId=" + custId + ", addressId=" + addressId + ", orderdate=" + orderdate
				+ ", code=" + code + ", details=" + details + "]";
	}

}
