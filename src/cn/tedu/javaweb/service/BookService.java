package cn.tedu.javaweb.service;

import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.pojo.PageBean;

//�ӿڣ�ֻ�Ƕ���ҵ����Ҫ�ķ���������û�о���ʵ��
public interface BookService {
/*
 *  ���ݾ���ҵ��ȥ����
 *  һ����˵������ɾ�Ĳ�ķ���
 *  ��Щ������dao��ķ���һһ��Ӧ
 * 
 */
	//ר�����ڷ�ҳ��ѯ�ķ���
	//������currentPage/pageSize
	//����ֵ���ͣ���Ҫ��������ҳ�������ࡿȥ��ȡֵ
	public PageBean<Book> selectBookByPage(String currentPage,String pageSize);
}
