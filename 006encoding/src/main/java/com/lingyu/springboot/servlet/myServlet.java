package com.lingyu.springboot.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns="/myservlet")
public class myServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设定浏览器编码格式
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("哈哈哈哈哈  哈呀呀呀娃娃呀");
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
