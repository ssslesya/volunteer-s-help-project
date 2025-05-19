package ru.lutsenko.files.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.lutsenko.files.service.FileStorageService;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileStorageService fileStorageService;

    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        try {
            List<String> fileIds = fileStorageService.uploadFiles(files);
            return ResponseEntity.ok(fileIds);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(List.of(e.getMessage()));
        }
    }

    @PostMapping("/get-by-ids")
    public ResponseEntity<List<Map<String, String>>> getFilesByIds(@RequestBody List<String> fileIds) {
        try {
            List<Map<String, String>> filesInfo = fileStorageService.getFilesByIds(fileIds);
            return ResponseEntity.ok(filesInfo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(List.of(Map.of("error", e.getMessage())));
        }
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<Map<String, String>> getFileById(@PathVariable String fileId) {
        try {
            Map<String, String> fileData = fileStorageService.getFileById(fileId);
            return ResponseEntity.ok(fileData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
