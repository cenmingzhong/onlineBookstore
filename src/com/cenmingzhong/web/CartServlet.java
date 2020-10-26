package com.cenmingzhong.web;

import com.cenmingzhong.prjo.Book;
import com.cenmingzhong.prjo.Cart;
import com.cenmingzhong.prjo.CartItem;
import com.cenmingzhong.service.BookService;
import com.cenmingzhong.service.impl.BookServiceImpl;
import com.cenmingzhong.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cenmingzhong
 * @create 2020-09-26-上午 10:47
 */
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数 商品编号，商品数量
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"),1);
        //2.获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            //修改商品数量
            cart.updateCount(id,count);
            //重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //2.
        if (cart!=null){
            //清空购物车
            cart.clear();
            //重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }



    }

    /**
     * 删除商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);

        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("id");
        if (cart!=null){
            //删除购物车商品项
            cart.deleteItem(id);
            //重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));

        }


    }
        /**
         * 加入购物车
         * @param req
         * @param resp
         * @throws ServletException
         * @throws IOException
         */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //2.调用bookService.queryBookById(id):Book得到图书信息
        Book book = bookService.queryBooKById(id);
        //3.把图书信息，转换成CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //4.调用Cart.addItem(CartItem)添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart==null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }

        cart.addItem(cartItem);
        System.out.println(cart);
        System.out.println("请求头Referer的值："+req.getHeader("Referer"));

        //最后一个添加的商品名称
        req.getSession().setAttribute("lastname",cartItem.getName());


        //5.重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));
    }


    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //2.调用bookService.queryBookById(id):Book得到图书信息
        Book book = bookService.queryBooKById(id);
        //3.把图书信息，转换成CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //4.调用Cart.addItem(CartItem)添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart==null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }

        cart.addItem(cartItem);
        System.out.println(cart);
        System.out.println("请求头Referer的值："+req.getHeader("Referer"));

        //最后一个添加的商品名称
        req.getSession().setAttribute("lastname",cartItem.getName());


        //返回购物车总的商品数量和最后一个添加的商品名称
        Map<String,Object> reslutMap=new HashMap<String, Object>();
        reslutMap.put("totalCount",cart.getTotalCount());
        reslutMap.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(reslutMap);
        resp.getWriter().write(resultMapJsonString);


    }
}
