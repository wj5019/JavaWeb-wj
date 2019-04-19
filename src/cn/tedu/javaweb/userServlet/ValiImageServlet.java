package cn.tedu.javaweb.userServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.utils.VerifyCode;


//专门生成一张验证码图片返回给浏览器
@WebServlet("/user/page/valiImageServlet")
public class ValiImageServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置response对象的缓存机制
		//控制浏览器不要缓存图片
		response.setDateHeader("Expires", -1);//响应头的过期时间，马上过期
		//Cache-Control:缓存控制
		//no-cache：没有缓存
		response.setHeader("Cache-Control", "no-cache");//设置一个缓存
		//调用验证码的对象的方法
		VerifyCode vc = new VerifyCode();
		//将图片保存到response响应的缓冲区
		vc.drawImage(response.getOutputStream());
		//获取验证码上的字母或数字
		String code = vc.getCode();
		//将code保存到session对象中
		request.getSession().setAttribute("code",code);
		
	}
}
