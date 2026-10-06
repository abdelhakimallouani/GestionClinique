package ma.youcode.clinique.filter;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class CsrfFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        String csrfToken = (String) session.getAttribute("csrfToken");

        if (csrfToken == null) {
            csrfToken = UUID.randomUUID().toString();
            session.setAttribute("csrfToken", csrfToken);
        }

        request.setAttribute("csrfToken", csrfToken);

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String submittedToken = request.getParameter("csrfToken");

            if (submittedToken == null
                    || !csrfToken.equals(submittedToken)) {

                response.sendError(
                        HttpServletResponse.SC_FORBIDDEN,
                        "Token CSRF invalide ou manquant");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}