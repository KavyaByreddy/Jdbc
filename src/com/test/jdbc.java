package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc {
	static Scanner sc=new Scanner(System.in);
	static Connection con;
	public static void main(String[] args) {
	JDBCCrud obj=new JDBCCrud();
	try {
		//load the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//establish connection
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pro18", "root", "root");
		
		System.out.println("Enter your option 1 - insert, 2-read 3-update 4-delete");
		int opt=Integer.parseInt(sc.nextLine());
		switch(opt){
			case 1:
				obj.insertData();
				break;
			case 2:
				obj.readData();
				break;
			case 3:
				obj.updateData();
				break;
			case 4:
				obj.deleteEmp();
				break;
			default:
				obj.chouseOption();	
		}
	}catch(Exception e) {
		System.out.println("Unable to connet");
	}
	
	
	

	}
	private void chouseOption() {
		// TODO Auto-generated method stub
		
	}
	private void insertData() throws SQLException {
		String sql_st="insert into emp values(?,?,?)";
		PreparedStatement st=con.prepareStatement(sql_st);
		
		System.out.println("Enter Emp Id:");
		int empId=Integer.parseInt(sc.nextLine());
		st.setInt(1, empId);
		
		System.out.println("Enter Emp Name");
		st.setString(2, sc.nextLine());
		
		System.out.println("Enter Emp Salary:");
		st.setInt(3, Integer.parseInt(sc.nextLine()));
		
		int row=st.executeUpdate();
		if(row>0) {
			System.out.println("Data Inserted");
		}
		else {
			System.out.println("Not Inserted");
		}
		
	}
	private void readData() {
		try {
			Statement st=con.createStatement();
			System.out.println("Enter Emp Id:");
			int empId=sc.nextInt();
			String sql_St="select * from emp where eid="+empId;
			ResultSet rs=st.executeQuery(sql_St);
			if(rs.next()) {
				int eid=rs.getInt(1);
				String ename=rs.getString(2);
				int esal = rs.getInt(3);
				System.out.println("Emp Id:"+eid+"-Emp Name:"+ename+"-E Salary"+esal);
			}
			else {
				System.out.println("No Data");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Data Displayed");
	}
	private void updateData() throws SQLException {
		System.out.println("Enter Emp Id:");
		int empId=sc.nextInt();
		
		System.out.println("Enter Emp Name to Update:");
		String ename = sc.nextLine();
		System.out.println(ename);
		//String sql_St = "update emp set ename = 'Rahul Gandhi' where eid=?";
		String sql_St = "update emp set ename =? where eid=?";
		PreparedStatement st=con.prepareStatement(sql_St);
		
		st.setInt(2, empId);
		st.setString(1, ename);
		
		int row=st.executeUpdate();
		if(row>0) {
			System.out.println("Data Updated");
		}
		else {
			System.out.println("Data Not Updated");
		}
		;
	}
	private void deleteEmp() throws SQLException {
		System.out.println("Enter Emp Id to delete");
		int eid=sc.nextInt();
		String sql_st="delete from emp where eid="+eid;
		Statement st=con.createStatement();
		int rows=st.executeUpdate(sql_st);
		if(rows>0) {
			System.out.println("Emp Rec deleted");
		}
		else {
			System.out.println("Unable to Delete");
		}
	}

}