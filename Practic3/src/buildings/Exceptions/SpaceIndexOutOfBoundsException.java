package buildings.Exceptions;

/**Ошибка выхода за границы номеров помещений */
public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public SpaceIndexOutOfBoundsException(String msg) {
        super(msg);
    }
}
