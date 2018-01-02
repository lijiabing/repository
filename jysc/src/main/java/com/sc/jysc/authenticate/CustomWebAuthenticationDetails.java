package com.sc.jysc.authenticate;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Jackbing on 2017/12/20.
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 1L;
    private final String randCheckCode;
    private final String checkCode;
    private final String original_data;
    private final String signed_data;
    private final String original_jsp;
    private final String KEY_ORIGINAL_DATA = "original_data";
    private final String KEY_ORIGINAL_JSP = "original_jsp";
    private final String KEY_SIGNED_DATA = "signed_data";

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.randCheckCode = this.getProperties(request.getSession(), "randCheckCode");
        this.checkCode = this.getParameter(request, "checkCode");
        this.original_data = this.getProperties(request.getSession(), "original_data");
        this.signed_data = this.getParameter(request, "signed_data");
        this.original_jsp = this.getParameter(request, "original_jsp");
    }

    public String getOriginal_data() {
        return this.original_data;
    }

    public String getSigned_data() {
        return this.signed_data;
    }

    public String getOriginal_jsp() {
        return this.original_jsp;
    }

    public String getRandCheckCode() {
        return this.randCheckCode;
    }

    public String getCheckCode() {
        return this.checkCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; checkCode: ").append(this.getCheckCode());
        return sb.toString();
    }

    private String getProperties(HttpSession httpSession, String key) {
        return httpSession.getAttribute(key) == null?null:httpSession.getAttribute(key).toString();
    }

    private String getParameter(HttpServletRequest request, String key) {
        return request.getParameter(key) == null?null:request.getParameter(key).toString();
    }
}

