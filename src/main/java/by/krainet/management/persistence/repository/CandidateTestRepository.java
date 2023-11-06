package by.krainet.management.persistence.repository;

import by.krainet.management.persistence.model.CandidateTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateTestRepository extends JpaRepository<CandidateTest, Long>,
        QuerydslPredicateExecutor<CandidateTest> {
}
