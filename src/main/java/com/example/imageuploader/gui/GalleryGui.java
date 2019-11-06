package com.example.imageuploader.gui;

import com.example.imageuploader.model.Image;
import com.example.imageuploader.repo.ImageRepo;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("gallery")
public class GalleryGui extends VerticalLayout {

    private ImageRepo imageRepo;

    @Autowired
    public GalleryGui(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;

        List<Image> all = imageRepo.findAll();

        all.stream().forEach(element -> {
            com.vaadin.flow.component.html.Image image =new com.vaadin.flow.component.html.Image(element.getImageAdress(),"no pic");
            add(image);

        } );
    }
}