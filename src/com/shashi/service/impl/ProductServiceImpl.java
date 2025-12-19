package com.shashi.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shashi.beans.Product;
import com.shashi.service.ProductService;
import com.shashi.utility.DBUtil;

public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        Connection con = DBUtil.provideConnection();

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setProdId(rs.getString("prodid"));
                p.setProdName(rs.getString("prodname"));
                p.setProdType(rs.getString("prodtype"));
                p.setProdInfo(rs.getString("prodinfo"));
                p.setProdPrice(rs.getDouble("prodprice"));
                p.setProdQuantity(rs.getInt("prodquantity"));
                p.setProdImage(rs.getString("prodimage"));
                products.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getAllProductsByType(String type) {

        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE prodtype=?";

        Connection con = DBUtil.provideConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, type);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product p = new Product();
                    p.setProdId(rs.getString("prodid"));
                    p.setProdName(rs.getString("prodname"));
                    p.setProdType(rs.getString("prodtype"));
                    p.setProdInfo(rs.getString("prodinfo"));
                    p.setProdPrice(rs.getDouble("prodprice"));
                    p.setProdQuantity(rs.getInt("prodquantity"));
                    p.setProdImage(rs.getString("prodimage"));
                    products.add(p);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
}
