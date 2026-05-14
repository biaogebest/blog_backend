package jorthan.blog.repository;

import jorthan.blog.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndExist(Long id, boolean exist);

    @Query("select c from Comment c where c.post.id = :postId and c.exist = :exist")
    Page<Comment> findByPostIdAndExist(Long postId, boolean exist, Pageable pageable);

    // 新增：获取某用户的所有评论
    @Query("SELECT c FROM Comment c WHERE c.user.id = :userId AND c.exist = true")
    List<Comment> findByUserId(@Param("userId") Long userId);

}
