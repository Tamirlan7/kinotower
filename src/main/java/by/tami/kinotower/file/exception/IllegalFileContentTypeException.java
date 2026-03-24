package by.tami.kinotower.file.exception;

public class IllegalFileContentTypeException extends RuntimeException {
    public IllegalFileContentTypeException() {
        super("Illegal file content type, only videos or images are valid.");
    }
}
