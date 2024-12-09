package kr.ac.kopo.model;

public class Buy {
	private Long bookid;
	private Integer price;
	private Integer sum;
	private Integer amount;
	private String bookname;
	private String writer;

	public Long getBookid() {
		return bookid;
	}

	public void setBookid(Long bookid) {
		this.bookid = bookid;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "Buy [bookid=" + bookid + ", price=" + price + ", sum=" + sum + ", amount=" + amount + ", bookname="
				+ bookname + ", writer=" + writer + "]";
	}

}
