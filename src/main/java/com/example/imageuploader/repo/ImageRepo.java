package com.example.imageuploader.repo;

import com.example.imageuploader.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {

}
