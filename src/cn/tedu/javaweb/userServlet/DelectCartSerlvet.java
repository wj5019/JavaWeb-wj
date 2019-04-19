package cn.tedu.javaweb.userServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/page/deleteCartServlet")
public class DelectCartSerlvet extends HttpServlet{
	//服务入口
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取页面url的参数
		
		//调用dao层：根据rid，删除cart表的数据
		
		//页面的跳转：当删除数据后，需要重新刷新页面
		//所谓的刷新页面，即是重新一次allCartSerlcet请求
		request.getRequestDispatcher("allCartServlet").forward(request, response);
	}
	

}
