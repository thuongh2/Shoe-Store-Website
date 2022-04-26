package com.example.businessapp.QueryCommon;

public class QueryProduct {

     static final String PRODUCT_QUERY = "select * from Product pro," +
            " (select * from Category cat where cat.active = true) as cat1,"+
            " (select * from Size si where si.active = true) as si1, " +
            " (select * from Image img where img.active = true) as img1, " +
            "where pro.category_id = cat1.id and pro.id = si1.pro_id and pro.id = img1.product_id";
}
