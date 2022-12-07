package com.example.businessapp.graphql;

import com.example.businessapp.entity.Comment;
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
public class CommentDataFetcher extends BaseService {
    @DgsQuery
    public List<Comment> getCommentOfProduct(@InputArgument(name = "productId") String productId){
        return commentRepository.findByProductId(productId);
    }

    @DgsMutation
    public String createComment(@InputArgument(name = "commnet") Comment comment){
        commentRepository.save(comment);
        return comment.getId();
    }

    @DgsMutation
    public String updateComment(@InputArgument(name = "commnet") Comment comment) throws Exception {
        if(comment.getId().isBlankOrNull()){
            throw new Exception("Không tìm thấy sản phẩm");
        }
        commentRepository.save(comment);
        return comment.getId();
    }

    @DgsMutation
    public boolean deleteComment(@InputArgument(name = "commentId") String commentId) throws Exception {
        if(commentId.isBlankOrNull()){
            throw new Exception("Không tìm thấy sản phẩm");
        }
        Comment comment = commentRepository.findById(commentId).get();
        commentRepository.delete(comment);
        return true;
    }
}
