package com.cenmingzhong.test;

import com.cenmingzhong.service.BookService;
import com.cenmingzhong.service.impl.BookServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author cenmingzhong
 * @create 2020-09-18-上午 11:09
 */
public class BookServiceTest {

    @Test
    public void addBook() {
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void queryBooKById() {
    }

    @Test
    public void queryBooks() {
    }

    @Test
    public void page(){
        BookService bookService = new BookServiceImpl();
        System.out.println(bookService.page(2, 4));

    }
}