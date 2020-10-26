package com.cenmingzhong.service;

import com.cenmingzhong.prjo.Cart;

/**
 * @author cenmingzhong
 * @create 2020-09-27-下午 16:31
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
}
