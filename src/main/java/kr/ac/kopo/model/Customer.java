package kr.ac.kopo.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private Long id;
	private String name;
	private String phone;
	private String email;
	private String password;
	private int role;

	private List<Address> address = new ArrayList<Address>();

	private List<Wish> wish = new ArrayList<Wish>();

	private List<Review> review = new ArrayList<Review>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<Wish> getWish() {
		return wish;
	}

	public void setWish(List<Wish> wish) {
		this.wish = wish;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", password="
				+ password + ", role=" + role + ", address=" + address + ", wish=" + wish + ", review=" + review + "]";
	}

}
