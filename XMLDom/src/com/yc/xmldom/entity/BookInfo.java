package com.yc.xmldom.entity;

public class BookInfo {
	private int id;
	private String bookName;
	private String bookAuthor;
	private String bookISBN;
	private double bookPrice;
	@Override
	public String toString() {
		return "BookInfo [id=" + id + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", bookISBN=" + bookISBN
				+ ", bookPrice=" + bookPrice + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double d) {
		this.bookPrice = d;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookAuthor == null) ? 0 : bookAuthor.hashCode());
		result = prime * result + ((bookISBN == null) ? 0 : bookISBN.hashCode());
		result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(bookPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookInfo other = (BookInfo) obj;
		if (bookAuthor == null) {
			if (other.bookAuthor != null)
				return false;
		} else if (!bookAuthor.equals(other.bookAuthor))
			return false;
		if (bookISBN == null) {
			if (other.bookISBN != null)
				return false;
		} else if (!bookISBN.equals(other.bookISBN))
			return false;
		if (bookName == null) {
			if (other.bookName != null)
				return false;
		} else if (!bookName.equals(other.bookName))
			return false;
		if (Double.doubleToLongBits(bookPrice) != Double.doubleToLongBits(other.bookPrice))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
