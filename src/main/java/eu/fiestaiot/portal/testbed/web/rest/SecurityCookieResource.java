package eu.fiestaiot.portal.testbed.web.rest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import eu.fiestaiot.portal.testbed.service.OpenAMSecurityHelper;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
public class SecurityCookieResource {

    private final Logger log = LoggerFactory.getLogger(SecurityCookieResource.class);
    @Inject
    private OpenAMSecurityHelper openAMSecurityHelper;

    @GetMapping("/getCookie")
    @Timed
    public ResponseEntity<String> getAllRegisterTestbeds(HttpServletRequest request) {
        String token = request.getParameter("iPlanetDirectoryPro");
        if (token == null) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("iPlanetDirectoryPro")) {
                    token = cookie.getValue();
                }
            }
        }
        return new ResponseEntity<>(token, null, HttpStatus.OK);
    }

    @GetMapping("/account")
    @Timed
    public ResponseEntity<JWTToken> getAccount(HttpServletRequest request) {
        String token = request.getParameter("iPlanetDirectoryPro");
        if (token == null) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("iPlanetDirectoryPro")) {
                    token = cookie.getValue();
                }
            }
        }

        JWTToken objectToken = new JWTToken(token);
        return new ResponseEntity<>(objectToken, null, HttpStatus.OK);
    }

    @PostMapping("/isAdmin")
    public ResponseEntity<?> isAdmin(HttpServletRequest request) {
        String userID = request.getHeader("userID");
        String token = request.getHeader("iPlanetDirectoryPro");

        return new ResponseEntity<>(openAMSecurityHelper.isAdmin(userID, token), null, HttpStatus.OK);

    }

}
