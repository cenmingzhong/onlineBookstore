package com.cenmingzhong.dao;


import com.cenmingzhong.prjo.Book;

import java.util.List;

/**
 * @author cenmingzhong
 * @create 2020-09-17-下午 20:58
 */
public interface BookDao {
    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Integer queryForPageTotalCout();

    List<Book> queryForPageItem(int begin, int pageSize);

    Integer queryForPageTotalCoutByPrice(int min, int max);

    List<Book> queryForPageItemByPrice(int begin, int pageSize, int min, int max);
}
