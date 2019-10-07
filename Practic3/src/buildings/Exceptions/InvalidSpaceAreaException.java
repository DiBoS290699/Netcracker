package buildings.Exceptions;

/**Ошибка некорретной площади помещения*/
public class InvalidSpaceAreaException extends IllegalArgumentException {
    public InvalidSpaceAreaException(String msg) {
        super(msg);
    }
}
