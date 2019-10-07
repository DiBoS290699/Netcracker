package buildings.Exceptions;

/**Ошибка некорретного количества комнат в помещении */
public class InvalidRoomsCountException extends IllegalArgumentException {
    public InvalidRoomsCountException(String msg) {
        super(msg);
    }
}
