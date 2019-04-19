package cn.tedu.javaweb.pojo;

//Model：数据模型层 数据封装
public class Cart {
	private Integer rid;
	private String uid;//用户的主键phone
	private String book;//书籍的主键isbn
	private Integer count;//购物车的数量
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Cart [rid=" + rid + ", uid=" + uid + ", book=" + book + ", count=" + count + "]";
	}
	

}
