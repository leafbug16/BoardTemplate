package util;

public class PageHandler {
	private int totalBoardCnt; // 총 게시물
	private int naviSize = 5; // 1~10
	private int totalPage; // 총 페이지수
	private int beginPage; // 시작 페이지
	private int endPage; // 끝 페이지
	private boolean showPrev; // 이전페이지를 보여줄 수 있는지 -> 1!= beginPage
	private boolean showNext; // 다음페이지를 보여줄 수 있는지 -> totalPage!= endPage
	private SearchCondition searchCondition;
	
	public PageHandler(int totalBoardCnt, int page) {
		this(totalBoardCnt, new SearchCondition(page, 10));
	}
	
	public PageHandler(int totalBoardCnt, int page, int pageSize) {
		this(totalBoardCnt, new SearchCondition(page, pageSize));
	}
	
	public PageHandler(int totalBoardCnt, SearchCondition searchCondition) {
		this.totalBoardCnt = totalBoardCnt;
		this.searchCondition = searchCondition;
		doPaging(totalBoardCnt, searchCondition);
	}
	
	private void doPaging(int totalBoardCnt, SearchCondition searchCondition) {
		totalPage = (int) Math.ceil(totalBoardCnt / (double) searchCondition.getPageSize());
		beginPage = (searchCondition.getPage() - 1) / naviSize * naviSize + 1;
		endPage = Math.min(beginPage + naviSize - 1, totalPage);
		showPrev = beginPage != 1;
		showNext = endPage != totalPage;
	}
	
	//getter, setter
	public int getTotalCnt() {
		return totalBoardCnt;
	}

	public void setTotalCnt(int totalBoardCnt) {
		this.totalBoardCnt = totalBoardCnt;
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

	public SearchCondition getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(SearchCondition searchCondition) {
		this.searchCondition = searchCondition;
	}

	@Override
	public String toString() {
		return "PageHandler [totalBoardCnt=" + totalBoardCnt + ", naviSize=" + naviSize + ", totalPage=" + totalPage + ", beginPage=" + beginPage + ", endPage=" + endPage
				+ ", showPrev=" + showPrev + ", showNext=" + showNext + ", searchCondition=" + searchCondition + "]";
	}

}