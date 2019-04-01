package com.example.qixin.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/** 自定义 Filter 有两种实现方式，第一种是使用 @WebFilter，第二种是使用 FilterRegistrationBean，
 * 经过实践之后发现使用 @WebFilter 自定义的过滤器优先级顺序不能生效，因此推荐使用第二个方案，
 * 接下来我们详细介绍第二种方案。
 * 创 建 时 间: 2019/3/31
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
public class MyFilter implements Filter  {

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) srequest;
        System.out.println("this is MyFilter,url :"+request.getRequestURI());
        filterChain.doFilter(srequest, sresponse);
    }

    @Override
    public void destroy() {

    }
}
