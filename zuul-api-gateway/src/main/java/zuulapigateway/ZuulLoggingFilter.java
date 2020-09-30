package zuulapigateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Log4j2
public class ZuulLoggingFilter extends ZuulFilter {
    @Override
    public boolean shouldFilter() {
        return true;
    }
    
    @Override
    public Object run() {
        HttpServletRequest request =
                RequestContext.getCurrentContext().getRequest();
        log.info("request -> {} request uri -> {}",
                request, request.getRequestURI());
        return null;
    }
    
    @Override
    public String filterType() {
        return "pre";
    }
    
    @Override
    public int filterOrder() {
        return 1;
    }
}
