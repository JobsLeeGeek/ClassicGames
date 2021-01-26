package org.jobsl.cgames.net.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/info", method = RequestMethod.POST)
public class InfoController {
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String get(HttpServletRequest request, HttpServletResponse response) {
        return "hhhh";
    }
}
