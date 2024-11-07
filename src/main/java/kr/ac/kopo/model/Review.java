package kr.ac.kopo.model;

import java.util.Date;

public class Review {
	private Long id;
	private Long bookId;
	private Long custId;
	private String comments;
	private Date writeDate;

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

	@Override
	public String toString() {
		return "Review [id=" + id + ", bookId=" + bookId + ", custId=" + custId + ", comments=" + comments
				+ ", writeDate=" + writeDate + "]";
	}

}
