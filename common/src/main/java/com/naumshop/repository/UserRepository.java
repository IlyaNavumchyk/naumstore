package com.naumshop.repository;

import com.naumshop.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u left join fetch u.roles where u.id = :id")
    Optional<User> findByIdd(@Param("id") Long id);
}
