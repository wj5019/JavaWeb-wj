package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.CollectDao;
import cn.tedu.javaweb.pojo.Collect;

//���Ʋ㣺controller
public class ClickCollectServlet extends HttpServlet{
   //�������
	@Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  //����request�����ʽ
		request.setCharacterEncoding("utf-8");
		//��ȡҳ��url·������
		String uid=request.getParameter("userId");
		String isbn=request.getParameter("product");
    /*1.��ѯ���ݿ����Ƿ��Ѿ��ղ�
     * 2.������ղأ�������ղذ�ť��ʱ������ȡ���ղ�
     * 3.���δ�ղأ�������ղذ�ť��ʱ����������ղ�
     */
		CollectDao dao=new CollectDao();
		//����һ��collect���󣬻�ȡ�ղ���Ϣ
		Collect col=dao.selectByUidAndIsbn(uid, isbn);
		if(col!=null){//��ʾԭ�����ղأ��ٴε��ʱȡ��
			dao.delete(uid, isbn);
		}else{//��ʾԭ��δ�ղأ��ٴε��ʱ���
			dao.insert(uid, isbn);
		}
		//ʹ��IO�������ajax����ķ���
		Writer out=response.getWriter();
		out.write("yes");
		out.close();
}
}