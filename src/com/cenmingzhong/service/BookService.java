package com.cenmingzhong.service;

import com.cenmingzhong.prjo.Book;
import com.cenmingzhong.prjo.Page;

import java.util.List;

/**
 * @author cenmingzhong
 * @create 2020-09-18-上午 11:00
 */
public interface BookService {
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBooKById(Integer id);

    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
