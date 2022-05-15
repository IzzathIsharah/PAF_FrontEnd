package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.Connect;

public class power_consumption {

	PreparedStatement preStatement;
	
	public String addPowerModel(String powerConID,String pname,String pdistrict,String pzipCode,int punits,int ptotal) {
		Connection con;
		PreparedStatement preStatement;
		
		try {
			con = Connect.getConnection();
			
			preStatement = con.prepareStatement("insert into power (powerConID,name,pdistrict,pzipCode,punits,ptotal) values (?,?,?,?,?,?)");
			preStatement.setString(1, powerConID);
			preStatement.setString(2, pname);
			preStatement.setString(3, pdistrict);
			preStatement.setString(4, pzipCode);
			preStatement.setInt(5, punits);
			preStatement.setInt(6, ptotal);
			preStatement.execute();
			preStatement.close();
			con.close();
			return "Successful";
		
		}catch (ClassNotFoundException | SQLException  e) {
			return "Error";
		}
	}
	
	public String getPowerModel() {
		
		Connection con;
		String data="";
		
		try {
			
			con = Connect.getConnection();
			preStatement = con.prepareStatement("SELECT * FROM power");
			
			ResultSet resultSet = preStatement.executeQuery();
			
			data = "<table style='border: 1px solid black;'>"
		            +"<tr>"
		            +"<th style='border: 1px solid black;'>ID</th>"
	                +"<th style='border: 1px solid black;'>Power Consumption ID</th>"
	                +"<th style='border: 1px solid black;'>User Name</th>"
	                +"<th style='border: 1px solid black;'>District Name</th>"
	                +"<th style='border: 1px solid black;'>District Zip Code</th>"
	                +"<th style='border: 1px solid black;'>Power Units</th>"
	                +"<th style='border: 1px solid black;'>TotalPower Consumption</th>"
	                +"<th style='border: 1px solid black;'>Edit/Delete</th>"
	                +"</tr>";
			
			while (resultSet.next()) {
				
				String button = "<button>Delete</button>";
				
				data = data+"<tr><td style='border: 1px solid black;'>"+resultSet.getString(1)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(2)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(3)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(4)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(5)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(6)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(7)+"</td>"
						+ "<td style='border: 1px solid black;'><button>Edit</button><button>Delete</button></td>"
					  + "</tr>";
				
			}
			
			preStatement.close();
			con.close();
			
		}catch (ClassNotFoundException | SQLException  e) {

			System.out.println(e.getMessage());
		}
		
		return data+"</table>";
	}

	public String editPowerModel(int id,String powerConID,String pname,String pdistrict,String pzipCode,int punits,int ptotal) {
		Connection con;
		
		try {
			con = Connect.getConnection();
			
				preStatement = con.prepareStatement("UPDATE power SET powerConID=?,pname=?,pdistrict=?,pzipCode=?,punits=?,ptotal=? where id=?");
				preStatement.setString(1, powerConID);
				preStatement.setString(2, pname);
				preStatement.setString(3, pdistrict);
				preStatement.setString(4, pzipCode);
				preStatement.setInt(5, punits);
				preStatement.setInt(6, ptotal);
				preStatement.setInt(7,id);
				preStatement.execute();
				preStatement.close();
				con.close();
				return "Successful";
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			return "Error";
		}
	}

	public String deletePowerModel(int id) {
		Connection con;
		
		try {
			con = Connect.getConnection();
			
			preStatement = con.prepareStatement("DELETE FROM power WHERE id=?");
			preStatement.setInt(1, id);
			preStatement.execute();
			
			return "Successful";
		
		}catch (ClassNotFoundException | SQLException  e) {
			return "Error";
		}
	}
	
}
