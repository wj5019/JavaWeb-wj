package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.BookDao;
import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.pojo.PageBean;
import cn.tedu.javaweb.service.BookService;
import cn.tedu.javaweb.service.BookServiceImpl;
//��ѯ�����鼮serlvrt
public class AllBookServlet extends HttpServlet{
	//��������ķ������
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����request�ı����ʽ
		request.setCharacterEncoding("utf-8");
		//����response�ı����ʽ
		response.setContentType("text/html;charaset=utf-8");
		
		/*//����dao��ķ�������ɲ�ѯ�鼮�Ĳ���
		BookDao dao=new BookDao();
		//���ò�ѯȫ�����ݵķ���
		ArrayList<Book> list=dao.selectAll();
		//�����ݼ��Ϸ�װ��request������ ת������ �ض��򲻴���
		request.setAttribute("books", list);//��������*/
		
		//��ȡҳ���е����������currentPage/pageSize
		String currentPage = request.getParameter("currentPage");//��ǰҳ
		String pageSize = request.getParameter("pageSize");//ÿһҳ������ 
		/*��Ҫ����ҵ���߼���ר�Ŵ����ҳҵ��
		 * 1.����һ���ӿڣ�Bookservice��ҵ��Ľӿڣ�
		 * 2.����һ��ʵ���ࣺBookserviceImpl��ҵ���ʵ���ࣩ
		 */
		BookService bs = new BookServiceImpl();
		//�����˵���
		PageBean<Book> pg = bs.selectBookByPage(currentPage, pageSize);
		request.setAttribute("pg", pg);
		request.setAttribute("books", pg.getPageList());
		
		//ҳ����ת
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
