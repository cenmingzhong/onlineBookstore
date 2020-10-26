package com.cenmingzhong.service.impl;

import com.cenmingzhong.dao.BookDao;
import com.cenmingzhong.dao.impl.BookDaoImpl;
import com.cenmingzhong.prjo.Book;
import com.cenmingzhong.prjo.Page;
import com.cenmingzhong.service.BookService;

import java.util.List;

/**
 * @author cenmingzhong
 * @create 2020-09-18-上午 11:03
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {

        bookDao.addBook(book);

    }

    @Override
    public void deleteBookById(Integer id) {

        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {

        bookDao.updateBook(book);
    }

    @Override
    public Book queryBooKById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();

        //设置当前页码
        page.setPageNo(pageNo);
        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总的记录数
        Integer pageTotalCout = bookDao.queryForPageTotalCout();
        //设置总记录数
        page.setPageTotalCout(pageTotalCout);
        //求总页码数
        Integer pageTotal = pageTotalCout / pageSize;
        if (pageTotalCout % pageSize > 0){
            pageTotal++;
        }
        //设置总页码数
        page.setPageTotal(pageTotal);

        //数据有效性边界检查
        if(pageNo<1){
            pageNo=1;
        }
        if(pageNo>pageTotal){
            pageNo=pageTotal;
        }



        //求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //求当前页数据
        List<Book> items = bookDao.queryForPageItem(begin,pageSize);
        //设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {

        Page<Book> page = new Page<Book>();

        //设置当前页码
        page.setPageNo(pageNo);
        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总的记录数
        Integer pageTotalCout = bookDao.queryForPageTotalCoutByPrice(min,max);
        //设置总记录数
        page.setPageTotalCout(pageTotalCout);
        //求总页码数
        Integer pageTotal = pageTotalCout / pageSize;
        if (pageTotalCout % pageSize > 0){
            pageTotal++;
        }
        //设置总页码数
        page.setPageTotal(pageTotal);

        //数据有效性边界检查
        if(pageNo<1){
            pageNo=1;
        }
        if(pageNo>pageTotal){
            pageNo=pageTotal;
        }



        //求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //求当前页数据
        List<Book> items = bookDao.queryForPageItemByPrice(begin,pageSize,min,max);
        //设置当前页数据
        page.setItems(items);

        return page;

    }
}
