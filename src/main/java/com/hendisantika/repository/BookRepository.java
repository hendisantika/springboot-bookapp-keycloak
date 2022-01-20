package com.hendisantika.repository;

import com.hendisantika.model.Book;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-bookapp-keycloak
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/01/22
 * Time: 13.40
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class BookRepository {
    private static final Map<String, Book> books = new ConcurrentHashMap<>();

}
