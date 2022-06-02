package com.hadt.ehust.config

import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class WebConfig: WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
        registry.addInterceptor(LoggingInterceptor())
    }
}

private class LoggingInterceptor: HandlerInterceptor {
    private val logger = LogManager.getLogger(LoggingInterceptor::class.java)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.info("{}: {} --> {}", request.method, request.remoteAddr, request.requestURI)
        request.queryString?.let {
            logger.info("query string: {}", it)
        }
        request.contentLength.takeIf { it != -1 }?.let {
            logger.info("content-length: {}", it)
        }
        return true
    }
}