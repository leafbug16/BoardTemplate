package util;

public class PageHandler {
	// SearchCondition sc객체로 옮기기
	// private int pageSize; // 한 페이지당 몇개의 게시물을 보여줄지
	// private int page; // 현재 페이지
	// private String searchField; // 검색분야
	// private String searchWord; // 검색어
	// private String query; // 쿼리스트링

	private int totalCnt; // 총 게시물
	private int naviSize = 5; // 1~10
	private int totalPage; // 총 페이지수
	private int beginPage; // 시작 페이지
	private int endPage; // 끝 페이지
	private boolean showPrev; // 이전페이지를 보여줄 수 있는지 -> 1!= beginPage
	private boolean showNext; // 다음페이지를 보여줄 수 있는지 -> totalPage!= endPage
	private SearchCondition sc;
	
	public PageHandler(int totalCnt, int page) {
		this(totalCnt, new SearchCondition(page, 10));
	}
	
	public PageHandler(int totalCnt, int page, int pageSize) {
		this(totalCnt, new SearchCondition(page, pageSize));
	}
	
	public PageHandler(int totalCnt, SearchCondition sc) {
		this.totalCnt = totalCnt;
		this.sc = sc;
		doPaging(totalCnt, sc);
	}
	
	private void doPaging(int totalCnt, SearchCondition sc) {
		totalPage = (int) Math.ceil(totalCnt / (double) sc.getPageSize());
		beginPage = (sc.getPage() - 1) / naviSize * naviSize + 1;
		endPage = Math.min(beginPage + naviSize - 1, totalPage);
		showPrev = beginPage != 1;
		showNext = endPage != totalPage;
	}
	
	//쓸모없는 메서드
	void print() {
		System.out.println("page=" + sc.getPage());
		System.out.print(showPrev ? "PREV " : "");
		for (int i = beginPage; i <= endPage; ++i) {
			System.out.print(i + " ");
		}
		System.out.println(showNext ? " NEXT" : "");
	}
	
	//getter, setter
	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getNaviSize() {
		return naviSize;
	}

	public void setNaviSize(int naviSize) {
		this.naviSize = naviSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isShowPrev() {
		return showPrev;
	}

	public void setShowPrev(boolean showPrev) {
		this.showPrev = showPrev;
	}

	public boolean isShowNext() {
		return showNext;
	}

	public void setShowNext(boolean showNext) {
		this.showNext = showNext;
	}

	public SearchCondition getSc() {
		return sc;
	}

	public void setSc(SearchCondition sc) {
		this.sc = sc;
	}

	@Override
	public String toString() {
		return "PageHandler [totalCnt=" + totalCnt + ", naviSize=" + naviSize + ", totalPage=" + totalPage + ", beginPage=" + beginPage + ", endPage=" + endPage
				+ ", showPrev=" + showPrev + ", showNext=" + showNext + ", sc=" + sc + "]";
	}

}