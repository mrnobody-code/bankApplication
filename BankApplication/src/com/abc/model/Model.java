package com.abc.model;

import java.io.FileInputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class Model {
	private String fname;
	private String lname;
	private String accno;
	private String email;
	private String phone;
	private int balance;
	private String password;
	private String raccno;
	private int amount;
	
	PreparedStatement pstmt = null;
	Connection con = null;
	ResultSet result =null;
	
	private ArrayList<Integer> al = new ArrayList<Integer>();
	private ArrayList<String> sal = new ArrayList<String>();
	private ArrayList<String> ral = new ArrayList<String>();
	
	

	public ArrayList<Integer> getAl() {
		return al;
	}

	public void setAl(ArrayList<Integer> al) {
		this.al = al;
	}

	public ArrayList<String> getSal() {
		return sal;
	}

	public void setSal(ArrayList<String> sal) {
		this.sal = sal;
	}

	public ArrayList<String> getRal() {
		return ral;
	}

	public void setRal(ArrayList<String> ral) {
		this.ral = ral;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getRaccno() {
		return raccno;
	}

	public void setRaccno(String raccno) {
		this.raccno = raccno;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	//Loading the drivers
	public Model() {
		try {
			FileInputStream fs = new FileInputStream("E:\\JAVA PROJECT WORKBENCH\\BankApplication\\src\\com\\abc\\model\\connection.properties");
			Properties p = new Properties();
			p.load(fs);
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String password = p.getProperty("password");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,password);
			System.out.println("Driver Loaded Successfully..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean register() throws SQLException {
		
		String s1="select accno from abcbank where accno=?";
		pstmt = con.prepareStatement(s1);
		pstmt.setString(1, accno);
		result = pstmt.executeQuery();
		if(result.next()==true) {
			return false;
		}
		else {
			String s = "insert into abcbank(fname,lname,accno,email,phone,balance,password) values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(s);
			
			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setString(3, accno);
			pstmt.setString(4, email);
			pstmt.setString(5, phone);
			pstmt.setInt(6, balance);
			pstmt.setString(7, password);
			
			int x = pstmt.executeUpdate();
			if(x>0) {
				return true;
			}
		}
		return false;
	}

	public boolean login() throws SQLException {
		String s="select * from abcbank where email=? and password=?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		
		result = pstmt.executeQuery();
		while(result.next()==true) {
			accno = result.getString("accno");
			return true;
		}
		return false;
	}

	public boolean checkBalance() throws SQLException {
		String s="select balance from abcbank where accno=?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, accno);
		
		result = pstmt.executeQuery();
		while(result.next()==true) {
			balance = result.getInt("balance");
			return true;
		}
		return false;
	}

	public boolean forget() throws SQLException {
		String s = "select * from abcbank where accno=? and email=?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, accno);
		pstmt.setString(2, email);
		
		result = pstmt.executeQuery();
		while(result.next()==true) {
			phone = result.getString("phone");
			accno = result.getString("accno");
			return true;
		}
		return false;
	}

	public boolean change() throws SQLException {
		String s="update abcbank set password=? where accno=?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, password);
		pstmt.setString(2, accno);
		
		int x = pstmt.executeUpdate();
		if(x>0) {
			return true;
		}
		return false;
	}

	public boolean transfer() throws SQLException {
		String s = "select balance from abcbank where accno=?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, accno);
		
		result = pstmt.executeQuery();
		if(result.next()==true) {
			balance = result.getInt("balance");
			
			if(balance>=amount) {
				String s1 = "update abcbank set balance=balance-? where accno=?";
				pstmt = con.prepareStatement(s1);
				pstmt.setInt(1, amount);
				pstmt.setString(2, accno);
				
				int x = pstmt.executeUpdate();
				if(x>0) {
					String s2 = "select accno,phone from abcbank where accno=?";
					pstmt = con.prepareStatement(s2);
					pstmt.setString(1, raccno);
				
					result = pstmt.executeQuery();
					
					if(result.next()==true) {
						phone = result.getString("phone");
						String s3 = "update abcbank set balance=balance+? where accno=?";
						pstmt = con.prepareStatement(s3);
						pstmt.setInt(1, amount);
						pstmt.setString(2, raccno);
						
						int y = pstmt.executeUpdate();
						if(y>0) {
							String s4 = "insert into getstatement(accno,raccno,balance) values(?,?,?)";
							pstmt = con.prepareStatement(s4);
							pstmt.setString(1, accno);
							pstmt.setString(2, raccno);
							pstmt.setInt(3, amount);
							
							int z = pstmt.executeUpdate();
							if(z>0) {
								return true;
							}
						}
					}
					else {
						return false;
					}
				}
			}
			else {
				return false;
			}
		}
		return false;
	}

	public ArrayList<Integer> getStatement() throws SQLException {
		String s = "select * from getstatement where accno=?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, accno);
		
		result = pstmt.executeQuery();
		
		while(result.next()==true) {
			sal.add(result.getString("accno"));
			ral.add(result.getString("raccno"));
			al.add(result.getInt("balance"));
		}
		return al;
	}

	public boolean applyLoan() throws SQLException {
		String s = "select * from abcbank where accno=?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, accno);
		
		result = pstmt.executeQuery();
		
		while(result.next()==true) {
			fname = result.getString("fname");
			email = result.getString("email");
			return true;
		}
		return false;
	}

}
