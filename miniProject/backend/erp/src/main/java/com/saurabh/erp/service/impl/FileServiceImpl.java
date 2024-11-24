package com.saurabh.erp.service.impl;

import com.saurabh.erp.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file, String name) throws IOException {


        //Fullpath
        String filePath = path+ File.separator+name;


        //Create folder if not created
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        //file copy or storing
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return name;
    }

    @Override
    public InputStream getImage(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
