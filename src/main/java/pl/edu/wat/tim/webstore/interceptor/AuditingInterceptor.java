package pl.edu.wat.tim.webstore.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Piotr on 10.05.2017.
 */
public class AuditingInterceptor implements HandlerInterceptor {

    private String productId;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(httpServletRequest.getRequestURI().endsWith("products/add") && httpServletRequest.getMethod().equals("POST")){
            productId = httpServletRequest.getParameterValues("productId")[0];
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        if(httpServletRequest.getRequestURI().endsWith("products/add") && httpServletResponse.getStatus()==302){
            System.out.println((String.format("Nowy produkt [%s] dodany.", productId)));
        }
    }
}
