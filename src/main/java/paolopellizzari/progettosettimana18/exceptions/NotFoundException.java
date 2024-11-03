package paolopellizzari.progettosettimana18.exceptions;

public class NotFoundException extends RuntimeException {
    public <T> NotFoundException(Class<T> entity) {
        super(entity.getSimpleName() + " not found!");
    }
}
