package buildings.OfficeBuilding;

import buildings.Exceptions.SpaceIndexOutOfBoundsException;
import buildings.Floor;
import buildings.Space;

public class OfficeFloor implements Floor {
    private FunctionNode head = new FunctionNode();     //Всегда пустая голова, ссылающаяся на нулевой узел
    private int numberOffices = 0;                      //Количество офисов на этаже

    /**Конструктор инициалазации через количество офисов*/
    public OfficeFloor(int numberOf) throws SpaceIndexOutOfBoundsException {
        if (numberOf < 1) {
            throw new SpaceIndexOutOfBoundsException("Incorrect number of offices: " + numberOf);
        }
        for (int i = 0; i < numberOf; ++i) {
            this.addNodeToTail().of = new Office();
        }
    }
    /**Конструктор инициализации через массив квартир*/
    public OfficeFloor(Office[] ofMas) {
        int length = ofMas.length;
        for (int i = 0; i < length; ++i) {
            this.addNodeToTail().of = new Office(ofMas[i]);
        }
    }
    /**Гетер количества офисов*/
    public int getNumberSpaces() { return this.numberOffices; }
    /**Гетер общей площади офисов*/
    public double getSumArea() {
        double sumArea = 0;
        FunctionNode temp = new FunctionNode(head.next);
        for (int i = 0; i < numberOffices; ++i) {
            temp = temp.next;
            sumArea += temp.of.getArea();
        }
        return sumArea;
    }
    /**Гетер общего количества комнат*/
    public int getSumRooms() {
        int sumRooms = 0;
        FunctionNode temp = new FunctionNode(head.next);
        for (int i = 0; i < numberOffices; ++i) {
            temp = temp.next;
            sumRooms += temp.of.getRooms();
        }
        return sumRooms;
    }
    /**Гетер массива офисов*/
    public Office[] getSpaces() {
        Office[] ofMas = new Office[numberOffices];
        FunctionNode temp = new FunctionNode(head.next);
        for (int i = 0; i < numberOffices; ++i) {
            temp = temp.next;
            ofMas[i] = temp.of;
        }
        return ofMas;
    }
    /**Гетер офиса по его номеру*/
    public Office getSpaceOnFloor(int index) throws SpaceIndexOutOfBoundsException{
        return this.getNodeByIndex(index).of;
    }
    /**Сетер офиса по его номеру*/
    public void setSpaceOnFloor(int index, Space newOf) throws SpaceIndexOutOfBoundsException {
        this.getNodeByIndex(index).of = new Office(newOf);
    }
    /**Добавление офиса на этаж по номеру и объекту офиса*/
    public void addSpaceOnFloor(int index, Office newOf) throws SpaceIndexOutOfBoundsException{
        this.addNodeByIndex(index).of = new Office(newOf);
    }
    /**Удаление офиса с этажа по его номеру*/
    public void deleteSpaceOnFloor(int index) throws SpaceIndexOutOfBoundsException{
        this.deleteNodeByIndex(index);
    }
    /**Гетер самой большого офиса на этаже*/
    public Office getBestSpace() {
        double maxArea = 0;
        Office bestSpace = new Office();
        FunctionNode temp = new FunctionNode(head.next);
        for (int i = 0; i < numberOffices; ++i) {
            temp = temp.next;
            if (maxArea < temp.of.getArea()) {
                maxArea = temp.of.getArea();
                bestSpace = temp.of;
            }
        }
        return bestSpace;
    }
    /**Приватный метод получения узла по его номеру*/
    private FunctionNode getNodeByIndex(int index) throws SpaceIndexOutOfBoundsException{
        if (index < 0 || index >= numberOffices) {
            throw new SpaceIndexOutOfBoundsException("Incorrect index: " + index);
        }
        FunctionNode temp = new FunctionNode(head.next);
        for (int i = 0; i <= index; ++i) {
            temp = temp.next;
        }
        return temp;
    }

    /**Сначала сохраняется последний объект в списке,
    * после чего следующий элемент от него становится пустым (ранее последний элемент ссылался на первый),
    * теперь добавляем связи для нового объекта. Он должен ссылаться на первый объект в списке.
    * Возвращаем ссылку на него.*/
    private FunctionNode addNodeToTail() {
        if (numberOffices == 0) {
            FunctionNode newNode = new FunctionNode();
            newNode.next = newNode;
            head.next = newNode;
            numberOffices = 1;
            return newNode;
        }

        else if (numberOffices == 1) {
            FunctionNode newNode = new FunctionNode(head.next);
            head.next.next = newNode;
            numberOffices = 2;
            return newNode;
        }

        else {
            FunctionNode temp = new FunctionNode(head.next);
            for (int i = 0; i < numberOffices; ++i) {
                temp = temp.next;
            }
            temp.next = new FunctionNode(head.next);
            ++numberOffices;
            temp = temp.next;
            return temp;
        }
    }
    /**Приватный метод добавления узла в список по номеру.*/
    private FunctionNode addNodeByIndex(int index) throws SpaceIndexOutOfBoundsException {
        if (this.numberOffices == 0 && index == 0 || this.numberOffices == 1 && index == 1 || index == this.numberOffices) {
            return addNodeToTail();
        }
        FunctionNode prevNode = getNodeByIndex(index - 1);
        FunctionNode temp = new FunctionNode(prevNode.next);
        prevNode.next = temp;
        ++this.numberOffices;
        return temp;
    }
    /**Приватный метод удаления узла из списка по его номеру.*/
    private FunctionNode deleteNodeByIndex(int index) throws SpaceIndexOutOfBoundsException {
        if (index == 0) {
            FunctionNode lastNode = new FunctionNode(head.next);
            for (int i = 0; i < numberOffices; ++i) {
                lastNode = lastNode.next;
            }
            FunctionNode temp = head.next;
            lastNode.next = head.next.next;
            head.next = head.next.next;
            --numberOffices;
            return temp;
        }
        FunctionNode prevNode = getNodeByIndex(index - 1);
        FunctionNode temp = prevNode.next;
        prevNode.next = prevNode.next.next;
        --this.numberOffices;
        return temp;
    }
    /**Класс узла в списке*/
    private static class FunctionNode {
        private Office of;
        private FunctionNode next;

        public FunctionNode() { }

        public FunctionNode(FunctionNode next) {
            this.of = new Office();
            this.next = next;
        }

        public FunctionNode(Office of, FunctionNode next) {
            this.of = of;
            this.next = next;
        }
    }
    /**Отображение содержимого поля класса*/
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        FunctionNode office = new FunctionNode(head.next);
        for (int i = 0; i < this.numberOffices; ++i) {
            office = office.next;
            str.append("№").append(i).append(office.of.toString());
        }
        str.append('\n');
        return str.toString();
    }
}
