package com.example.mvc.controller;

import com.example.mvc.model.ApiResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @PostMapping("/upload")
    public ApiResponse<Map<String, String>> uploadFile(
            @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ApiResponse.error(400, "请选择文件");
        }

        try {
            String fileName = file.getOriginalFilename();
            String filePath = "uploads/" + fileName;
            File dest = new File(filePath);
            dest.getParentFile().mkdirs();
            file.transferTo(dest);

            Map<String, String> result = new HashMap<>();
            result.put("fileName", fileName);
            result.put("filePath", filePath);
            result.put("fileSize", file.getSize() + " bytes");

            return ApiResponse.success(result);
        } catch (IOException e) {
            return ApiResponse.error(500, "文件上传失败：" + e.getMessage());
        }
    }

    @PostMapping("/uploads")
    public ApiResponse<List<Map<String, String>>> uploadFiles(
            @RequestParam("files") MultipartFile[] files) {
        List<Map<String, String>> results = new ArrayList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    String fileName = file.getOriginalFilename();
                    String filePath = "uploads/" + fileName;
                    File dest = new File(filePath);
                    dest.getParentFile().mkdirs();
                    file.transferTo(dest);

                    Map<String, String> result = new HashMap<>();
                    result.put("fileName", fileName);
                    result.put("filePath", filePath);
                    result.put("fileSize", file.getSize() + " bytes");
                    results.add(result);
                } catch (IOException e) {
                    return ApiResponse.error(500, "文件上传失败：" + e.getMessage());
                }
            }
        }

        return ApiResponse.success(results);
    }

    @GetMapping("/download/{fileName}")
    public void downloadFile(@PathVariable String fileName, HttpServletResponse response) {
        File file = new File("uploads/" + fileName);

        if (!file.exists()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", 
                         "attachment; filename=\"" + fileName + "\"");

        try (FileInputStream in = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}