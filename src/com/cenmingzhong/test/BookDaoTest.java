package com.cenmingzhong.test;

import com.cenmingzhong.dao.BookDao;
import com.cenmingzhong.dao.impl.BookDaoImpl;
import com.cenmingzhong.prjo.Book;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author cenmingzhong
 * @create 2020-09-17-下午 21:36
 */
public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

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
    public void queryBookById() {
    }

    @Test
    public void queryBooks() {
    }
    @Test
    public void queryForPageTotalCout(){
        BookDao bookDao = new BookDaoImpl();
        Integer integer = bookDao.queryForPageTotalCout();
        System.out.println(integer);
    }
    @Test
    public void queryForPageItem(){
        BookDao bookDao = new BookDaoImpl();
        System.out.println(bookDao.queryForPageItem(1, 4));

    }
}