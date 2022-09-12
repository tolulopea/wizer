package com.tolulope.wizertest.repository;

import com.tolulope.wizertest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsBookByName(String name);
}
