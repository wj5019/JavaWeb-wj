package cn.tedu.javaweb.service;

import java.util.ArrayList;

import cn.tedu.javaweb.dao.BookDao;
import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.pojo.PageBean;

//ҵ���߼���ľ���ʵ����
//�ɳ���Ա�ֶ���д����������ҵ��
public class BookServiceImpl implements BookService{//�ӿڣ����漸�в����ֶ�д

	@Override
	public PageBean<Book> selectBookByPage(String currentPage, String pageSize) {
		// ����һ����ҳ����
		PageBean<Book> pageBean = new PageBean<Book>();
		//pageBean���󣺷�װ��ǰ��ҳ��  //�������nullĬ�ϵ�һҳ��������null����ת��
		if (currentPage == null) {
			currentPage = "1";//Ĭ����ҳ
		}
		int i_currentPage = Integer.parseInt(currentPage);
		pageBean.setCurrentPage(Integer.parseInt(currentPage));
		//pageBean���󣺷�װÿһҳ������
		if (pageSize == null) {
			pageSize = "8";//Ĭ��ÿһҳ8����
		}
		int i_pageSize = Integer.parseInt(pageSize);
		//pageBean���󣺷�װ�鼮��������
		/*1.��Ҫ����dao��ķ�����selectAll(),��ȡȫ���鼮
		 * 2.����list���ϣ���ȡlist.size()����������
		 * 
		 */
		BookDao dao = new BookDao();
		ArrayList<Book> books = dao.selectAll();
		int i_totalCount = books.size();
		pageBean.setTotalCount(i_totalCount);
		//pageBean���󣺷�װ��ҳ��
		//��ʽ����ҳ��=������/ÿһҳ������+1
		//��������ҳ��=100/8=12+1
		//����ȡ����Math.ceil()
		//����ȡ����Math.floor()
		int i_totalPages = (int) (Math.floor(i_totalCount/i_pageSize)+1);//����ȡ��
		pageBean.setTotalPages(i_totalPages);
		//pageBean���󣺷�װ��ҳ������
		//��Ҫ����dao��ķ���
		//��������һҳ��0��ʼ����8����¼ -->��0����7
		//      �ڶ�ҳ��8��ʼ����8����¼ -->��8����15
		//      ����ҳ��16��ʼ����8����¼-->��16����23
		//��ʽ��startRow = (currentPage-1)*pageSize;
		int startRow = (i_currentPage - 1)*i_pageSize;
		ArrayList<Book> list = dao.selectBookByPages(startRow, i_pageSize);
		pageBean.setPageList(list);//��װ
		return pageBean;
	}

}
