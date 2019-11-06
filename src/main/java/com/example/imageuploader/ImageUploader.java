package com.example.imageuploader;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.imageuploader.model.Image;
import com.example.imageuploader.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploader {

    private Cloudinary cloudinary;
    private ImageRepo imageRepo;

    @Autowired
    public ImageUploader(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dpkpccf32",
                "api_key", "632386747929182",
                "api_secret", "R_22jPs9lboHq_P65bKjtZugSYE"));
    }

    public String uploadFileAndSaveToDatabase(String path) {
        File file = new File(path);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            imageRepo.save(new Image(uploadResult.get("url").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadResult.get("url").toString();
    }
}
