package kr.ac.kopo.model;

import java.util.Date;

public class Review {
	private Long id;
	private Long bookId;
	private Long custId;
	private Long apiId;
	private String comments;
	private Date writeDate;
	private Double star;

	private Integer select = 1;

	private Double avg;
	private Integer count;

	private Customer customer;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getAvg() {
		return avg;
	}

	public void setAvg(Double avg) {
		this.avg = avg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public Double getStar() {
		return star;
	}

	public void setStar(Double star) {
		this.star = star;
	}

	public Integer getSelect() {
		return select;
	}

	public void setSelect(Integer select) {
		this.select = select;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getApiId() {
		return apiId;
	}

	public void setApiId(Long apiId) {
		this.apiId = apiId;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", bookId=" + bookId + ", custId=" + custId + ", apiId=" + apiId + ", comments="
				+ comments + ", writeDate=" + writeDate + ", star=" + star + ", select=" + select + ", avg=" + avg
				+ ", count=" + count + ", customer=" + customer + "]";
	}

}
