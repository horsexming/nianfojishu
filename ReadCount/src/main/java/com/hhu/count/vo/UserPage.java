package com.hhu.count.vo;

/*分页page*/
public class UserPage {
	
	private Integer pn;//当前页数
	private Integer page;//总页数
	private Integer pagenum;//总记录数
	private Integer pagesize;//每页总条数
	public Integer getPn() {
		return pn;
	}
	public void setPn(Integer pn) {
		this.pn = pn;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPagenum() {
		return pagenum;
	}
	public void setPagenum(Integer pagenum) {
		this.pagenum = pagenum;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	
	

}
