package org.example.servlet;

import com.google.gson.Gson;
import org.example.dao.AuthorsDao;
import org.example.model.Authors;
import org.example.dao.DataSourceFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet("/api/authors/*")
public class AuthorsServlet extends HttpServlet {

    private AuthorsDao authorsDao;
    private Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DataSourceFactory.getConnection();
            authorsDao = new AuthorsDao(connection);
        } catch (SQLException e) {
            throw new ServletException("Unable to initialize AuthorDao", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        resp.setContentType("application/json");

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Get all authors
                List<Authors> authors = authorsDao.findAll();
                resp.getWriter().write(gson.toJson(authors));
            } else {
                // Get author by ID
                int id = Integer.parseInt(pathInfo.substring(1));
                Optional<Authors> authors = authorsDao.find(id);
                if (authors.isPresent()) {
                    resp.getWriter().write(gson.toJson(authors.get()));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Authors author = gson.fromJson(req.getReader(), Authors.class);
        try {
            if (authorsDao.save(author)) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        try {
            if (authorsDao.delete(new Authors(id, null))) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
