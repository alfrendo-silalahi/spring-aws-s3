package com.alfrendosilalahi.spring.aws.s3;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@RequestParam("image-name") String imageName) throws IOException {
        return imageService.getImage(imageName);
    }

    @PostMapping("/upload")
    public boolean uploadImage(@RequestPart("image") MultipartFile image) {
        return imageService.uploadFile(image);
    }

}
