package com.gqhmt.sys.handleException;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Filename:    com.gqhmt.sys.handleException.DefaultHandlerExceptionResolver
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/12/30 0:03
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/30  于泳      1.0     1.0 Version
 */
public class DefaultHandlerExceptionResolver extends SimpleMappingExceptionResolver implements HandlerExceptionResolver {

    private String errorView;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        return super.doResolveException(request, response, handler, ex);
        try {
            if (ex instanceof NoSuchRequestHandlingMethodException) {
                return handleNoSuchRequestHandlingMethod((NoSuchRequestHandlingMethodException) ex, request, response, handler);
            }
            else if (ex instanceof HttpRequestMethodNotSupportedException) {
                return handleHttpRequestMethodNotSupported((HttpRequestMethodNotSupportedException) ex, request,
                        response, handler);
            }
            else if (ex instanceof HttpMediaTypeNotSupportedException) {
                return handleHttpMediaTypeNotSupported((HttpMediaTypeNotSupportedException) ex, request, response,
                        handler);
            }
            else if (ex instanceof HttpMediaTypeNotAcceptableException) {
                return handleHttpMediaTypeNotAcceptable((HttpMediaTypeNotAcceptableException) ex, request, response,
                        handler);
            }
            else if (ex instanceof MissingPathVariableException) {
                return handleMissingPathVariable((MissingPathVariableException) ex, request,
                        response, handler);
            }
            else if (ex instanceof MissingServletRequestParameterException) {
                return handleMissingServletRequestParameter((MissingServletRequestParameterException) ex, request,
                        response, handler);
            }
            else if (ex instanceof ServletRequestBindingException) {
                return handleServletRequestBindingException((ServletRequestBindingException) ex, request, response,
                        handler);
            }
            else if (ex instanceof ConversionNotSupportedException) {
                return handleConversionNotSupported((ConversionNotSupportedException) ex, request, response, handler);
            }
            else if (ex instanceof TypeMismatchException) {
                return handleTypeMismatch((TypeMismatchException) ex, request, response, handler);
            }
            else if (ex instanceof HttpMessageNotReadableException) {
                return handleHttpMessageNotReadable((HttpMessageNotReadableException) ex, request, response, handler);
            }
            else if (ex instanceof HttpMessageNotWritableException) {
                return handleHttpMessageNotWritable((HttpMessageNotWritableException) ex, request, response, handler);
            }
            else if (ex instanceof MethodArgumentNotValidException) {
                return handleMethodArgumentNotValidException((MethodArgumentNotValidException) ex, request, response,
                        handler);
            }
            else if (ex instanceof MissingServletRequestPartException) {
                return handleMissingServletRequestPartException((MissingServletRequestPartException) ex, request,
                        response, handler);
            }
            else if (ex instanceof BindException) {
                return handleBindException((BindException) ex, request, response, handler);
            }
            else if (ex instanceof NoHandlerFoundException) {
                return handleNoHandlerFoundException((NoHandlerFoundException) ex, request, response, handler);
            }else {
                sendServerError(ex, request, response);
            }
        }
        catch (Exception handlerException) {
            if (logger.isWarnEnabled()) {
                logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
            }
        }
        return new ModelAndView(errorView);
    }

    public String getErrorView() {
        return errorView;
    }

    public void setErrorView(String errorView) {
        this.errorView = errorView;
    }

    /**
     * Handle the case where no request handler method was found.
     * <p>The default implementation logs a warning, sends an HTTP 404 error, and returns
     * an empty {@code ModelAndView}. Alternatively, a fallback view could be chosen,
     * or the NoSuchRequestHandlingMethodException could be rethrown as-is.
     * @param ex the NoSuchRequestHandlingMethodException to be handled
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler, or {@code null} if none chosen
     * at the time of the exception (for example, if multipart resolution failed)
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    protected ModelAndView handleNoSuchRequestHandlingMethod(NoSuchRequestHandlingMethodException ex,
                                                             HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

//        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        request.setAttribute("errorCode",HttpServletResponse.SC_NOT_FOUND);
        return new ModelAndView(errorView);
    }

    /**
     * Handle the case where no request handler method was found for the particular HTTP request method.
     * <p>The default implementation logs a warning, sends an HTTP 405 error, sets the "Allow" header,
     * and returns an empty {@code ModelAndView}. Alternatively, a fallback view could be chosen,
     * or the HttpRequestMethodNotSupportedException could be rethrown as-is.
     * @param ex the HttpRequestMethodNotSupportedException to be handled
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler, or {@code null} if none chosen
     * at the time of the exception (for example, if multipart resolution failed)
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    protected ModelAndView handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                               HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String[] supportedMethods = ex.getSupportedMethods();
        if (supportedMethods != null) {
            response.setHeader("Allow", StringUtils.arrayToDelimitedString(supportedMethods, ", "));
        }
//        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, ex.getMessage());

        request.setAttribute("errorCode",HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        request.setAttribute("errorMsg",ex.getMessage());
        return new ModelAndView(errorView);
    }

    protected ModelAndView handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                           HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

//        response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
//        if (!CollectionUtils.isEmpty(mediaTypes)) {
//            response.setHeader("Accept", MediaType.toString(mediaTypes));
//        }
        request.setAttribute("errorCode",HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        request.setAttribute("errorMsg",ex.getMessage());
        return new ModelAndView(errorView);
    }

    protected ModelAndView handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

//        response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        request.setAttribute("errorCode",HttpServletResponse.SC_NOT_ACCEPTABLE);
        request.setAttribute("errorMsg",ex.getMessage());
        return new ModelAndView(errorView);
    }

    protected ModelAndView handleMissingPathVariable(MissingPathVariableException ex,
                                                     HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

//        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        request.setAttribute("errorCode",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        request.setAttribute("errorMsg",ex.getMessage());
        return new ModelAndView(errorView);
    }

    protected ModelAndView handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

//        response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        request.setAttribute("errorCode",HttpServletResponse.SC_BAD_REQUEST);
        request.setAttribute("errorMsg",ex.getMessage());
        return new ModelAndView(errorView);
    }

    protected ModelAndView handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

//        response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        request.setAttribute("errorCode",HttpServletResponse.SC_BAD_REQUEST);
        request.setAttribute("errorMsg",ex.getMessage());
        return new ModelAndView(errorView);
    }

    protected ModelAndView handleConversionNotSupported(ConversionNotSupportedException ex,
                                                        HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        if (logger.isWarnEnabled()) {
            logger.warn("Failed to convert request element: " + ex);
        }
        sendServerError(ex, request, response);
        return new ModelAndView(errorView);
    }

    protected ModelAndView handleTypeMismatch(TypeMismatchException ex,
                                              HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        if (logger.isWarnEnabled()) {
            logger.warn("Failed to bind request element: " + ex);
        }
//        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        request.setAttribute("errorCode",HttpServletResponse.SC_BAD_REQUEST);
        request.setAttribute("errorMsg",ex.getMessage());
        return new ModelAndView(errorView);
    }

    protected ModelAndView handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                        HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        if (logger.isWarnEnabled()) {
            logger.warn("Failed to read HTTP message: " + ex);
        }
        request.setAttribute("errorCode",HttpServletResponse.SC_BAD_REQUEST);
        request.setAttribute("errorMsg",ex.getMessage());
        return new ModelAndView(errorView);
    }

    protected ModelAndView handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
                                                        HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        if (logger.isWarnEnabled()) {
            logger.warn("Failed to write HTTP message: " + ex);
        }
        sendServerError(ex, request, response);
        return new ModelAndView(errorView);
    }

    protected ModelAndView handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                 HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        request.setAttribute("errorCode",HttpServletResponse.SC_BAD_REQUEST);
        request.setAttribute("errorMsg",ex.getMessage());
        return new ModelAndView(errorView);
    }

    protected ModelAndView handleMissingServletRequestPartException(MissingServletRequestPartException ex,
                                                                    HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        request.setAttribute("errorCode",HttpServletResponse.SC_BAD_REQUEST);
        request.setAttribute("errorMsg",ex.getMessage());
        return new ModelAndView(errorView);
    }

    protected ModelAndView handleBindException(BindException ex, HttpServletRequest request,
                                               HttpServletResponse response, Object handler) throws IOException {

        request.setAttribute("errorCode",HttpServletResponse.SC_BAD_REQUEST);
        request.setAttribute("errorMsg",ex.getMessage());
        return new ModelAndView(errorView);
    }

    protected ModelAndView handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                         HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

//        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        request.setAttribute("errorCode",HttpServletResponse.SC_NOT_FOUND);
        request.setAttribute("errorMsg",ex.getMessage());
        return new ModelAndView(errorView);
    }


    /**
     * Invoked to send a server error. Sets the status to 500 and also sets the
     * request attribute "javax.servlet.error.exception" to the Exception.
     */
    protected void sendServerError(Exception ex, HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.setAttribute("javax.servlet.error.exception", ex);
//        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        request.setAttribute("errorCode",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        request.setAttribute("errorMsg",ex.getMessage());
        request.setAttribute("errorException",ex);


    }
}
