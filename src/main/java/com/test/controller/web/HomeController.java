package com.test.controller.web;

import com.test.model.UserModel;
import com.test.service.IUserService;
import com.test.utils.FormUtils;
import com.test.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/thoat"})
public class HomeController extends HttpServlet {

    @Inject
    IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equalsIgnoreCase("login")) {
            String message = req.getParameter("message");
            String alert = req.getParameter("alert");
            if (message != null && alert != null) {
                req.setAttribute("message", message);
                req.setAttribute("alert", alert);
            }
            req.getRequestDispatcher("views/login.jsp").forward(req, resp);
        } else if (action != null && action.equals("logout")) {
            SessionUtil.getInstance().removeValue(req, "USERMODEL");
            resp.sendRedirect(req.getContextPath() + "/trang-chu");
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("views/web/home.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("login")) {
            UserModel user = FormUtils.toModel(UserModel.class, req);
            String redirectPath = req.getContextPath();
            user = userService.findUser(user.getUserName(), user.getPassword(), 1);
            if (user != null) {
                SessionUtil.getInstance().putValue(req, "USERMODEL", user);
                if (user.getRoleModel().getCode().equals("ADMIN")) {
                    redirectPath += "/admin";
                } else if (user.getRoleModel().getCode().equals("USER")) {
                    redirectPath += "/trang-chu";
                }
                resp.sendRedirect(redirectPath);
            } else {
                resp.sendRedirect(redirectPath + "/dang-nhap?action=login&message=Login_Fail&alert=danger");
            }
        }
    }
}
