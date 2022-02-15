package com.sean.auth.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

@RestController
@RequestMapping("/monitoring")
class MonitoringController {
    @Autowired
    private lateinit var requestMappingHandlerMapping : RequestMappingHandlerMapping

    @GetMapping("/endpoints")
    fun getEndpoints() : ResponseEntity<List<String>> {
        return ResponseEntity(
            requestMappingHandlerMapping
                .handlerMethods
                .map {
                    it.key.toString()
                },
            HttpStatus.OK
        )
    }
}