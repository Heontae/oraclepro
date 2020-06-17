package com.javaex.phone;

public class PersonVo {

	//필드
	private int Person_id;
	private String name,hp,company;
	
	//g/s
	public int getPerson_id() {
		return Person_id;
	}
	public void setPerson_id(int person_id) {
		Person_id = person_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	
	//생성자
	public PersonVo() {
		
	}
	
	public PersonVo(int person_id) {
		this.Person_id = person_id;
	}
	
	
	public PersonVo(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	public PersonVo(int person_id, String name, String hp, String company) {
		this.Person_id = person_id;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	//일반메소드

	@Override
	public String toString() {
		return "PersonVo [Person_id=" + Person_id + ", name=" + name + ", hp=" + hp + ", company=" + company + "]";
	}

	}
