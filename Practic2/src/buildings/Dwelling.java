package buildings;

public class Dwelling {
    private DwellingFloor[] dwellingFloors;

    /**Конструктор по количеству этажей и массиву квартир на этажах*/
    public Dwelling(int numberFloors, int[] arrayNumberFlats) {

        if (numberFloors == 0 || numberFloors != arrayNumberFlats.length) {
            throw new IndexOutOfBoundsException("Incorrect number flats");
        }
        this.dwellingFloors = new DwellingFloor[numberFloors];
        for (int i = 0; i < numberFloors; ++i) {
            this.dwellingFloors[i] = new DwellingFloor(arrayNumberFlats[i]);
        }
    }
    /**Конструктор по массиву этажей дома*/
    public Dwelling(DwellingFloor[] df) {
        int length = df.length;
        this.dwellingFloors = new DwellingFloor[length];
        for (int i = 0; i < length; ++i) {
            this.dwellingFloors[i] = new DwellingFloor(df[i].getFlats());
        }
    }
    /**Гетер общего количества этажей дома*/
    public int getSumFloors() { return this.dwellingFloors.length; }
    /**Гетер общего количества квартир дома*/
    public int getSumFlats() {
        int sum = 0;
        for (DwellingFloor floor : dwellingFloors) {
            sum += floor.getNumberFlats();
        }
        return sum;
    }
    /**Гетер общей площади квартир дома*/
    public double getSumAreaFlatsInDwelling() {
        double sum = 0;
        for (DwellingFloor floor : dwellingFloors) {
            sum += floor.getSumAreaFlats();
        }
        return sum;
    }
    /**Гетер общего количества комнат дома*/
    public int getSumRoomsInDwelling() {
        int sum = 0;
        for (DwellingFloor floor : dwellingFloors) {
            sum += floor.getSumRoomsFlats();
        }
        return sum;
    }
    /**Гетер массива этажей дома*/
    public DwellingFloor[] getDwellingFloors() { return this.dwellingFloors; }
    /**Гетер объекта этажа по его номеру*/
    public DwellingFloor getDwellingFloor(int numberFloor) {
        if (numberFloor < 0 || numberFloor > this.dwellingFloors.length) {
            throw new IndexOutOfBoundsException("Incorrect numberFloor: " + numberFloor);
        }
        return this.dwellingFloors[numberFloor];
    }
    /**Сетер этажа по его номеру и ссылке на этаж*/
    public void setDwellingFloor(int numberFloor,
                                          DwellingFloor newFloor) {
        if (numberFloor < 0 || numberFloor > this.dwellingFloors.length) {
            throw new IndexOutOfBoundsException("Incorrect numberFloor: " + numberFloor);
        }
        this.dwellingFloors[numberFloor] = new DwellingFloor(newFloor.getFlats());
    }
    /**Поиск этажа, на котором находится квартира по нужному номеру*/
    private int[] searchFloorAndFlat(int numberFlat) throws IndexOutOfBoundsException {
        if (numberFlat < 0 || numberFlat >= this.getSumFlats()) {
            throw new IndexOutOfBoundsException("Incorrect numberFlat: " + numberFlat);
        }
        int i = 0, numberFlats;
        for (; numberFlat >= this.dwellingFloors[i].getNumberFlats(); ++i) {
            numberFlats = this.dwellingFloors[i].getNumberFlats();
            numberFlat -= numberFlats;
        }
        int[] floorAndFlat = {i, numberFlat};
        return floorAndFlat;
    }
    /**Гетер объекта квартиры по его номеру в доме*/
    public Flat getFlatInDwelling(int numberFlat) {
        int[] floorAndFlat = this.searchFloorAndFlat(numberFlat);
        return this.dwellingFloors[floorAndFlat[0]].getFlatOnFloor(floorAndFlat[1]);
    }
    /**Сетер картиры по его номеру и ссылку на новую квартиру*/
    public void setFlatInDwelling(int numberFlat, Flat newFlat) {
        int[] floorAndFlat = this.searchFloorAndFlat(numberFlat);
        this.dwellingFloors[floorAndFlat[0]].setFlatOnFloor(floorAndFlat[1], newFlat);
    }
    /**Добавление квартиры по его будущему номеру
     *  и ссылке на новую квартиру
     *  (количество этажей в доме при этом не увеличивается)*/
    public void addFlatInDwelling(int numberFlat, Flat newFlat) {
        if (numberFlat < 0 || numberFlat > this.getSumFlats()) {
            throw new IndexOutOfBoundsException("Incorrect numberFlat");
        }
        if (numberFlat == this.getSumFlats()) {
            DwellingFloor tmp = this.dwellingFloors[this.dwellingFloors.length - 1];
            tmp.addFlatOnFloor(tmp.getNumberFlats(), newFlat);
        }
        else {
            int i = 0, numberFlats;
            for (; numberFlat >= this.dwellingFloors[i].getNumberFlats(); ++i) {
                numberFlats = this.dwellingFloors[i].getNumberFlats();
                numberFlat -= numberFlats;
            }
            this.dwellingFloors[i].addFlatOnFloor(numberFlat, newFlat);
        }
    }
    /**Удаление картиры по его номеру*/
    public void deleteFlatInDwelling(int numberFlat) {
        int[] floorAndFlat = this.searchFloorAndFlat(numberFlat);
        this.dwellingFloors[floorAndFlat[0]].deleteFlatOnFloor(floorAndFlat[1]);
    }
    /**Гетер самой большой квартиры дома*/
    public Flat getBestSpace() {
        double maxArea = 0;
        Flat bestSpace = new Flat();
        for (DwellingFloor floor : dwellingFloors) {
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
        int num = 0;
        for (int i = 0; i < sumFloors; ++i) {
            int sumFlatsOnFloor = this.dwellingFloors[i].getNumberFlats();
            for (int k = 0; k < sumFlatsOnFloor; ++k, ++num) {
                Flat flat = this.dwellingFloors[i].getFlatOnFloor(k);
                int p = k;
                for (; p > 0; --p) {
                    if (flat.getArea() > this.dwellingFloors[i].getFlatOnFloor(p-1).getArea()) {
                        allFlats[p] = this.dwellingFloors[i].getFlatOnFloor(p-1);
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
        int length = this.dwellingFloors.length;
        for (int i = length - 1; i >= 0; --i) {
            str.append("Floor№" + i + ": " + dwellingFloors[i].toString());
        }
        return str.toString();
    }
}
