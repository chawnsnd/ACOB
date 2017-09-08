package goods;

//상품 빈
public class GoodsDataBean {
	//필요한 정보 더 넣기
	private int no; 		//상품 관리번호
	private String name;	//상품이름
	private int price;	//가격
	private String company;	//제조회사
	private int stock; //재고
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
