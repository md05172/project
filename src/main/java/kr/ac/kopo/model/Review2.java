package kr.ac.kopo.model;

import java.util.Date;

public class Review2 {
	private Long id;
	private Long bookId;
	private Long custId;
	private String comments;
	private Date writeDate;
	private Double star;

	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	private Integer select = 1;

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

	@Override
	public String toString() {
		return "Review2 [id=" + id + ", bookId=" + bookId + ", custId=" + custId + ", comments=" + comments
				+ ", writeDate=" + writeDate + ", star=" + star + ", customer=" + customer + ", select=" + select + "]";
	}

}
