package kr.ac.kopo.model;

public class Wish {
	private Long id;
	private Long bookId;
	private Long custId;

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

	@Override
	public String toString() {
		return "Wish [id=" + id + ", bookId=" + bookId + ", custId=" + custId + "]";
	}

}
