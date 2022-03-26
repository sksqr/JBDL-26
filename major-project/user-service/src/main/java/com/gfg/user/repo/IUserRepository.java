package com.gfg.user.repo;

import com.gfg.user.dbentities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {

}
