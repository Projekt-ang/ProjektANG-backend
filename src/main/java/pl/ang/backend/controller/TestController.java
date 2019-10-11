package pl.ang.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping({ "/test" })
    @PreAuthorize("hasRole('USER')")
    public String test() {
        return "hello world";
    }

    @RequestMapping({ "/admin/test" })
    @PreAuthorize("hasRole('ADMIN')")
    public String adminTest() {
        return "hello world admin";
    }

}
