package com.cenmingzhong.dao.impl;

import com.cenmingzhong.dao.BookDao;
import com.cenmingzhong.prjo.Book;

import java.util.List;

/**
 * @author cenmingzhong
 * @create 2020-09-17-下午 21:04
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {

        String sql="INSERT INTO t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values " +
                " (?,?,?,?,?,?)";

        //return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath())
        return updata(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());

    }

    @Override
    public int deleteBookById(Integer id) {
        String sql="delete from t_book where id = ?";
        return updata(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql="update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";

        return updata(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());

    }

    @Override
    public Book queryBookById(Integer id) {
        String sql="select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where id = ?";

        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCout() {
        String sql = "select Count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItem(int begin, int pageSize) {
        String sql="select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCoutByPrice(int min, int max) {
        String sql = "select Count(*) from t_book where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql,min,max);
        return count.intValue();

    }

    @Override
    public List<Book> queryForPageItemByPrice(int begin, int pageSize, int min, int max) {
        String sql="select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath " +
                "from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }


}
