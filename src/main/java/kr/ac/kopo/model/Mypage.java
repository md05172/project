package kr.ac.kopo.model;

import java.util.ArrayList;
import java.util.List;

public class Mypage {
	private Long bookId;
	private String bookName;
	private String bookPublisher;
	private Integer bookPrice;
	private String bookPath;

	private List<Wish> wishs = new ArrayList<Wish>();

	private List<Review> reviews = new ArrayList<Review>();

	private List<Orders> orders = new ArrayList<Orders>();

	private List<Address> address = new ArrayList<Address>();

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public List<Wish> getWishs() {
		return wishs;
	}

	public void setWishs(List<Wish> wishs) {
		this.wishs = wishs;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Integer getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Integer bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookPath() {
		return bookPath;
	}

	public void setBookPath(String bookPath) {
		this.bookPath = bookPath;
	}

	@Override
	public String toString() {
		return "Mypage [bookId=" + bookId + ", bookName=" + bookName + ", bookPublisher=" + bookPublisher + ", wishs="
				+ wishs + ", reviews=" + reviews + ", orders=" + orders + ", address=" + address + "]";
	}

}
