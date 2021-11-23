/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.logicApplication.bookDAO;

import com.nhom3.model.PagedResult;
import com.nhom3.model.book.Author;
import com.nhom3.model.book.Book;
import com.nhom3.model.book.BookItem;
import com.nhom3.model.book.Publisher;
import com.nhom3.model.order.BoughtBookItem;
import com.nhom3.utils.JdbcUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duong
 */
public class BookDAOImpl implements BookDAO {

    private JdbcUtils jdbcUtils;

    public BookDAOImpl() {
        try {
            jdbcUtils = new JdbcUtils();
        } catch (IOException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public BookItem get(String barCode) {
        BookItem bookItem = null;
        String sql = "SELECT b.BarCode AS BarCode, b.Price AS Price, b.Discount AS Discount,\n"
                + "  b1.Id AS BookId, b1.Title AS Title, b1.Summary AS Summary, b1.Pages AS Pages, b1.Language AS Language,\n"
                + "  a.Id AS AuthorId, a.Name AS AuthorName, a.Biography AS AuthorBiography,\n"
                + "  p.Id AS PId, p.Name AS PName, p.Address AS PAddress\n"
                + "  FROM bookitem b \n"
                + "  JOIN book b1 ON b.BookId = b1.Id\n"
                + "  JOIN author a ON b1.AuthorId = a.Id\n"
                + "  JOIN publisher p ON b1.PublisherId = p.Id\n"
                + "  WHERE b.BarCode = ? LIMIT 1";
        try (Connection connection = jdbcUtils.connect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, barCode);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int pId = rs.getInt("PId");
                String pName = rs.getString("PName");
                String pAddress = rs.getString("PAddress");
                Publisher publisher = new Publisher(pId, pName, pAddress);

                int aId = rs.getInt("AuthorId");
                String aName = rs.getString("AuthorName");
                String aBiography = rs.getString("AuthorBiography");
                Author author = new Author(aId, aName, aBiography);

                int bId = rs.getInt("BookId");
                String bTitle = rs.getString("Title");
                String bSummary = rs.getString("Summary");
                int pages = rs.getInt("Pages");
                String language = rs.getString("Language");
                Book book = new Book(bId, bTitle, bSummary, pages, language, author, publisher);

                double price = rs.getDouble("Price");
                double discount = rs.getDouble("Discount");
                bookItem = new BookItem(barCode, price, discount, book);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bookItem;
    }

    @Override
    public PagedResult<BookItem> getList(String search, int page, int limit) {
        PagedResult<BookItem> res = new PagedResult<>();
        res.setTotalRecord(countTotal(search));
        res.setTotalPages((int) Math.ceil((double) res.getTotalRecord() / limit));
        if (res.getTotalRecord() == 0) {
            return res;
        }

        int start = (page - 1) * limit;

        res.setItems(getListBook(search, start, limit));
        return res;
    }

    private int countTotal(String search) {
        int total = 0;
        if (search == null) {
            search = "";
        }
        String sqlTotal = "SELECT COUNT(*) as total FROM bookItem WHERE BookId IN (SELECT id FROM book WHERE Title LIKE '%" + search + "%')";
        try (Connection connection = jdbcUtils.connect(); PreparedStatement statement = connection.prepareStatement(sqlTotal)) {
            System.out.println(sqlTotal);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return total;
    }

    private List<BookItem> getListBook(String search, int start, int limit) {
        List<BookItem> books = new ArrayList<BookItem>();
        String sql = "SELECT b.BarCode AS BarCode, b.Price AS Price, b.Discount AS Discount,\n"
                + "  b1.Id AS BookId, b1.Title AS Title, b1.Summary AS Summary, b1.Pages AS Pages, b1.Language AS Language,\n"
                + "  a.Id AS AuthorId, a.Name AS AuthorName, a.Biography AS AuthorBiography,\n"
                + "  p.Id AS PId, p.Name AS PName, p.Address AS PAddress\n"
                + "  FROM bookitem b \n"
                + "  JOIN book b1 ON b.BookId = b1.Id AND b1.Title LIKE ? \n"
                + "  JOIN author a ON b1.AuthorId = a.Id\n"
                + "  JOIN publisher p ON b1.PublisherId = p.Id\n"
                + "  ORDER BY b.CreatedDate DESC\n"
                + "  LIMIT ?, ?";
        try (Connection connection = jdbcUtils.connect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + search + "%");
            statement.setInt(2, start);
            statement.setInt(3, limit);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int pId = rs.getInt("PId");
                String pName = rs.getString("PName");
                String pAddress = rs.getString("PAddress");
                Publisher publisher = new Publisher(pId, pName, pAddress);

                int aId = rs.getInt("AuthorId");
                String aName = rs.getString("AuthorName");
                String aBiography = rs.getString("AuthorBiography");
                Author author = new Author(aId, aName, aBiography);

                int bId = rs.getInt("BookId");
                String bTitle = rs.getString("Title");
                String bSummary = rs.getString("Summary");
                int pages = rs.getInt("Pages");
                String language = rs.getString("Language");
                Book book = new Book(bId, bTitle, bSummary, pages, language, author, publisher);

                String barCode = rs.getString("BarCode");
                double price = rs.getDouble("Price");
                double discount = rs.getDouble("Discount");
                BookItem bookItem = new BookItem(barCode, price, discount, book);
                books.add(bookItem);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return books;
    }

    @Override
    public void addToCart(String barCode, int cartId, int quantity) {
        BoughtBookItem item = new BoughtBookItem();
        String sqlCheck = "SELECT * FROM boughtBookItem WHERE BookBarCode = ? AND CartId = ? LIMIT 1";
        try (Connection connection = jdbcUtils.connect(); PreparedStatement statement = connection.prepareStatement(sqlCheck)) {
            statement.setString(1, barCode);
            statement.setInt(2, cartId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("Id");
                int mQuantity = rs.getInt("Quantity");
                item.setId(id);
                item.setQuantity(mQuantity);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (item.getId() == 0) {
            String sqlInsert = "INSERT INTO boughtBookItem (Quantity, BookBarCode, CartId) VALUES (?, ?, ?)";
            try (Connection connection = jdbcUtils.connect(); PreparedStatement statement = connection.prepareStatement(sqlInsert)) {
                statement.setInt(1, quantity);
                statement.setString(2, barCode);
                statement.setInt(3, cartId);
                statement.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            String sqlUpdate = "UPDATE boughtBookItem SET Quantity = ? WHERE Id = ?";
            try(Connection connection = jdbcUtils.connect(); PreparedStatement statement = connection.prepareStatement(sqlUpdate)){
                statement.setInt(1, item.getQuantity() + quantity);
                statement.setInt(2, item.getId());
                statement.executeUpdate();
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }

    }
}
