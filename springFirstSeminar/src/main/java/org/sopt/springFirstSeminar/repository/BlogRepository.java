package org.sopt.springFirstSeminar.repository;

import org.sopt.springFirstSeminar.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
