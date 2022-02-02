package com.mettle.featureflag.repository;

import com.mettle.featureflag.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {



}
