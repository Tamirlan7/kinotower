package by.tami.kinotower.file.events;

import by.tami.kinotower.web.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Component
public class FileCleanUpListener {

    @EventListener
    public void handleFileCleanUp(FileCleanUpEvent event) {
        for (var file : event.files) {
            try {
                Files.deleteIfExists(Paths.get(file.getPath()));
            } catch (IOException e) {
                log.error("FileCleanUpListener.handleFileCleanUp(), error occurred when deleting file, message: {}", e.getMessage());
                throw new BadRequestException("exception caused in the FileCleanUpListener,handleFileCleanUp(), message: " + e.getMessage());
            }
        }
    }
}
