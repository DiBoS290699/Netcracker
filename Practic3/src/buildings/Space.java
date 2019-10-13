package buildings;

/**Интерфейс помещения здания
 * Содержит гетеры и сетеры классов Flat и Office*/
public interface Space {
    double getArea();
    void setArea(double a);
    int getRooms();
    void setRooms(int r);
}
