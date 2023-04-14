//package com.team9.had.config;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.team9.had.utils.Constant;
//import com.team9.had.utils.EncryptDecrypt;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//
//@ControllerAdvice
//public class ResponseBodyModifier implements ResponseBodyAdvice<Object> {
//    @Override
//    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        return true;
//    }
//
//    @Override
//    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//
//        String decryptedData;
//        try {
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            ObjectMapper obj = new ObjectMapper();
//            obj.setDateFormat(df);
//            decryptedData = obj.writeValueAsString(body);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        String encryptedData;
//        try {
//            encryptedData = EncryptDecrypt.encrypt(decryptedData, Constant.SECRET_KEY);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return encryptedData;
//    }
//}
//
