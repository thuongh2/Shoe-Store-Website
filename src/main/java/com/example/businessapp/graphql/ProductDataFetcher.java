package com.example.businessapp.graphql;

import com.example.businessapp.entity.Product;
import com.example.businessapp.manager.BaseService;
import com.example.businessapp.manager.ProductManager;
import com.example.businessapp.utils.Extensions;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@DgsComponent
@ExtensionMethod(Extensions.class)
public class ProductDataFetcher extends BaseService {

    @Autowired
    protected ProductManager productManager;

    @DgsQuery
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @DgsQuery
    public Product getProductById(@InputArgument(name = "id") String id){
        return productRepository.findById(id).get();
    }

    @DgsQuery
    public List<Product> getProductByName(@InputArgument(name = "name") String name){
        return productRepository.findByName(name);
    }

    @DgsQuery
    public List<Product> getProductLikeName(@InputArgument(name = "name") String name){
        return productManager.findProductByNameLike(name);
    }

    @DgsQuery
    public List<Product> getProductByCategory(@InputArgument(name = "category") String category){
        return productRepository.findProductByCategoryId(category);
    }

    @DgsMutation
    public String createProduct(@InputArgument(name = "product") Product product){
        productRepository.save(product);
        return product.getId();
    }

    @DgsMutation
    public String updateProduct(@InputArgument(name = "product") Product product) throws Exception {
        if(product.getId().isBlankOrNull()){
            throw new Exception("Không tìm thấy sản phẩm");
        }
        productRepository.save(product);
        return product.getId();
    }

    @DgsMutation
    public boolean deleteProduct(@InputArgument(name = "productId") String productId) throws Exception {
        if(productId.isBlankOrNull()){
            throw new Exception("Không tìm thấy sản phẩm");
        }
        Product product = productRepository.findById(productId).get();
        productRepository.delete(product);
        return true;
    }

}
