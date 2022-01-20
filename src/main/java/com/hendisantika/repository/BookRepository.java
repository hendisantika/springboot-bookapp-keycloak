package com.hendisantika.repository;

import com.hendisantika.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    static {
        books.put("B01", new Book("B01", "Harry Potter and the Deathly Hallows", "J.K. Rowling"));
        books.put("B02", new Book("B02", "The Lord of the Rings", "J.R.R. Tolkien"));
        books.put("B03", new Book("B03", "Fantastic Beast", "J.K. Rowling"));
    }

    public List<Book> readAll() {
        List<Book> allBooks = new ArrayList<>(books.values());
        allBooks.sort(Comparator.comparing(Book::getId));
        return allBooks;
    }

    public void create(Book book) {
        books.put(book.getId(), book);
    }

    public Book read(String id) {
        return books.get(id);
    }

    public void update(Book book) {
        books.put(book.getId(), book);
    }
}
