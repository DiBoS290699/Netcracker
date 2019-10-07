package buildings.OfficeBuilding;

public class Office {
    public static final double DEFAULT_AREA = 250;
    public static final int DEFAULT_ROOMS = 1;
    private double area;       //Размер комнаты/Площадь (в кв.м)
    private int rooms;          //Количество комнат (в шт)

    /**Конструктор по умолчанию area = 250, rooms = 1*/
    public Office() {
        this.area = DEFAULT_AREA;
        this.rooms = DEFAULT_ROOMS;
    }
    /**Конструктор с инициализацией площади (кол-во комнат 1)*/
    public Office(double a) { this.area = a; this.rooms = DEFAULT_ROOMS; }
    /**Конструктор с инициализацией площади и кол-ва комнат*/
    public Office(double a, int r) { this.area = a; this.rooms = r; }

    public Office(Office newOf) { this.area = newOf.area; this.rooms = newOf.rooms; }
    /**Гетер поля площади*/
    public double getArea() { return this.area; }
    /**Сетер поля площади*/
    public void setArea(double a) { this.area = a; }
    /**Гетер поля комнат*/
    public int getRooms() { return this.rooms; }
    /**Сетер поля комнат*/
    public void setRooms(int r) { this.rooms = r; }
    /**Отображение содержимого полей класса*/
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("(Area=").append(this.area);
        str.append("; Rooms=").append(this.rooms);
        str.append(") ");
        return str.toString();
    }
}
