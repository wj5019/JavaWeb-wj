package cn.tedu.javaweb.pojo;
//model�㣺����ģ�Ͳ�
//�������ݵķ�װ
public class Collect {
	private Integer rid;//�ղؼе���������������
	private String uid;//�û�����������û����ֻ���
	private String book;//��ǵ��������鼮��isbn���
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	@Override
	public String toString() {
		return "Collect [rid=" + rid + ", uid=" + uid + ", book=" + book + "]";
	}
}
