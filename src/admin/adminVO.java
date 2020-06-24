package admin;

import java.util.Date;

public class adminVO {
	private String id ;
	private int num ;
	private String title ;
	private String content ;
	Date date ;
	private String pos ;
	private String dept ;
	
	public adminVO(String id, int num, String title, String content, Date date, String pos, String dept) {
		super();
		this.id = id;
		this.num = num;
		this.title = title;
		this.content = content;
		this.date = date;
		this.pos = pos;
		this.dept = dept;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
}
