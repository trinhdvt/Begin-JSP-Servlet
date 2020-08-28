package com.test.controller.web;

import com.test.model.UserModel;
import com.test.service.ICategoryService;
import com.test.service.IUserService;
import com.test.utils.FormUtils;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap"})
public class HomeController extends HttpServlet {

    @Inject
    ICategoryService categoryService;

    @Inject
    IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equalsIgnoreCase("login")) {
            req.getRequestDispatcher("views/login.jsp").forward(req, resp);
        } else if (action != null && action.equals("logout")) {

        } else {
            RequestDispatcher rd = req.getRequestDispatcher("views/web/home.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("login")) {
            UserModel user = FormUtils.toModel(UserModel.class, req);
            user = userService.findUser(user.getUserName(), user.getPassword(), 1);
            if (user != null) {
                String redirectPath = req.getContextPath();
                if (user.getRoleModel().getCode().equals("ADMIN")) {
                    redirectPath += "/admin";
                } else if (user.getRoleModel().getCode().equals("USER")) {
                    redirectPath += "/trang-chu";
                }
                resp.sendRedirect(redirectPath);
            } else {
                resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login");
            }
        }
    }
}
