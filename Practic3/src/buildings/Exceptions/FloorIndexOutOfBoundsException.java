package buildings.Exceptions;

/**Ошибка выхода за границы номеров этажей*/
public class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException{
    public FloorIndexOutOfBoundsException(String msg) {
        super(msg);
    }
}
