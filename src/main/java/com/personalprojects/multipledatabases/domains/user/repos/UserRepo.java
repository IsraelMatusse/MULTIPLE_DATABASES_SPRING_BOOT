package com.personalprojects.multipledatabases.domains.user.repos;

import com.personalprojects.multipledatabases.domains.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
