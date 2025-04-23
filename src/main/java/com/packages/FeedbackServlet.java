package com.packages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/submitFeedback")
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Getting user feedback from the form
        String name = request.getParameter("name");
        String feedback = request.getParameter("feedback");

        // Establishing database connection and inserting data
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO feedback (name, feedback) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, feedback);

            int result = ps.executeUpdate();
            if (result > 0) {
                response.getWriter().write("Feedback submitted successfully!");
            } else {
                response.getWriter().write("Failed to submit feedback.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Database error: " + e.getMessage());
        }
    }
}
