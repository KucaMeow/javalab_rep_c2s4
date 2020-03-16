//package ru.itis.controllers;
//
//import org.springframework.context.ApplicationContext;
//import ru.itis.services.EmailService;
//import ru.itis.services.RegisterService;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Servlet Implementation
// */
//@WebServlet("/registerOld")
//public class RegisterServlet extends HttpServlet {
//
//    private RegisterService registerService;
//    private EmailService emailVerificationService;
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        ServletContext context = config.getServletContext();
//        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
////        registerService = springContext.getBean(RegisterService.class);
////        emailVerificationService = springContext.getBean(EmailVerificationService.class);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.sendRedirect("files");
//    }
//}
