package com.saurabh.erp.controller;

import com.saurabh.erp.payload.FileResponse;
import com.saurabh.erp.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("api/v1/employee/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @Value("${project.image}")
    private String path;

    //to serve images
    @GetMapping(value = "/profile/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) {
        try {
            InputStream resource = fileService.getImage(path, imageName);

            if (resource == null) {
                // serve a default image if the requested image is not found
                resource = fileService.getImage(path, "default.jpg");
                if (resource == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Default image also not found");
                }
                response.setContentType("image/jpeg");
                StreamUtils.copy(resource, response.getOutputStream());
            }
            response.setContentType("image/jpeg");
            StreamUtils.copy(resource, response.getOutputStream());
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while retrieving the image.");
        }


    }

    //use upload?imgName=empName_id.jpg
    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(
            @RequestParam("image") MultipartFile image,
            @RequestParam("imgName") String imgName
    ){
        String fileName = null;
        try {
            fileName = fileService.uploadImage(path,image,imgName);
        } catch (IOException e) {
            return new ResponseEntity<>(new FileResponse(null, "image upload failed"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(new FileResponse(fileName, "image successfully uploaded"), HttpStatus.OK);
    }


}
