package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dal.DBcon;
import com.dao.UserDao;
import com.model.Login;
import com.model.Registration;

public class UserService implements UserDao {

	public boolean registration(Registration reg) {

		boolean res = false;
		String sql = "insert into EMPLOYEE(EMPNAME,EMAIL,CITY)values(?,?,?)";
		System.out.println("inside user service");
		DBcon dbc = new DBcon();
		Connection conn = dbc.getConnection();

		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, reg.getUname());
			pst.setString(2, reg.getEmail());
			pst.setString(3, reg.getCity());

			int i = pst.executeUpdate();
			if (i > 0) {
				System.out.println("user is registered!");
				res = true;
				return res;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return res;

	}

	@Override
	public boolean login(Login log, HttpServletRequest request, HttpServletResponse response) {
		String sql = "select * from employee";
		System.out.println("inside login service");
		// TODO Auto-generated method stub

		DBcon dbc = new DBcon();
		Connection conn = dbc.getConnection();

		try {

			PreparedStatement pst = conn.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();
			String inputName = log.getUname();
			String inputEmail = log.getEmail();

			String resName = "";
			String resEmail = "";
			String dummy = "";
			while (rs.next()) {
				resName = rs.getString(1);
				resEmail = rs.getString(2);
				dummy = rs.getString(3);
				if (inputName.equals(resName) && inputEmail.equals(resEmail)) {
					System.out.println(("user validation is successfull"));
					System.out.println("userInput was : " + inputName + " userEmail was : " + inputEmail);

					HttpSession session = request.getSession();
					session.setAttribute("sesname", resName);
					
					response.sendRedirect("Loginsuccess.jsp");

					return true;
				}

			}
			System.out.println("user validation error!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return false;
	}

}
