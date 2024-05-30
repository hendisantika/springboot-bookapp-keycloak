package com.hendisantika.controller;

import com.hendisantika.repository.BookRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.RolesAllowed;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-bookapp-keycloak
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/01/22
 * Time: 13.47
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class LibraryController {

    private final HttpServletRequest request;
    private final BookRepository bookRepository;

    public LibraryController(HttpServletRequest request, BookRepository bookRepository) {
        this.request = request;
        this.bookRepository = bookRepository;
    }

    @GetMapping(value = "/")
    public String getHome() {
        return "index";
    }

    @RolesAllowed({"librarian", "member"})
    @GetMapping(value = "/books")
    public String getBooks(Model model) {
        configCommonAttributes(model);
        model.addAttribute("books", bookRepository.readAll());
        return "books";
    }

    @GetMapping(value = "/manager")
    public String getManager(Model model) {
        configCommonAttributes(model);
        model.addAttribute("books", bookRepository.readAll());
        return "manager";
    }

    @GetMapping(value = "/logout")
    public String logout() throws ServletException {
        request.logout();
        return "redirect:/";
    }

    private void configCommonAttributes(Model model) {
        model.addAttribute("name", getKeycloakSecurityContext().getIdToken().getGivenName());
    }

    private KeycloakSecurityContext getKeycloakSecurityContext() {
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }
}
