package cn.tedu.javaweb.pojo;

//Model�㣺����ģ��
public class CartAndBook {
	private Cart Cart;//���ﳵ��Ϣ
	private Book Book;//�鼮��Ϣ
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
