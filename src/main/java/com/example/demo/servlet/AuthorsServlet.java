//package org.example.servlet;
//
//import com.google.gson.Gson;
//import org.example.dao.AuthorsDao;
//import org.example.model.Authors;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/api/authors/*")
//public class AuthorsServlet extends HttpServlet {
//
//    private AuthorsDao authorsDao;
//    private final Gson gson = new Gson();
//
//    @Override
//    public void init() throws ServletException {
//        authorsDao = new AuthorsDao();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String pathInfo = req.getPathInfo();
//        resp.setContentType("application/json");
//
//        try {
//            if (pathInfo == null || pathInfo.equals("/")) {
//                List<Authors> authors = authorsDao.findAll();
//                resp.getWriter().write(gson.toJson(authors));
//            } else {
//                try {
//                    Integer id = Integer.parseInt(pathInfo.substring(1));
//                    Authors author = authorsDao.find(id);
//                    if (author != null) {
//                        resp.getWriter().write(gson.toJson(author));
//                    } else {
//                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                        resp.getWriter().write("{\"error\": \"Author not found\"}");
//                    }
//                } catch (NumberFormatException e) {
//                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                    resp.getWriter().write("{\"error\": \"Invalid ID format\"}");
//                }
//            }
//        } catch (Exception e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            resp.getWriter().write("{\"error\": \"Server error\"}");
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        resp.setContentType("application/json");
//        Authors author = gson.fromJson(req.getReader(), Authors.class);
//
//        try {
//            if (authorsDao.save(author)) {
//                resp.setStatus(HttpServletResponse.SC_CREATED);
//                resp.getWriter().write(gson.toJson(author));
//            } else {
//                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                resp.getWriter().write("{\"error\": \"Failed to create author\"}");
//            }
//        } catch (Exception e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            resp.getWriter().write("{\"error\": \"Server error\"}");
//        }
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        resp.setContentType("application/json");
//        String pathInfo = req.getPathInfo();
//
//        try {
//            if (pathInfo == null || pathInfo.equals("/")) {
//                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                resp.getWriter().write("{\"error\": \"ID is required\"}");
//                return;
//            }
//
//            try {
//                Integer id = Integer.parseInt(pathInfo.substring(1));
//                if (authorsDao.delete(id)) {
//                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
//                } else {
//                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                    resp.getWriter().write("{\"error\": \"Author not found\"}");
//                }
//            } catch (NumberFormatException e) {
//                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                resp.getWriter().write("{\"error\": \"Invalid ID format\"}");
//            }
//        } catch (Exception e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            resp.getWriter().write("{\"error\": \"Server error\"}");
//        }
//    }
//}