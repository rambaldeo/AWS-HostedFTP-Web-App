package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TestDBConnection")
public class TestDBConnection extends HttpServlet {

	String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false&serverTimezone=UTC";
	String user = "root";
	String password = "201059737";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			System.out.println("Connection was successful");
			response.getWriter().println("Connected to database!");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Failed to connect: " + e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usernameInput = request.getParameter("username");
		String passwordInput = request.getParameter("password");

		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			System.out.println("Successfully connected");
			PreparedStatement query = conn
					.prepareStatement("SELECT userId FROM users WHERE username = ? AND password = ?");
			query.setString(1, usernameInput);
			query.setString(2, passwordInput);

			ResultSet rs = query.executeQuery();

			response.getWriter().println("The userId is " + rs);

		} catch (Exception e) {

		}
	}
}
