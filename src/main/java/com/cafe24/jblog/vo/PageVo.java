package com.cafe24.jblog.vo;

public class PageVo {

	private int pageNo;
	private int startContentNo;
	private int endContentNo;
	private int totalContentCount;
	private int contentPerPage;
	private int totalPageNo;

	public PageVo(int pageNo, int contentPerPage, int totalContentCount) {
		this.contentPerPage = contentPerPage; 
		this.totalContentCount = totalContentCount;
		this.totalPageNo =  (totalContentCount/contentPerPage) + 1; 
		if(pageNo < 1) {
			this.pageNo=1;
		}
		else if(pageNo > totalPageNo) {
			this.pageNo = totalPageNo;
		}else {
			this.pageNo=pageNo;
		}
		this.startContentNo = (this.pageNo-1)*contentPerPage;

		if(totalContentCount < startContentNo + contentPerPage) {
			this.endContentNo = totalContentCount;
		}else {
			this.endContentNo= startContentNo + contentPerPage;
		}
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if(pageNo < 1) {
			this.pageNo=1;
		}
		if(pageNo > totalPageNo) {
			this.pageNo = totalPageNo;
		}
		this.pageNo = pageNo;
	}

	public int getStartContentNo() {
		return startContentNo;
	}

	public void setStartContentNo(int startContentNo) {
		this.startContentNo = startContentNo;
	}

	public int getEndContentNo() {
		return endContentNo;
	}

	public void setEndContentNo(int endContentNo) {
		this.endContentNo = endContentNo;
	}

	public int getTotalContentCount() {
		return totalContentCount;
	}

	public void setTotalContentCount(int totalContentCount) {
		this.totalContentCount = totalContentCount;
	}

	public int getContentPerPage() {
		return contentPerPage;
	}

	public void setContentPerPage(int contentPerPage) {
		this.contentPerPage = contentPerPage;
	}
	public int getTotalPageNo() {
		return totalPageNo;
	}

	public void setTotalPageNo(int totalPageNo) {
		this.totalPageNo = totalPageNo;
	}
	
	@Override
	public String toString() {
		return "PageVo [pageNo=" + pageNo + ", startContentNo=" + startContentNo + ", endContentNo=" + endContentNo
				+ ", totalContentCount=" + totalContentCount + ", contentPerPage=" + contentPerPage + ", totalPageNo="
				+ totalPageNo + "]";
	}
	
}
