package cn.tedu.javaweb.pojo;

import java.util.ArrayList;

//所有的分页都依赖与该PageBean
//<T>表示是泛型！！，举例PageBean<Book> PageBean<User> 放book就是关于book的，放user就是关于user的
public class PageBean<T> {
	
	//通过这两个字段，可以计算出，上一页下一页的内容
	private int currentPage;//当前页
	private int pageSize;//每一页的数量
	//通过这两个字段计算出尾页的内容
	private int totalPages;//总共多少页
	private int totalCount;//所有数据的数量
	//分页的数据：需要存放起来
	private ArrayList<T> pageList;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public ArrayList<T> getPageList() {
		return pageList;
	}
	public void setPageList(ArrayList<T> pageList) {
		this.pageList = pageList;
	}
	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalPages=" + totalPages
				+ ", totalCount=" + totalCount + ", pageList=" + pageList + "]";
	}

}
