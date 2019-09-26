public class Flat {
    public static final double DEFAULT_AREA = 50;
    public static final int DEFAULT_ROOMS = 2;
    private double area;       //Размер комнаты/Площадь (в кв.м)
    private int rooms;          //Количество комнат (в шт)

    /**Конструктор по умолчанию ничего не делает, т.к. по умолчанию area = 50, rooms = 2*/
    public Flat() {
        this.area = DEFAULT_AREA;
        this.rooms = DEFAULT_ROOMS;
    }
    /**Конструктор с инициализацией площади (кол-во комнат 2)*/
    public Flat(double a) { this.area = a; this.rooms = DEFAULT_ROOMS; }
    /**Конструктор с инициализацией площади и кол-ва комнат*/
    public Flat(double a, int r) { this.area = a; this.rooms = r; }
    /**Гетер поля площади*/
    public double getArea() { return this.area; }
    /**Сетер поля площади*/
    public void setArea(double a) { this.area = a; }
    /**Гетер поля комнат*/
    public int getRooms() { return this.rooms; }
    /**Сетер поля комнат*/
    public void setRooms(int r) { this.rooms = r; }
}
