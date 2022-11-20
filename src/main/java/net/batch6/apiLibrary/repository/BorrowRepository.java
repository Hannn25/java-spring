package net.batch6.apiLibrary.repository;

import net.batch6.apiLibrary.model.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

}
