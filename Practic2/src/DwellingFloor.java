public class DwellingFloor {
    private Flat flats[];   //Массив квартир на этаже

    /**Конструктор инициализации через количество квартир*/
    public DwellingFloor(int numberFlats) throws IllegalArgumentException{
        if (numberFlats <= 0)   //ызов исключения при некорректном количестве квартир
            throw new IllegalArgumentException("Incorrect number of flats!");
        this.flats = new Flat[numberFlats];
    }
    /**Конструктор инициализации через массив квартир*/
    public DwellingFloor(Flat[] newFlats) {
        this.flats = newFlats;
    }

    public int getNumberFlats() {
        return this.flats.length;
    }

    public double getSumAreaFlats(){
        int numberFlats = this.flats.length;
        double sumArea = 0;
        for (int i = 0; i < numberFlats; ++i) {
            sumArea += this.flats[i].getArea();
        }
        return sumArea;
    }

    public int getSumRooms() {
        int numberFlats = this.flats.length;
        int sumRooms = 0;
        for (int i = 0; i < numberFlats; ++i) {
            sumRooms += this.flats[i].getRooms();
        }
        return sumRooms;
    }

    public Flat[] getFlats() {
        return this.flats;
    }

    public Flat getFlatByNumber(int numberFlat) {
        return this.flats[numberFlat];
    }

    public void setFlatByNumber(int numberFlat, Flat newFlat) {
        this.flats[numberFlat] = newFlat;
    }

    public void addFlatByNumber(int numberFlat, Flat newFlat) {
        int numberFlats = this.flats.length + 1;
        Flat[] tmp = new Flat[numberFlats];
        for (int i = 0; i < numberFlats; ++i) {
            if (i != numberFlat) {
                tmp[i] = this.flats[i];
            }
            else {
                tmp[numberFlat] = newFlat;
            }
        }
        this.flats = tmp;
    }

    public void deleteFlatByNumber(int numberFlat) {
        int numberFlats = this.flats.length;
        Flat[] tmp = new Flat[numberFlats - 1];
        for (int i = 0; i < numberFlats; ++i) {
            if (i != numberFlat) {
                tmp[i] = this.flats[i];
            }
            else {
                --i;
            }
        }
        this.flats = tmp;
    }

    public Flat getBestSpace() {
        double maxArea = 0, numberFlats = this.flats.length;
        Flat bestSpace = new Flat();
        for (Flat flat : flats) {
            if (maxArea < flat.getArea()) {
                maxArea = flat.getArea();
                bestSpace = flat;
            }
        }
        return bestSpace;
    }
}