package com.temalab.forum.repository;

import com.temalab.forum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
