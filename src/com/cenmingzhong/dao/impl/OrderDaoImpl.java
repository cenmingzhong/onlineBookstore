package com.cenmingzhong.dao.impl;

import com.cenmingzhong.dao.OrderDao;
import com.cenmingzhong.prjo.Order;

/**
 * @author cenmingzhong
 * @create 2020-09-27-下午 16:06
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(`order_id`,`createTime`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return updata(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());

    }
}
