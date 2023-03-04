package com.cx.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/2-21:57
 * @Version 2022.2 1.8
 */
@WebFilter("*.do")
public class Filter01 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("A");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("A2");
    }

    @Override
    public void destroy() {

    }
}
