package com.cenmingzhong.dao.impl;

import com.cenmingzhong.dao.OrderItemDao;
import com.cenmingzhong.prjo.OrderItem;

/**
 * @author cenmingzhong
 * @create 2020-09-27-下午 16:10
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return updata(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());

    }
}
