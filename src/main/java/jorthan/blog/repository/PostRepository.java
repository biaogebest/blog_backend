package jorthan.blog.repository;

import jorthan.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByExist(boolean exist, Pageable pageable);

    Optional<Post> findByIdAndExist(Long id, boolean exist);

    Page<Post> findByCategoryAndExist(String category, Boolean exist, Pageable pageable);
}
