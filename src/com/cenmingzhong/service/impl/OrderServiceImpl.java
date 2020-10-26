package com.cenmingzhong.service.impl;

import com.cenmingzhong.dao.BookDao;
import com.cenmingzhong.dao.OrderDao;
import com.cenmingzhong.dao.OrderItemDao;
import com.cenmingzhong.dao.impl.BookDaoImpl;
import com.cenmingzhong.dao.impl.OrderDaoImpl;
import com.cenmingzhong.dao.impl.OrderItemDaoImpl;
import com.cenmingzhong.prjo.*;
import com.cenmingzhong.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author cenmingzhong
 * @create 2020-09-27-下午 16:32
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {

        //订单号（唯一性）
        String orderId=System.currentTimeMillis()+""+userId;
        //创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);
        //遍历购物车中每一个商品项转换成为订单项保存到数据库中
        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            //获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            //转换为每一个订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);

        }

        //情空购物车
        cart.clear();
        return orderId;
    }
}
