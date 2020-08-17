package com.test.controller.admin;

import com.test.constant.Constant;
import com.test.model.NewModel;
import com.test.service.INewService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet {

    @Inject
    INewService newService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewModel newModel = new NewModel();

        var currentPage = req.getParameter("currentPage");
        if (currentPage != null) {
            newModel.setCurrentPage(Integer.parseInt(currentPage));
        } else {
            newModel.setCurrentPage(1);
        }
        var maxPageItems = req.getParameter("maxPageItems");
        if (maxPageItems != null) {
            newModel.setMaxPageItems(Integer.parseInt(maxPageItems));
        } else {
            newModel.setMaxPageItems(2);
        }

        int offset = (newModel.getCurrentPage() - 1) * newModel.getMaxPageItems();
        var data = newService.findAll(offset, newModel.getMaxPageItems());
        newModel.setListData(data);
        newModel.setTotalItems(newService.getTotalItems());
        newModel.setTotalPages((int) Math.ceil((double) newModel.getTotalItems() / newModel.getMaxPageItems()));

        req.setAttribute(Constant.MODEL, newModel);
        RequestDispatcher rd = req.getRequestDispatcher("views/admin/news/list.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
