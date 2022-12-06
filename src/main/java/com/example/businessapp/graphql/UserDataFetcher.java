package com.example.businessapp.graphql;

import com.example.businessapp.entity.Product;
import com.example.businessapp.entity.User;
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
public class UserDataFetcher extends BaseService {

    @DgsQuery
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @DgsQuery
    public User getUserById(@InputArgument(name = "id") String id){
        return userRepository.findById(id).get();
    }

    @DgsQuery
    public List<User> getUserByName(@InputArgument(name = "name") String name){
        return userRepository.findByName(name);
    }

    @DgsMutation
    public String createUser(@InputArgument(name = "user") User user){
        userRepository.save(user);
        return user.getId();
    }

    @DgsMutation
    public String updateUser(@InputArgument(name = "user") User user) throws Exception {
        if(user.getId().isBlankOrNull()){
            throw new Exception("Không tìm thấy sản phẩm");
        }
        userRepository.save(user);
        return user.getId();
    }

    @DgsMutation
    public boolean deleteUser(@InputArgument(name = "userId") String userId) throws Exception {
        if(userId.isBlankOrNull()){
            throw new Exception("Không tìm thấy sản phẩm");
        }
        User user = userRepository.findById(userId).get();
        userRepository.delete(user);
        return true;
    }
}
