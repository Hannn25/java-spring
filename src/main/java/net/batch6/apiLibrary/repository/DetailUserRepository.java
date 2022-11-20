package net.batch6.apiLibrary.repository;


import net.batch6.apiLibrary.model.entity.DetailUser;
import net.batch6.apiLibrary.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DetailUserRepository extends JpaRepository<DetailUser, Long> {
    Optional<DetailUser> findByUserId(User user);
}
