package buildings.OfficeBuilding;

import buildings.Exceptions.FloorIndexOutOfBoundsException;
import buildings.Exceptions.SpaceIndexOutOfBoundsException;

public class OfficeBuilding {
    private FunctionNode head = new FunctionNode();
    private int numberFloors = 0;

    public OfficeBuilding(int numberFloors, int[] arrayNumberOffices) throws SpaceIndexOutOfBoundsException {
        if (numberFloors <= 0 || numberFloors != arrayNumberOffices.length) {
            throw new FloorIndexOutOfBoundsException("Incorrect number flats");
        }
        for (int i = 0; i < numberFloors; ++i) {
            this.addNodeToTail().off = new OfficeFloor(arrayNumberOffices[i]);
        }
    }

    public OfficeBuilding(OfficeFloor[] offMas) {
        int length = offMas.length;
        for (int i = 0; i < length; ++i) {
            this.addNodeToTail().off = new OfficeFloor(offMas[i].getOffices());
        }
    }
    /**Гетер общего количества этажей дома*/
    public int getSumFloors() { return this.numberFloors; }
    /**Гетер общего количества офисов дома*/
    public int getSumOffices() {
        int sum = 0;
        FunctionNode temp = new FunctionNode(head.next, head.prev);
        for (int i = 0; i < numberFloors; ++i) {
            temp = temp.next;
            sum += temp.off.getNumberOffices();
        }
        return sum;
    }

    public double getSumAreaOfficesInBuilding() {
        double sum = 0;
        FunctionNode temp = new FunctionNode(head.next, head.prev);
        for (int i = 0; i < numberFloors; ++i) {
            temp = temp.next;
            sum += temp.off.getSumAreaOffices();
        }
        return sum;
    }

    public double getSumRoomsOfficesInBuilding() {
        double sum = 0;
        FunctionNode temp = new FunctionNode(head.next, head.prev);
        for (int i = 0; i < numberFloors; ++i) {
            temp = temp.next;
            sum += temp.off.getSumRoomsOffices();
        }
        return sum;
    }

    public OfficeFloor[] getFloors() {
        OfficeFloor[] offMas = new OfficeFloor[numberFloors];
        FunctionNode temp = new FunctionNode(head.next, head.prev);
        for (int i = 0; i < numberFloors; ++i) {
            temp = temp.next;
            offMas[i] = temp.off;
        }
        return offMas;
    }



    public OfficeFloor getOfficeFloor(int index) {
        return this.getNodeByIndex(index).off;
    }

    public void setOfficeFloor(int index, OfficeFloor newOff) {
        this.getNodeByIndex(index).off = new OfficeFloor(newOff.getOffices());
    }
    /**Поиск этажа, на котором находится офис по нужному номеру*/
    private int[] searchFloorAndOffice(int numberOffice) throws SpaceIndexOutOfBoundsException {
        if (numberOffice < 0 || numberOffice >= this.getSumOffices()) {
            throw new SpaceIndexOutOfBoundsException("Incorrect numberFlat: " + numberOffice);
        }
        FunctionNode floor = new FunctionNode(head.next.next, head.prev);
        int i = 0, numberOffices = floor.off.getNumberOffices();
        for (; numberOffice >= numberOffices; ++i) {
            numberOffice -= numberOffices;
            floor = floor.next;
            numberOffices = floor.off.getNumberOffices();
        }
        return new int[] {i, numberOffice};
    }
    /**Гетер объекта офиса по его номеру в доме*/
    public Office getOfficeInBuilding(int numberOffice) throws SpaceIndexOutOfBoundsException {
        int[] floorAndOffice = this.searchFloorAndOffice(numberOffice);
        return this.getOfficeFloor(floorAndOffice[0]).getOfficeOnFloor(floorAndOffice[1]);
    }
    /**Сетер картиры по его номеру и ссылку на новую квартиру*/
    public void setOfficeInBuilding(int numberFlat, Office newOffice) throws SpaceIndexOutOfBoundsException {
        int[] floorAndOffice = this.searchFloorAndOffice(numberFlat);
        this.getOfficeFloor(floorAndOffice[0]).setOfficeOnFloor(floorAndOffice[1], newOffice);
    }
    /**Добавление квартиры по его будущему номеру
     *  и ссылке на новую квартиру
     *  (количество этажей в доме при этом не увеличивается)*/
    public void addOfficeInBuilding(int numberOffice, Office newOffice) throws SpaceIndexOutOfBoundsException {
        int sumOffices = this.getSumOffices();
        if (numberOffice < 0 || numberOffice > sumOffices) {
            throw new SpaceIndexOutOfBoundsException("Incorrect numberFlat: " + numberOffice);
        }
        if (numberOffice == sumOffices) {
            OfficeFloor tmp = this.head.prev.off;
            tmp.addOfficeOnFloor(tmp.getNumberOffices(), newOffice);
        }
        else {
            FunctionNode floor = new FunctionNode(head.next.next, head.prev);
            int numberOffices = floor.off.getNumberOffices();
            while (numberOffice >= numberOffices) {
                numberOffice -= numberOffices;
                floor = floor.next;
                numberOffices = floor.off.getNumberOffices();
            }
            floor.off.addOfficeOnFloor(numberOffice, newOffice);
        }
    }
    /**Удаление офиса по его номеру*/
    public void deleteOfficeInBuilding(int numberOffice) throws SpaceIndexOutOfBoundsException {
        int[] floorAndOffice = this.searchFloorAndOffice(numberOffice);
        this.getOfficeFloor(floorAndOffice[0]).deleteOfficeOnFloor(floorAndOffice[1]);
    }
    /**Гетер самого большого офиса на этаже*/
    public Office getBestSpace() {
        double maxArea = 0;
        Office bestSpace = new Office();
        FunctionNode temp = new FunctionNode(head.next, head.prev);
        for (int i = 0; i < numberFloors; ++i) {
            temp = temp.next;
            Office tempOffice = temp.off.getBestSpace();
            if (maxArea < tempOffice.getArea()) {
                maxArea = tempOffice.getArea();
                bestSpace = tempOffice;
            }
        }
        return bestSpace;
    }
    /**Гетер отсортированного по убыванию площадей массива офисов*/
    public Office[] getSortFlatDown() {
        Office[] allOffices = new Office[this.getSumOffices()];
        int sumFloors = this.getSumFloors();
        int num, k = 0;
        FunctionNode floor = new FunctionNode(head.next.next, head.prev);
        for (int i = 0; i < sumFloors; ++i) {
            int sumOfficesOnFloor = floor.off.getNumberOffices();
            num = k;
            Office[] offices = floor.off.getOffices();
            for (k = 0; k < sumOfficesOnFloor; ++k) {
                Office office = offices[k];
                int p = num + k;
                for (; p > 0; --p) {
                    if (office.getArea() > allOffices[p-1].getArea()) {
                        allOffices[p] = allOffices[p-1];
                    }
                    else break;
                }
                allOffices[p] = office;
            }
            floor = floor.next;
        }
        return allOffices;
    }

