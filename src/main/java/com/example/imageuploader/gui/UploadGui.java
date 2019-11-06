package com.example.imageuploader.gui;

import com.example.imageuploader.ImageUploader;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route("upload-image")
public class UploadGui extends VerticalLayout {

    private ImageUploader imageUploader;

    @Autowired
    public UploadGui(ImageUploader imageUploader) {
        this.imageUploader = imageUploader;

        Label label = new Label();
        TextField textField = new TextField();
        Button button = new Button("upload");
        button.addClickListener(buttonClickEvent ->
        {
            String uploadedImage = imageUploader.uploadFileAndSaveToDatabase(textField.getValue());
            Image image = new Image(uploadedImage, "no pic");
            label.setText("upload succeed");
            add(label);
            add(image);
        });
        add(textField);
        add(button);
    }
}
