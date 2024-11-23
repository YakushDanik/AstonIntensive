package org.example.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.dao.BooksDao;
import org.example.dao.SessionFactoryProvider;
import org.example.model.Authors;
import org.example.model.Books;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/api/books/*")
public class BooksServlet extends HttpServlet {

    private BooksDao booksDao;

    @Override
    public void init() throws ServletException {
        booksDao = new BooksDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        resp.setContentType("application/json");

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<Books> books = booksDao.findAll();
                resp.getWriter().write(new Gson().toJson(books));
            } else {
                try {
                    Integer id = Integer.parseInt(pathInfo.substring(1));
                    Books book = booksDao.find(id);
                    if (book != null) {
                        resp.getWriter().write(new Gson().toJson(book));
                    } else {
                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    }
                } catch (NumberFormatException e) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write("{\"error\": \"Invalid ID format\"}");
                }
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Server error\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String json = req.getReader().readLine();
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

            String title = jsonObject.get("title").getAsString();
            int authorID = jsonObject.get("author_id").getAsInt();

            Authors author = findAuthorById(authorID);
            if (author == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"Author not found\"}");
                return;
            }

            Books book = new Books(title, author);
            if (booksDao.save(book)) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write(new Gson().toJson(book));
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"Failed to create book\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Server error\"}");
        }
    }


    private Authors findAuthorById(int authorId) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            return session.get(Authors.class, authorId);
        }
    }
}