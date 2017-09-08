package member;

import java.sql.Date;
import java.sql.Timestamp;

public class MemberDataBean {
	private String id;				//아이디	
	private String passwd;			//비밀번호
	private String name;			//이름
	private Timestamp reg_date;		//생성날짜
	private int num;				//맴버 번호
	private String email;			//이메일
	private String address;			//주소
	private int sex;				//성별
	private Date birth;				//생년월일
	
	//사용자에게 표시되는것 
	//아이디, 비밀번호, 이름, 이메일 , 주소, 성별, 생년월일
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
