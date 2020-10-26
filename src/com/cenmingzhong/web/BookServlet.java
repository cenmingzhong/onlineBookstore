package com.cenmingzhong.web;

import com.cenmingzhong.prjo.Book;
import com.cenmingzhong.prjo.Page;
import com.cenmingzhong.service.BookService;
import com.cenmingzhong.service.impl.BookServiceImpl;
import com.cenmingzhong.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author cenmingzhong
 * @create 2020-09-18-上午 11:12
 */
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用bookservice.page(pageNo,pageSize):返回page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3.保存page对象到request域中
        req.setAttribute("page",page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        //1.获取请求的参数，封装成Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2.调用BookService.addBook()保存图书
        bookService.addBook(book);
        System.out.println("req.getContextPath():"+req.getContextPath());
        //3.跳到图书列表页面
       resp.sendRedirect(req.getContextPath()+ "/manager/bookServlet?action=page&pageNo="+pageNo);


    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求的参数id,图书编程
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //2.调用bookService.deleteBookById,删除图书
        bookService.deleteBookById(id);
        //3.重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数，封装成Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //2.调用bookService.updataBook(book)修改图书
        bookService.updateBook(book);
        //3.重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2.调用bookService.queryBookById查询图书
        Book book = bookService.queryBooKById(id);
        //3.保存图书到request域中
        req.setAttribute("book",book);
        //4.请求转发到pages/manager/book_edit.jsp页面中
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);


    }


    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把全部图书保存到Request域中
        req.setAttribute("books",books);
        //3.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

}
