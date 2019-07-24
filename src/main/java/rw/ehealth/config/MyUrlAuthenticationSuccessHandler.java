package rw.ehealth.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class MyUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	 protected Log logger = LogFactory.getLog(this.getClass());
	 
	    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	 
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		 handle(request, response, authentication);
	        clearAuthenticationAttributes(request);
	    }
	 
	    protected void handle(HttpServletRequest request, 
	      HttpServletResponse response, Authentication authentication)
	      throws IOException {
	  
	        String targetUrl = determineTargetUrl(authentication);
	 
	        if (response.isCommitted()) {
	            logger.debug(
	              "Response has already been committed. Unable to redirect to "
	              + targetUrl);
	            return;
	        }
	 
	        redirectStrategy.sendRedirect(request, response, targetUrl);
	    }
	 
	    protected String determineTargetUrl(Authentication authentication) {
	        boolean isGDOCTOR = false;
	        boolean isADMIN = false;
	        boolean isLABODOCTOR = false;
	        boolean isRECEPTIONIST = false;
	        Collection<? extends GrantedAuthority> authorities
	         = authentication.getAuthorities();
	        for (GrantedAuthority grantedAuthority : authorities) {
	            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
	            	isADMIN = true;
	                break;
	            } else if (grantedAuthority.getAuthority().equals("ROLE_GENERAL_DOCTOR")) {
	            	isGDOCTOR = true;
	                break;
	            }else if (grantedAuthority.getAuthority().equals("ROLE_LABORATORY_DOCTOR")) {
	            	isLABODOCTOR = true;
	            	break;
				}else if (grantedAuthority.getAuthority().equals("ROLE_RECEPTIONIST")) {
					isRECEPTIONIST = true;
					break;
				}
	        }
	 
	        if (isADMIN) {
	            return "/";
	        } else if (isRECEPTIONIST) {
	            return "recptionist";
	        } else if (isGDOCTOR) {
				return "gdoctor";
			}else if (isLABODOCTOR) {
				return "labodoctor";
			}
	        else {
	            throw new IllegalStateException();
	        }
	    }
	 
	    protected void clearAuthenticationAttributes(HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        if (session == null) {
	            return;
	        }
	        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	    }
	 
	    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
	        this.redirectStrategy = redirectStrategy;
	    }
	    protected RedirectStrategy getRedirectStrategy() {
	        return redirectStrategy;
	    }
	}
