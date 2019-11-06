package com.example.imageuploader.repo;

import com.example.imageuploader.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser,Long> {

    AppUser findByUsername(String username);

}
