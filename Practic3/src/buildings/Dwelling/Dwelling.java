package buildings.Dwelling;

import buildings.Exceptions.FloorIndexOutOfBoundsException;
import buildings.Exceptions.SpaceIndexOutOfBoundsException;

public class Dwelling {
    private DwellingFloor[] floors;

    /**Конструктор по количеству этажей и массиву квартир на этажах*/
    public Dwelling(int numberFloors, int[] arrayNumberFlats) throws SpaceIndexOutOfBoundsException{

        if (numberFloors <= 0 || numberFloors != arrayNumberFlats.length) {
            throw new SpaceIndexOutOfBoundsException("Incorrect number flats");
        }
        this.floors = new DwellingFloor[numberFloors];
        for (int i = 0; i < numberFloors; ++i) {
            this.floors[i] = new DwellingFloor(arrayNumberFlats[i]);
        }
    }
    /**Конструктор по массиву этажей дома*/
    public Dwelling(DwellingFloor[] df) {
        int length = df.length;
        this.floors = new DwellingFloor[length];
        for (int i = 0; i < length; ++i) {
            this.floors[i] = new DwellingFloor(df[i].getSpaces());
        }
    }
    /**Гетер общего количества этажей дома*/
    public int getSumFloors() { return this.floors.length; }
    /**Гетер общего количества квартир дома*/
    public int getSumFlats() {
        int sum = 0;
        for (DwellingFloor floor : floors) {
            sum += floor.getNumberSpaces();
        }
        return sum;
    }
    /**Гетер общей площади квартир дома*/
    public double getSumAreaFlatsInDwelling() {
        double sum = 0;
        for (DwellingFloor floor : floors) {
            sum += floor.getSumArea();
        }
        return sum;
    }
    /**Гетер общего количества комнат дома*/
    public int getSumRoomsInDwelling() {
        int sum = 0;
        for (DwellingFloor floor : floors) {
            sum += floor.getSumRooms();
        }
        return sum;
    }
    /**Гетер массива этажей дома*/
    public DwellingFloor[] getFloors() { return this.floors; }
    /**Гетер объекта этажа по его номеру*/
    public DwellingFloor getDwellingFloor(int numberFloor) throws FloorIndexOutOfBoundsException {
        if (numberFloor < 0 || numberFloor >= this.floors.length) {
            throw new FloorIndexOutOfBoundsException("Incorrect numberFloor: " + numberFloor);
        }
        return this.floors[numberFloor];
    }
    /**Сетер этажа по его номеру и ссылке на этаж*/
    public void setDwellingFloor(int numberFloor,
                                          DwellingFloor newFloor)  throws FloorIndexOutOfBoundsException {
        if (numberFloor < 0 || numberFloor >= this.floors.length) {
            throw new FloorIndexOutOfBoundsException("Incorrect numberFloor: " + numberFloor);
        }
        this.floors[numberFloor] = new DwellingFloor(newFloor.getSpaces());
    }
    /**Поиск этажа, на котором находится квартира по нужному номеру*/
    private int[] searchFloorAndFlat(int numberFlat) throws SpaceIndexOutOfBoundsException {
        if (numberFlat < 0 || numberFlat >= this.getSumFlats()) {
            throw new SpaceIndexOutOfBoundsException("Incorrect numberFlat: " + numberFlat);
        }
        int i = 0, numberFlats;
        for (; numberFlat >= this.floors[i].getNumberSpaces(); ++i) {
            numberFlats = this.floors[i].getNumberSpaces();
            numberFlat -= numberFlats;
        }
        return new int[] {i, numberFlat};
    }
    /**Гетер объекта квартиры по его номеру в доме*/
    public Flat getFlatInDwelling(int numberFlat) throws SpaceIndexOutOfBoundsException {
        int[] floorAndFlat = this.searchFloorAndFlat(numberFlat);
        return this.floors[floorAndFlat[0]].getSpaceOnFloor(floorAndFlat[1]);
    }
    /**Сетер картиры по его номеру и ссылку на новую квартиру*/
    public void setFlatInDwelling(int numberFlat, Flat newFlat) throws SpaceIndexOutOfBoundsException {
        int[] floorAndFlat = this.searchFloorAndFlat(numberFlat);
        this.floors[floorAndFlat[0]].setSpaceOnFloor(floorAndFlat[1], newFlat);
    }
    /**Добавление квартиры по его будущему номеру
     *  и ссылке на новую квартиру
     *  (количество этажей в доме при этом не увеличивается)*/
    public void addFlatInDwelling(int numberFlat, Flat newFlat) throws SpaceIndexOutOfBoundsException {
        if (numberFlat < 0 || numberFlat > this.getSumFlats()) {
            throw new SpaceIndexOutOfBoundsException("Incorrect numberFlat: " + numberFlat);
        }
        if (numberFlat == this.getSumFlats()) {
            DwellingFloor tmp = this.floors[this.floors.length - 1];
            tmp.addSpaceOnFloor(tmp.getNumberSpaces(), newFlat);
        }
        else {
            int i = 0, numberFlats;
            for (; numberFlat >= this.floors[i].getNumberSpaces(); ++i) {
                numberFlats = this.floors[i].getNumberSpaces();
                numberFlat -= numberFlats;
            }
            this.floors[i].addSpaceOnFloor(numberFlat, newFlat);
        }
    }
    /**Удаление картиры по его номеру*/
    public void deleteFlatInDwelling(int numberFlat) throws SpaceIndexOutOfBoundsException {
        int[] floorAndFlat = this.searchFloorAndFlat(numberFlat);
        this.floors[floorAndFlat[0]].deleteSpaceOnFloor(floorAndFlat[1]);
    }
    /**Гетер самой большой квартиры дома*/
    public Flat getBestSpace() {
        double maxArea = 0;
        Flat bestSpace = new Flat();
        for (DwellingFloor floor : floors) {
            Flat tmp = floor.getBestSpace();
            if (maxArea < tmp.getArea()) {
                maxArea = tmp.getArea();
                bestSpace = tmp;
            }
        }
        return bestSpace;
    }
    /**Гетер отсортированного по убыванию площадей массива квартир*/
    public Flat[] getSortFlatDown() {
        Flat[] allFlats = new Flat[this.getSumFlats()];
        int sumFloors = this.getSumFloors();
        int num, k = 0;
        for (int i = 0; i < sumFloors; ++i) {
            int sumFlatsOnFloor = this.floors[i].getNumberSpaces();
            num = k;
            for (k = 0; k < sumFlatsOnFloor; ++k) {
                Flat flat = this.floors[i].getSpaceOnFloor(k);
                int p = num + k;
                for (; p > 0; --p) {
                    if (flat.getArea() > allFlats[p-1].getArea()) {
                        allFlats[p] = allFlats[p-1];
                    }
                    else break;
                }
                allFlats[p] = flat;
            }
        }
        return allFlats;
    }
    /**Отображение содержимого поля класса*/
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Dwelling:\n");
        int length = this.floors.length;
        for (int i = length - 1; i >= 0; --i) {
            str.append("Floor№" + i + ": " + floors[i].toString());
        }
        return str.toString();
    }
}
