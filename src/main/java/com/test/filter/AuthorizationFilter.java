package com.test.filter;

import com.test.constant.Constant;
import com.test.model.UserModel;
import com.test.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getRequestURI();
        if (url.startsWith("/HelloWorld/admin")) {
            var usermodel = (UserModel) SessionUtil.getInstance().getValue(req, Constant.USER_MODEL);
            if (usermodel != null) {
                if (usermodel.getRoleModel().getCode().equals(Constant.ADMIN)) {
                    chain.doFilter(request, response);
                } else if (usermodel.getRoleModel().getCode().equals(Constant.USER)) {
                    resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=Not_permission&alert=danger");
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=Not_login_yet&alert=danger");
            }

        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
