package org.example.servlet;

import com.google.gson.Gson;
import org.example.dao.CategoriesDao;
import org.example.model.Categories;
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

@WebServlet("/api/categories/*")
public class CategoriesServlet extends HttpServlet {

    private CategoriesDao categoriesDao;
    private Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DataSourceFactory.getConnection();
            categoriesDao = new CategoriesDao(connection);
        } catch (SQLException e) {
            throw new ServletException("Unable to initialize CategoriesDao", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        resp.setContentType("application/json");

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Get all categories
                List<Categories> categories = categoriesDao.findAll();
                resp.getWriter().write(gson.toJson(categories));
            } else {
                // Get category by ID
                int id = Integer.parseInt(pathInfo.substring(1));
                Optional<Categories> category = categoriesDao.find(id);
                if (category.isPresent()) {
                    resp.getWriter().write(gson.toJson(category.get()));
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
        Categories category = gson.fromJson(req.getReader(), Categories.class);
        try {
            if (categoriesDao.save(category)) {
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
            if (categoriesDao.delete(new Categories(id, null))) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
