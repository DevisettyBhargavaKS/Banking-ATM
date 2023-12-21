package com.CodeWithBhargav.Repository;

import com.CodeWithBhargav.Model.TransactionalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionalTypeRepository extends JpaRepository<TransactionalType, Integer> {
    TransactionalType findByType(String type);

    @Query("FROM TransactionalType tt WHERE tt.id=:id")
    TransactionalType findById(int id);
}
