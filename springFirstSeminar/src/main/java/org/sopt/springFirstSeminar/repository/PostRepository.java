package org.sopt.springFirstSeminar.repository;

import org.sopt.springFirstSeminar.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
