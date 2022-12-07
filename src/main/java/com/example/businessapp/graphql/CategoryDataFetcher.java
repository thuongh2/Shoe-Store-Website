package com.example.businessapp.graphql;

import com.example.businessapp.entity.Category;
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
public class CategoryDataFetcher extends BaseService {

    @DgsQuery
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    @DgsMutation
    public String createCategory(@InputArgument(name = "category") Category category){
        categoryRepository.save(category);
        return category.getId();
    }

    @DgsMutation
    public String updateCategory(@InputArgument(name = "category") Category category) throws Exception {
        if(category.getId().isBlankOrNull()){
            throw new Exception("Không tìm thấy sản phẩm");
        }
        categoryRepository.save(category);
        return category.getId();
    }

    @DgsMutation
    public boolean deleteCategory(@InputArgument(name = "categoryId") String categoryId) throws Exception {
        if(categoryId.isBlankOrNull()){
            throw new Exception("Không tìm thấy sản phẩm");
        }
        Category category = categoryRepository.findById(categoryId).get();
        categoryRepository.delete(category);
        return true;
    }
}
