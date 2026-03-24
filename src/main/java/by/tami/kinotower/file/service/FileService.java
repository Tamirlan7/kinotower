package by.tami.kinotower.file.service;

import by.tami.kinotower.file.dto.FileDto;
import by.tami.kinotower.file.exception.IllegalFileContentTypeException;
import by.tami.kinotower.file.mapper.FileMapper;
import by.tami.kinotower.file.model.File;
import by.tami.kinotower.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final Logger logger = LoggerFactory.getLogger(FileService.class);
    private final FileRepository fileRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public FileDto saveFile(MultipartFile file) {
        String contentType = file.getContentType();

        if (contentType == null ||
                (!contentType.startsWith("image") &&
                        !contentType.startsWith("video"))) {
            throw new IllegalFileContentTypeException();
        }

        String fileOriginalName = file.getOriginalFilename();
        String fileUniqueName = UUID.randomUUID() + "_" + fileOriginalName;
        Path targetLocation = Paths.get(uploadDir)
                .resolve(contentType.startsWith("image") ? "images" : "films")
                .resolve(fileUniqueName);


        try {
            Files.createDirectories(targetLocation);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            File fileEntity = new File();
            fileEntity.setName(fileUniqueName);
            fileEntity.setPath(targetLocation.toString());
            fileEntity.setType(file.getContentType());

            return FileMapper.toDto(fileRepository.save(fileEntity));
        } catch (IOException e) {
            logger.error("Cannot create directory {}", targetLocation);
        }

        return null;
    }
}
