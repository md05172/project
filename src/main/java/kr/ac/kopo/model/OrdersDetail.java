package kr.ac.kopo.model;

public class OrdersDetail {
	private Long id;
	private Long ordersId;
	private Long bookId;
	private Integer amount;
	private String phone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "OrdersDetail [id=" + id + ", ordersId=" + ordersId + ", bookId=" + bookId + ", amount=" + amount
				+ ", phone=" + phone + "]";
	}

}
