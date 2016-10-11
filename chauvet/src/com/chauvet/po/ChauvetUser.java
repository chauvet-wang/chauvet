package com.chauvet.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="chauvet_user")
public class ChauvetUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private int sex;
	private int age;
	private Date createTime;
	private Date registerTime;
	
	
//	@Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
//    @Column(name="id",length = 32)
	/*@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id",length = 32)*/
	
    @Id  
    @GenericGenerator(name="systemUUID",strategy="uuid")  
    @GeneratedValue(generator="systemUUID")  
    @Column(name = "id", insertable = true, updatable = true, nullable = false,length = 32)  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="name",length=32)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="sex")
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	@Column(name="age")
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="createTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	//DATE            - java.sql.Date        
    //TIME            - java.sql.Time        
    //TIMESTAMP - java.sql.Timestamp        
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="registerTime")
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	
	
	
	
	
}
