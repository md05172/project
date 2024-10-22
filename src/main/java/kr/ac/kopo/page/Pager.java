package kr.ac.kopo.page;

public class Pager {
	private int page = 1; // 현재페이지 변수 기본값 1
	private int perPage = 10; // 한 화면에 보여질 개수
	private float total; // 총 개수
	private int perGroup = 5; // 표시될 버튼 개수 ex: < << 1 2 3 4 5 >> >

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getPerGroup() {
		return perGroup;
	}

	public void setPerGroup(int perGroup) {
		this.perGroup = perGroup;
	}

	public int getLast() {
		return 0;
	}
}
