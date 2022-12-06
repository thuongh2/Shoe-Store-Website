package com.example.businessapp.graphql;

import com.example.businessapp.entity.Product;
import com.example.businessapp.manager.BaseService;
import com.example.businessapp.utils.Extensions;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.experimental.ExtensionMethod;

import java.util.List;

@DgsComponent
@ExtensionMethod(Extensions.class)
public class ProductDataFetcher extends BaseService {

    @DgsQuery
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @DgsMutation
    public String createProduct(@InputArgument(name = "product") Product product){
        productRepository.save(product);
        return product.getId();
    }

}
