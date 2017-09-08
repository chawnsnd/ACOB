package member;

import java.sql.Date;
import java.sql.Timestamp;

public class LogonDataBean {
	//필요한 정보 더 넣기
	private String id;			//id		1
	private String passwd;		//비밀번호		2
	private String name;		//이름		3
	private Timestamp reg_date;	//생성날짜		4
	private int num;			//멤버 번호	5
	private String email;		//이메일		6
	private String address;		//주소		7
	private int sex;			//성별		8	
	private Date birth;			//생년 월일	9
	
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
