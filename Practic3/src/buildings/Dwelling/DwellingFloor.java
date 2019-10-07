package buildings.Dwelling;

import buildings.Exceptions.SpaceIndexOutOfBoundsException;

public class DwellingFloor {
    private Flat flats[];   //Массив квартир на этаже

    /**Конструктор инициализации через количество квартир*/
    public DwellingFloor(int numberFlats) throws IllegalArgumentException{
        if (numberFlats <= 0)   //вызов исключения при некорректном количестве квартир
            throw new IllegalArgumentException("Incorrect number of flats!");
        this.flats = new Flat[numberFlats];
        for (int i = 0; i < numberFlats; ++i) {
            this.flats[i] = new Flat();
        }
    }
    /**Конструктор инициализации через массив квартир*/
    public DwellingFloor(Flat[] newFlats) {
        int length = newFlats.length;
        this.flats = new Flat[length];
        for (int i = 0; i < length; ++i) {
            this.flats[i] = new Flat(newFlats[i].getArea(), newFlats[i].getRooms());
        }
    }
    /**Гетер количества квартир*/
    public int getNumberFlats() {
        return this.flats.length;
    }
    /**Гетер общей площади квартир*/
    public double getSumAreaFlats(){
        int numberFlats = this.flats.length;
        double sumArea = 0;
        for (int i = 0; i < numberFlats; ++i) {
            sumArea += this.flats[i].getArea();
        }
        return sumArea;
    }
    /**Гетер общего количества комнат*/
    public int getSumRoomsFlats() {
        int numberFlats = this.flats.length;
        int sumRooms = 0;
        for (int i = 0; i < numberFlats; ++i) {
            sumRooms += this.flats[i].getRooms();
        }
        return sumRooms;
    }
    /**Гетер массива квартир*/
    public Flat[] getFlats() {
        return this.flats;
    }
    /**Гетер квартиры по его номеру*/
    public Flat getFlatOnFloor(int numberFlat) throws SpaceIndexOutOfBoundsException {
        if (numberFlat < 0 || this.flats.length < numberFlat) {
            throw new SpaceIndexOutOfBoundsException("Incorrect numberFlat: " + numberFlat);
        }
        return this.flats[numberFlat];
    }
    /**Сетер квартиры по его номеру*/
    public void setFlatOnFloor(int numberFlat, Flat newFlat) throws SpaceIndexOutOfBoundsException {
        if (numberFlat < 0 || this.flats.length < numberFlat) {
            throw new SpaceIndexOutOfBoundsException("Incorrect numberFlat: " + numberFlat);
        }
        this.flats[numberFlat] = newFlat;
    }
    /**Добавление квартиры на этаж по номеру и объекту квартиры*/
    public void addFlatOnFloor(int numberFlat, Flat newFlat) throws SpaceIndexOutOfBoundsException {
        if (numberFlat < 0 || numberFlat > this.getNumberFlats()) {
            throw new SpaceIndexOutOfBoundsException("Incorrect numberFlat: " + numberFlat);
        }
        int newSumFlats = this.flats.length + 1;
        Flat[] tmp = new Flat[newSumFlats];
        int i = 0;
        for (; i < numberFlat; ++i) {
            tmp[i] = this.flats[i];
        }
        tmp[numberFlat] = newFlat;
        for (int k = i + 1; k < newSumFlats; ++i, ++k) {
            tmp[k] = this.flats[i];
        }
        this.flats = tmp;
    }
    /**Удаление квартиры с этажа по его номеру
     * с востановление последовательности нумерации*/
    public void deleteFlatOnFloor(int numberFlat) throws SpaceIndexOutOfBoundsException {
        if (numberFlat < 0 || numberFlat >= this.getNumberFlats()) {
            throw new SpaceIndexOutOfBoundsException("Incorrect numberFlat: " + numberFlat);
        }
        int numberFlats = this.flats.length - 1;
        Flat[] tmp = new Flat[numberFlats];
        int i = 0;
        for (; i < numberFlat; ++i) {
            tmp[i] = this.flats[i];
        }
        for (int k = i + 1; i < numberFlats; ++i, ++k) {
            tmp[i] = this.flats[k];
        }
        this.flats = tmp;
    }
    /**Гетер самой большой квартиры на этаже*/
    public Flat getBestSpace() {
        double maxArea = 0;
        Flat bestSpace = new Flat();
        for (Flat flat : flats) {
            if (maxArea < flat.getArea()) {
                maxArea = flat.getArea();
                bestSpace = flat;
            }
        }
        return bestSpace;
    }
    /**Отображение содержимого поля класса*/
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int length = this.flats.length;
        for (int i = 0; i < length; ++i) {
            str.append("№").append(i).append(flats[i].toString());
        }
        str.append('\n');
        return str.toString();
    }
}