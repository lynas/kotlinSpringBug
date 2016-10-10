package com.lynas.config

import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by sazzad on 10/3/16
 */

@Component
open class InterceptorConfig : HandlerInterceptorAdapter() {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any?): Boolean {
        if (request.requestURI.equals("/")) {
            println("In config")
            return true
        }
        return true
    }
}