package ru.lutsenko.files.service;

import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FileStorageService {

    private final MinioClient minioClient;

    public FileStorageService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Value("${minio.bucketName}")
    private String bucketName;

    public List<String> uploadFiles(MultipartFile[] files) throws Exception {
        try {
            // Проверяем, существует ли бакет, если нет - создаем
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            // Загружаем каждый файл и возвращаем их ID
            List<String> fileIds = new ArrayList<>();
            for (MultipartFile file : files) {
                String fileId = uploadSingleFile(file);
                fileIds.add(fileId);
            }
            return fileIds;
        } catch (MinioException e) {
            throw new Exception("Error occurred while uploading files to MinIO: " + e.getMessage());
        }
    }

    private String uploadSingleFile(MultipartFile file) {
        try {
            // Генерируем уникальный ID для файла
            String fileId = UUID.randomUUID().toString();

            // Загружаем файл в MinIO
            InputStream inputStream = file.getInputStream();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileId) // Используем ID как имя объекта в MinIO
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            return fileId; // Возвращаем ID файла
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file: " + file.getOriginalFilename() + ". Error: " + e.getMessage());
        }
    }

    public List<Map<String, String>> getFilesByIds(List<String> fileIds) throws Exception {
        try {
            // Проверяем, существует ли бакет
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                throw new Exception("Bucket does not exist: " + bucketName);
            }

            // Собираем информацию о файлах
            List<Map<String, String>> filesInfo = new ArrayList<>();
            for (String fileId : fileIds) {
                if (fileId == null || fileId.trim().isEmpty()) {
                    filesInfo.add(Map.of(
                            "id", fileId,
                            "error", "File ID is invalid"
                    ));
                    continue;
                }

                try {
                    // Проверяем существование объекта
                    minioClient.statObject(StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileId)
                            .build());

                    // Генерируем URL для скачивания
                    String fileUrl = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                            .bucket(bucketName)
                            .object(fileId)
                            .method(Method.GET)
                            .build());

                    filesInfo.add(Map.of(
                            "id", fileId,
                            "url", fileUrl
                    ));
                } catch (Exception e) {
                    filesInfo.add(Map.of(
                            "id", fileId,
                            "error", "File not found or error occurred: " + e.getMessage()
                    ));
                }
            }

            return filesInfo;
        } catch (MinioException e) {
            throw new Exception("Error occurred while retrieving files from MinIO: " + e.getMessage());
        }
    }

    public Map<String, String> getFileById(String fileId) throws Exception {
        try {
            // Проверяем существование бакета
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                throw new Exception("Bucket does not exist: " + bucketName);
            }

            // Получаем информацию о файле
            StatObjectResponse stat = minioClient.statObject(StatObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileId)
                    .build());

            // Генерируем presigned URL
            String fileUrl = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(fileId)
                    .build());

            // Получаем имя оригинального файла (если оно было сохранено, например, как metadata или objectName)
            String objectName = fileId; // по умолчанию — fileId
            String contentType = stat.contentType(); // MIME type, например application/pdf

            // Если вы знаете, что имя файла хранится в метаданных или где-то ещё, можно извлечь его оттуда
            // Например, если вы сохраняли оригинальное имя файла при загрузке:
            // String originalFileName = stat.userMetadata().get("x-amz-meta-filename");

            // Извлекаем расширение из objectName или другого источника
            String fileType = "other";
            if (objectName != null && objectName.contains(".")) {
                String ext = objectName.substring(objectName.lastIndexOf(".") + 1).toLowerCase();
                switch (ext) {
                    case "jpg":
                    case "jpeg":
                    case "png":
                    case "gif":
                    case "webp":
                    case "svg":
                        fileType = "image";
                        break;
                    case "pdf":
                        fileType = "application/pdf";
                        break;
                    case "docx":
                        fileType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
                        break;
                    case "doc":
                        fileType = "application/msword";
                        break;
                    case "txt":
                        fileType = "text/plain";
                        break;
                    case "xls":
                    case "xlsx":
                        fileType = "application/vnd.ms-excel";
                        break;
                    default:
                        fileType = "other";
                }
            }

            return Map.of(
                    "id", fileId,
                    "url", fileUrl,
                    "fileType", fileType,
                    "contentType", contentType,
                    "fileName", objectName
            );

        } catch (Exception e) {
            throw new Exception("File not found or error occurred: " + e.getMessage());
        }
    }
}
