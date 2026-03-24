package by.tami.kinotower.file.mapper;

import by.tami.kinotower.file.dto.FileDto;
import by.tami.kinotower.file.model.File;

public class FileMapper {
    public static FileDto toDto(File file) {
        FileDto fileDto = new FileDto();
        fileDto.setId(file.getId());
        fileDto.setPath(file.getPath());
        fileDto.setName(file.getName());
        fileDto.setType(file.getType());
        return fileDto;
    }
}
