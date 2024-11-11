package org.example.servlet;

import org.example.dao.BooksDao;
import org.example.dao.DataSourceFactory;
import org.example.model.Books;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet("/api/books/*")
public class BooksServlet extends HttpServlet {
    private BooksDao booksDao;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DataSourceFactory.getConnection();
            this.booksDao = new BooksDao(connection);
        } catch (SQLException e) {
            throw new ServletException("Unable to initialize BooksDao", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        resp.setContentType("application/json");

        try (PrintWriter out = resp.getWriter()) {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<Books> books = booksDao.findAll();
                out.println(books);
            } else {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length == 2) {
                    int id = Integer.parseInt(pathParts[1]);
                    Optional<Books> book = booksDao.find(id);
                    if (book.isPresent()) {
                        out.println(book.get());
                    } else {
                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        out.println("{\"error\": \"Book not found\"}");
                    }
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.println("{\"error\": \"Invalid request\"}");
                }
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace(resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String title = req.getParameter("title");
            int authorId = Integer.parseInt(req.getParameter("author_id"));
            Books book = new Books(0, title, authorId);
            boolean success = booksDao.save(book);
            resp.setStatus(success ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_BAD_REQUEST);
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace(resp.getWriter());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            int authorId = Integer.parseInt(req.getParameter("author_id"));
            Books book = new Books(id, title, authorId);
            boolean success = booksDao.update(book);
            resp.setStatus(success ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NOT_FOUND);
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace(resp.getWriter());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        try {
            if (pathInfo != null && pathInfo.split("/").length == 2) {
                int id = Integer.parseInt(pathInfo.split("/")[1]);
                Books book = new Books(id, null, 0);
                boolean success = booksDao.delete(book);
                resp.setStatus(success ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NOT_FOUND);
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("{\"error\": \"Invalid request\"}");
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace(resp.getWriter());
        }
    }
}
