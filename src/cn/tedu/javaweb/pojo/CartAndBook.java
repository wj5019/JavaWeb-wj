package cn.tedu.javaweb.pojo;

//Model层：数据模型
public class CartAndBook {
	private Cart Cart;//购物车信息
	private Book Book;//书籍信息
	public Cart getCart() {
		return Cart;
	}
	public void setCart(Cart cart) {
		Cart = cart;
	}
	public Book getBook() {
		return Book;
	}
	public void setBook(Book book) {
		Book = book;
	}
	@Override
	public String toString() {
		return "CartAndBook [Cart=" + Cart + ", Book=" + Book + "]";
	}

}
