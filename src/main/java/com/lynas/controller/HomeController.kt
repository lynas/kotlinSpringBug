package com.lynas.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * Created by lynas on 10/10/2016
 */
@Controller
class HomeController {

    @GetMapping("/")
    fun home():String {
        return "home"
    }
}