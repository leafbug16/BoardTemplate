package util;

public class SearchCondition {
	//검색 조건
	private String searchField = "";
	private String searchWord = "";
	//현재 페이지
	private int page;
	//페이지당 게시물 수
	private int pageSize;

	//검색어가 지정된 경우
	public SearchCondition(String searchField, String searchWord, int page, int pageSize) {
		this.searchField = searchField;
		this.searchWord = searchWord;
		this.page = page;
		this.pageSize = pageSize;
	}
	//검색어가 없는 경우
	public SearchCondition(int page, int pageSize) {
		this("title", "", page, pageSize);
	}
	
	//현재 페이지에 따라 게시물을 가져올 시작점(특정 레코드)
	public int getOffset(int page) {
		return (page - 1) * pageSize;
	}

	//현재 페이지를 기준으로 쿼리스트링을 반환하는 경우
	public String getQueryString() {
		return getQueryString(page);
	}

	//지정된 페이지가 있을 경우 쿼리스트링 반환
	public String getQueryString(int page) {
		if (searchWord != null && !"".equals(searchWord))
			return "?pageNum=" + page + "&pageSize=" + pageSize + "&searchField=" + searchField + "&searchWord=" + searchWord;
		else
			return "?pageNum=" + page + "&pageSize=" + pageSize;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "SearchCondition [searchField=" + searchField + ", searchWord=" + searchWord + ", page=" + page + ", pageSize=" + pageSize + "]";
	}

}