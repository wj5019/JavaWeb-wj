package cn.tedu.javaweb.adminServlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

import cn.tedu.javaweb.dao.BookDao;
import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.utils.DateConverter;

@WebServlet("/admin/page/productAddServlet")
public class ProductAddServlet extends HttpServlet{
        //提供服务入口的方法：专门进行商品文件上传
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //先判断form表单是否为上传文件的类型
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		System.out.println(isMultipart);
		//获取上传文件目标路径
		String dirPath=request.getServletContext().getRealPath("/user/img/goods");
		System.out.println("上传的目标路径:"+dirPath);
		//获取文件上传的解析器
		//专门用于解析上传的图片或者文件等等
		ServletFileUpload upload=new ServletFileUpload();
		if (isMultipart) {//如果是文件上传的类型的form表单
			//通过文件上传解析器，获取form表单的迭代器
			//该迭代器中存放了form所有数据
			InputStream in = null;//输入流 -读取表单数据到程序中
			FileOutputStream out = null;//输出流 -从程序中写出表单数据
			try {
				 FileItemIterator items = upload.getItemIterator(request);
				 //使用while循环遍历迭代器
				 while (items.hasNext()) {//如果存在下一条记录
					 //数据需要通过流的形式去获取
					 FileItemStream item = items.next();
					 in = item.openStream();//打开文本框的流，获取数据
					//判断item这一条记录是普通文本还是上传的文件
					 if(item.isFormField()) {//普通文本框
						 //获取普通文本框的name值：举例name="isbn"
						 String fieldName = item.getFieldName();
						 //获取文本框的value值:举例value="98565225"
						 String fieldValue = Streams.asString(in,"utf-8");
						 System.out.println("普通文本框："+fieldName+"="+fieldValue);
						 //把数据封装到request对象中：以key-value键值对形式
						 request.setAttribute(fieldName, fieldValue);
					 }else {//上传文件
						 //获取上传文本框的内容：举例：上传了一张bilibili.png
						 String imageName = item.getName();//图片的名字
						 System.out.println("上传图片的名字："+imageName);
						 if(imageName == null || "".equals(imageName)) {
							 //如果没有上传的图片那么不要继续执行下面的代码了
							 continue;//返回while循环执行下一条记录
						 }
						 //需要获取上传文本框的名字：举例<input type="file" name="detail1big">
						 String fieldName = item.getFieldName();
						 //生成一张图片的完整路径，举例：/day03/user/img/goods/<%=isbn%>/detail1big.jpg
						 //File.separator按照不同的操作系统生成正斜杠反斜杠
						 String imgPath = dirPath+File.separator+request.getAttribute("isbn")+File.separator+fieldName+".jpg";
						 System.out.println("最终图片的位置:"+imgPath);
						 //根据图片的最终位置，生成文件夹以及图片文件
						 File file = new File(imgPath);//根据路径参数生成文件对象
						//创建了一个名称为isbn编号的文件夹
						 file.getParentFile().mkdir();
						 //在该文件夹中创建一个新的文件
						 file.createNewFile();//也就是目标图片
					/////现在有了空瓶，通过流的形式把图片灌进去
						 //使用输出流输出一张图片到目标位置
						 out = new FileOutputStream(file);
						 //使用缓冲区进行流的加载
						 //缓冲区指的是：字节码数组，把图片按照字节存起来
						 byte[] buf = new byte[1024];//存放1024个字节
						 int len = 0;//读取字节时的编号，最后一个字节为-1
						 while((len = in.read(buf))!=-1) {//循环读取图片的内容
							 //不等于-1时证明还没有把图片完全生成
							 out.write(buf, 0, len);//0表示偏移量，从0开始到len结束
						 }
					 }
					
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(in!=null) {
				in.close();
				}
				if (out!=null) {
					out.close();
				}
				
			}
		}
		
		
		//调用dao层的方法，插入book书籍的基本信息
		Book book = new Book();
		book.setAuthor(request.getAttribute("author").toString());
		book.setEdition(Integer.parseInt(request.getAttribute("edition").toString()));
		book.setForm(request.getAttribute("form").toString());
		book.setFormat(request.getAttribute("format").toString());
		book.setIsbn(request.getAttribute("isbn").toString());
		book.setPackaging(request.getAttribute("packaging").toString());
		book.setPages(Integer.parseInt(request.getAttribute("pages").toString()));
		book.setPress(request.getAttribute("press").toString());
		book.setPrice(Double.parseDouble(request.getAttribute("price").toString()));
		try {//DateConverter工具类日期格式转换
			book.setPublished(DateConverter.parseDate(request.getAttribute("published").toString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		book.setTitle(request.getAttribute("title").toString());
		book.setWords(Integer.parseInt(request.getAttribute("words").toString()));
		Writer writer = response.getWriter();
		BookDao dao = new BookDao();
		dao.insert(book);
		writer.write("yes");
		writer.close();


	}
}
