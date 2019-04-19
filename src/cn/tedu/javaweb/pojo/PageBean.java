package cn.tedu.javaweb.pojo;

import java.util.ArrayList;

//���еķ�ҳ���������PageBean
//<T>��ʾ�Ƿ��ͣ���������PageBean<Book> PageBean<User> ��book���ǹ���book�ģ���user���ǹ���user��
public class PageBean<T> {
	
	//ͨ���������ֶΣ����Լ��������һҳ��һҳ������
	private int currentPage;//��ǰҳ
	private int pageSize;//ÿһҳ������
	//ͨ���������ֶμ����βҳ������
	private int totalPages;//�ܹ�����ҳ
	private int totalCount;//�������ݵ�����
	//��ҳ�����ݣ���Ҫ�������
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
