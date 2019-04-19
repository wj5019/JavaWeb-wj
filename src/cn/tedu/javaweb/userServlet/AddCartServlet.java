package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.CartDao;
import cn.tedu.javaweb.pojo.Cart;
import cn.tedu.javaweb.pojo.User;

//controller���Ʋ�
public class AddCartServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����request�ı����ʽ
		request.setCharacterEncoding("utf-8");
		//��ȡҳ��������url�Ĳ���
		String newcount=request.getParameter("count");
		String isbn=request.getParameter("product");
		User user=(User)request.getSession().getAttribute("user");
		String uid=user.getPhone();
		//��Ҫ����dao��ķ���
		/*1.�ж����ݿ��У��Ƿ��Ѿ����ڸñ���Ĺ��ﳵ��Ϣ
		 * 2.���ԭ������ӹ����ﳵ����ô�Թ��ﳵ��Ϣ����
		 * 3.���ԭ��û����ӹ����ﳵ��Ϣ����ô�Թ��ﳵ��Ϣ����
		 */
		CartDao dao=new CartDao();
		Cart cart=dao.selectByUidAndIsbn(uid,isbn);
		if (cart!=null) {//��ʾԭ���Ѿ���ӹ����ﳵ
			int oldcount=cart.getCount();
			int totalcount=oldcount+Integer.parseInt(newcount);
			dao.update(cart.getRid(),totalcount);
		}else {//��ʾԭ��û����ӹ����ﳵ
			dao.insert(uid, isbn,Integer.parseInt(newcount));
		}
		//ʹ��io�����ظ������һ���ַ������
		Writer out=response.getWriter();
		out.write("yes");
		out.close();
	}

}
