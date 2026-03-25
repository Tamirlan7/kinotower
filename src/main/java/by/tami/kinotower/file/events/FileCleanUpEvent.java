package by.tami.kinotower.file.events;

import by.tami.kinotower.file.model.File;

import java.util.List;

public class FileCleanUpEvent {
    List<File> files;

    public FileCleanUpEvent(List<File> files) {
        this.files = files;
    }
}
