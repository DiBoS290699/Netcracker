import buildings.*;

public class Test {
    public static void main(String[] args) {
        Flat f = new Flat();
        System.out.println(f);
        Flat f1 = new Flat(22.5, 3);
        System.out.println("Area = " + f1.getArea() + " " + "Rooms = " + f1.getRooms());
        System.out.println("ToString Flat: " + f1);
        Flat f2 = new Flat(), f3 = new Flat(15.1);
        Flat[] fmas = {f1, f2, f3};
        DwellingFloor dw1 = new DwellingFloor( fmas );
        DwellingFloor dw2 = new DwellingFloor(3);
        System.out.print("ToString dw1: " + dw1);
        System.out.print("ToString dw2: " + dw2);
        dw2.setFlatOnFloor(0, new Flat(31.4, 3));
        dw2.setFlatOnFloor(1, new Flat(25.4, 1));
        dw2.setFlatOnFloor(2, new Flat(49,3));
        System.out.print("ToString dw2: " + dw2);
        DwellingFloor[] dwMas = {dw1, dw2};
        Dwelling d1 = new Dwelling(dwMas);
        System.out.print("ToString Dwelling:\n" + d1);
        System.out.println("getSumAreaFlats dw1: " + dw1.getSumAreaFlats() +
                " getSumAreaFlats dw2: " + dw2.getSumAreaFlats());
        System.out.println("getSumRoomsFlats dw1: " + dw1.getSumRoomsFlats() +
                " getSumRoomsFlats dw2: " + dw2.getSumRoomsFlats());
        dw1.addFlatOnFloor(2, new Flat(61,4));
        System.out.print(dw1);
        dw2.deleteFlatOnFloor(1);
        System.out.print(dw2);
        System.out.println("getBestSpace dw1: " + dw1.getBestSpace() +
                " getBestSpace dw2: " + dw2.getBestSpace());
        System.out.print(d1);
        System.out.println("getSumAreaFlatsInDwelling d1: " + d1.getSumAreaFlatsInDwelling());
        System.out.println("getSumRoomsInDwelling d1: " + d1.getSumRoomsInDwelling());
        d1.setDwellingFloor(1, dw2);
        System.out.print(d1);
        d1.addFlatInDwelling(5, new Flat(45.2, 3));
        System.out.print(d1);
    }
}
