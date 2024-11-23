package org.example.servlet;

import com.google.gson.Gson;
import org.example.dao.BooksCategoriesDao;
import org.example.model.BooksCategories;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/books-categories/*")
public class BooksCategoriesServlet extends HttpServlet {

    private final BooksCategoriesDao booksCategoriesDao = new BooksCategoriesDao();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        resp.setContentType("application/json");

        if (pathInfo == null || pathInfo.equals("/")) {
            List<BooksCategories> booksCategories = booksCategoriesDao.findAll();
            resp.getWriter().write(gson.toJson(booksCategories));
        } else {
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                BooksCategories booksCategory = booksCategoriesDao.find(id);
                if (booksCategory != null) {
                    resp.getWriter().write(gson.toJson(booksCategory));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BooksCategories booksCategory = gson.fromJson(req.getReader(), BooksCategories.class);
        if (booksCategoriesDao.save(booksCategory)) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getPathInfo().substring(1));
            if (booksCategoriesDao.delete(id)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
