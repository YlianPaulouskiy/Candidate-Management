package by.krainet.management.persistence.repository;

import by.krainet.management.persistence.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>,
        QuerydslPredicateExecutor<Test> {

    boolean existsByName(String name);

}