    private FunctionNode getNodeByIndex(int index) throws SpaceIndexOutOfBoundsException {
        if (index < 0 || index >= this.numberFloors) {
            throw new SpaceIndexOutOfBoundsException("Error!");
        }

        if (index > this.numberFloors / 2) {
            FunctionNode temp = this.head.prev;

            int length = this.numberFloors - index;
            for (int i = 0; i < length; ++i) {
                temp = temp.prev;
            }
            return temp;

        } else {
            FunctionNode temp = this.head.next;

            for (int i = 0; i < index; ++i) {
                temp = temp.next;
            }

            return temp;
        }
    }
    /*Сначала сохраняется последний объект в списке,
    после чего следующий элемент от него становится пустым (ранее последний элемент ссылался на первый),
    теперь добавляем связи для нового объекта. Он должен ссылаться на сохранённый старый объект и на первый
    объект в списке. Теперь нужно чтобы голова и первый ссылались на новый элемент. Возвращаем ссылку на него.*/
    private FunctionNode addNodeToTail() {
        if (numberFloors == 0) {
            FunctionNode temp = new FunctionNode();
            temp.next = temp;
            temp.prev = temp;
            head.next = temp;
            head.prev = temp;
            numberFloors = 1;
            return temp;
        }

        else if (numberFloors == 1) {
            FunctionNode temp = new FunctionNode(head.next, head.next);
            head.prev = temp;
            head.next.next = temp;
            head.next.prev = temp;
            numberFloors = 2;
            return temp;
        }

        else {
            FunctionNode temp = new FunctionNode(head.prev, head.next);
            head.next.prev = temp;
            temp.prev.next = temp;
            head.prev = temp;
            ++numberFloors;
            return temp;
        }
    }

    private FunctionNode addNodeByIndex(int index) throws SpaceIndexOutOfBoundsException {
        if (this.numberFloors == 0 && index == 0 || this.numberFloors == 1 && index == 1 || index == this.numberFloors) {
            return addNodeToTail();
        }

        FunctionNode nextNode = getNodeByIndex(index);
        FunctionNode temp = new FunctionNode(nextNode.prev, nextNode);
        nextNode.prev.next = temp;
        nextNode.prev = temp;
        ++this.numberFloors;
        return temp;
    }

    private FunctionNode deleteNodeByIndex(int index) throws SpaceIndexOutOfBoundsException {
        FunctionNode temp = getNodeByIndex(index);
        temp.next.prev = temp.prev;
        temp.prev.next = temp.next;
        --this.numberFloors;
        return temp;
    }

    private static class FunctionNode {
        private OfficeFloor off;
        private FunctionNode next;
        private FunctionNode prev;

        public FunctionNode() {
        }

        public FunctionNode(FunctionNode prev, FunctionNode next) {
            this.prev = prev;
            this.next = next;
        }

        public FunctionNode(OfficeFloor off, FunctionNode prev, FunctionNode next) {
            this.off = off;
            this.prev = prev;
            this.next = next;
        }
    }
    /**Отображение содержимого поля класса*/
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Building:\n");
        FunctionNode floor = new FunctionNode(head.next, head.prev);
        for (int i = numberFloors - 1; i >= 0; --i) {
            floor = floor.prev;
            str.append("Floor№" + i + ": " + floor.off.toString());
        }
        return str.toString();
    }
}
