package eu.fiestaiot.portal.testbed.web.filter;

import eu.fiestaiot.portal.testbed.config.FiestaTestbedRegistryProperties;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.util.WebUtils;

/**
 * This filter is used in production, to put HTTP cache headers with a long (1
 * month) expiration time.
 */
public class CachingHttpHeadersFilter implements Filter {

    // We consider the last modified date is the start up time of the server
    private final static long LAST_MODIFIED = System.currentTimeMillis();

    private long CACHE_TIME_TO_LIVE = TimeUnit.DAYS.toMillis(1461L);

    private FiestaTestbedRegistryProperties fiestaTestbedProperties;

    public CachingHttpHeadersFilter(FiestaTestbedRegistryProperties fiestaTestbedProperties) {
        this.fiestaTestbedProperties = fiestaTestbedProperties;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        CACHE_TIME_TO_LIVE = TimeUnit.DAYS.toMillis(fiestaTestbedProperties.getHttp().getCache().getTimeToLiveInDays());
    }

    @Override
    public void destroy() {
        // Nothing to destroy
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        httpResponse.setHeader("Cache-Control", "max-age=" + CACHE_TIME_TO_LIVE + ", public");
        httpResponse.setHeader("Pragma", "cache");

        Cookie jsesionCookie = WebUtils.getCookie(httpRequest, "JSESSIONID");
        if (jsesionCookie != null) {
            jsesionCookie.setValue(null);
            jsesionCookie.setMaxAge(0);

            httpResponse.addCookie(jsesionCookie);
        }

        // Setting Expires header, for proxy caching
        httpResponse.setDateHeader("Expires", CACHE_TIME_TO_LIVE + System.currentTimeMillis());

        // Setting the Last-Modified header, for browser caching
        httpResponse.setDateHeader("Last-Modified", LAST_MODIFIED);

        chain.doFilter(request, response);
    }
}
