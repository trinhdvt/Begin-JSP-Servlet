package com.test.controller.admin;

import com.test.constant.Constant;
import com.test.model.NewModel;
import com.test.paging.PageRequest;
import com.test.paging.Pageable;
import com.test.paging.Sorter;
import com.test.service.INewService;
import com.test.utils.FormUtils;

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
        NewModel newModel = FormUtils.toModel(NewModel.class, req);
        Pageable pageable = new PageRequest(newModel.getCurrentPage(), newModel.getMaxPageItems(),
                new Sorter(newModel.getSortOrder(), newModel.getSortBy()));
        var data = newService.findAll(pageable);

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
