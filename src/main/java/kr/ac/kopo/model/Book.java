package kr.ac.kopo.model;

public class Book {
	private Long id;
	private String name;
	private String publisher;
	private int price;
	private String path;
	private String info;
	private String writer;
	private String writerInfo;
	private String category;

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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriterInfo() {
		return writerInfo;
	}

	public void setWriterInfo(String writerInfo) {
		this.writerInfo = writerInfo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", publisher=" + publisher + ", price=" + price + ", path=" + path
				+ ", info=" + info + ", writer=" + writer + ", writerInfo=" + writerInfo + ", category=" + category
				+ ", getId()=" + getId() + ", getName()=" + getName() + ", getPublisher()=" + getPublisher()
				+ ", getPrice()=" + getPrice() + ", getPath()=" + getPath() + ", getInfo()=" + getInfo()
				+ ", getWriter()=" + getWriter() + ", getWriterInfo()=" + getWriterInfo() + ", getCategory()="
				+ getCategory() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
