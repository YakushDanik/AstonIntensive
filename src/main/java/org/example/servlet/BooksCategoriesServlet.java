package org.example.servlet;

import com.google.gson.Gson;
import org.example.dao.BooksCategoriesDao;
import org.example.model.BooksCategories;
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

@WebServlet("/api/bookscategories/*")
public class BooksCategoriesServlet extends HttpServlet {

    private BooksCategoriesDao booksCategoriesDao;
    private Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DataSourceFactory.getConnection();
            booksCategoriesDao = new BooksCategoriesDao(connection);
        } catch (SQLException e) {
            throw new ServletException("Unable to initialize BooksCategoriesDao", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        try {
            List<BooksCategories> booksCategories = booksCategoriesDao.findAll();
            resp.getWriter().write(gson.toJson(booksCategories));
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BooksCategories booksCategory = gson.fromJson(req.getReader(), BooksCategories.class);
        try {
            if (booksCategoriesDao.save(booksCategory)) {
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
        BooksCategories booksCategory = gson.fromJson(req.getReader(), BooksCategories.class);
        try {
            if (booksCategoriesDao.delete(booksCategory)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
