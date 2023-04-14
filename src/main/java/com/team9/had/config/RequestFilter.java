//package com.team9.had.config;
//
//import com.team9.had.utils.Constant;
//import com.team9.had.utils.EncryptDecrypt;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ReadListener;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletInputStream;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletRequestWrapper;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.util.stream.Collectors;
//
//@Component
//public class RequestFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String encryptedData = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        String decryptedData;
//
//        try {
//            decryptedData = EncryptDecrypt.decrypt(encryptedData, Constant.SECRET_KEY);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        // Create a new ServletInputStream that reads from the modified request body
//        ByteArrayInputStream modifiedRequestBodyStream = new ByteArrayInputStream(decryptedData.getBytes());
//        ServletInputStream modifiedRequestBodyInputStream = new ServletInputStream() {
//            @Override
//            public boolean isFinished() {
//                return false;
//            }
//
//            @Override
//            public boolean isReady() {
//                return false;
//            }
//
//            @Override
//            public void setReadListener(ReadListener readListener) {
//
//            }
//
//            @Override
//            public int read() throws IOException {
//                return modifiedRequestBodyStream.read();
//            }
//        };
//
//        // Replace the original request body with the modified version
//        HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(request) {
//            @Override
//            public ServletInputStream getInputStream() throws IOException {
//                return modifiedRequestBodyInputStream;
//            }
//        };
//
//        // Set the content type to application/json
//        wrappedRequest.setCharacterEncoding("UTF-8");
//        filterChain.doFilter(wrappedRequest, response);
//    }
//}
