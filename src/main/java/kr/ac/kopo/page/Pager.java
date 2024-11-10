package kr.ac.kopo.page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mypc
 *
 */
public class Pager {
	private int page = 1; // 현재페이지 변수 기본값 1
	private int perPage = 10; // 한 화면에 보여질 개수
	private float total; // 총 개수
	private int perGroup = 5; // 표시될 버튼 개수 ex: < << 1 2 3 4 5 >> >

	private String category;

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
		// 도서 총 개수에 보여질 개수를 나눈다 1.1같이 소수점이 있으면 2로 만든다.
		return (int) Math.ceil(total / perPage);
	}

	// 이전
	public int getPrev() {
		// 현재 페이지가 보여질 버튼 개수보다 작거나 같으면 1로하고 아니라면 계산값으로
		// 6~10에서 이전 페이지를 누르면 1이 되고 11~ 15에서 이전을 누르면 전 페이지네이션 첫번째 버튼으로 가게 만듬.
		return page <= perGroup ? 1 : (((page - 1) / perGroup) - 1) * perGroup + 1;
	}

	// 다음
	public int getNext() {
		int next = (((page - 1) / perGroup) + 1) * perGroup + 1;
		int last = getLast();

		// 다음을 눌렀을때 마지막 페이지 수보다 크면 last로 적다면 next값
		return next < last ? next : last;
	}

	// 페이지 번호 만들기
	public List<Integer> getList() {
		List<Integer> list = new ArrayList<>();

		// 시작페이지값 알기
		int startPage = (((page - 1) / perGroup) + 0) * perGroup + 1;

		// 시작페이지부터 시작페이지 + 보여질 버튼개수 그리고 마지막 페이지 수와 같거나 전까지 반복
		for (int i = startPage; i < (startPage + perGroup) && i <= getLast(); i++)
			list.add(i);

		// 만약 반복후에 list가 비어있다면 도서가 없는것으로 1페이지를 띄어준다
		if (list.isEmpty())
			list.add(1);

		return list;
	}

	// 검색기능
	public String getQuery() {
		String query = "";

//		if(search > 0)

		return "";
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
