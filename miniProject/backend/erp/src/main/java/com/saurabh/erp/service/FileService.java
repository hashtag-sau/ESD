package com.saurabh.erp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public interface FileService {
    String uploadImage(String path, MultipartFile file, String name) throws IOException;

    InputStream getImage(String path,String fileName) throws FileNotFoundException;


}
