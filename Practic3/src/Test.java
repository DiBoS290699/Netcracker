import buildings.OfficeBuilding.*;

public class Test {
    public static void main(String[] args) {
        Office f = new Office();
        System.out.println(f);
        Office f1 = new Office(22.5, 3);
        System.out.println("Area = " + f1.getArea() + " " + "Rooms = " + f1.getRooms());
        System.out.println("ToString Office: " + f1);
        Office f2 = new Office(), f3 = new Office(15.1);
        Office[] fmas = {f1, f2, f3};
        OfficeFloor dw1 = new OfficeFloor( fmas );
        OfficeFloor dw2 = new OfficeFloor(3);
        System.out.print("ToString dw1: " + dw1);
        System.out.print("ToString dw2: " + dw2);
        dw2.setOfficeOnFloor(0, new Office(31.4, 3));
        dw2.setOfficeOnFloor(1, new Office(25.4, 1));
        dw2.setOfficeOnFloor(2, new Office(49,3));
        System.out.print("ToString dw2: " + dw2);
        OfficeFloor[] dwMas = {dw1, dw2};
        OfficeBuilding d1 = new OfficeBuilding(dwMas);
        System.out.print("ToString Dwelling:\n" + d1);
        System.out.println("getSumAreaOffices dw1: " + dw1.getSumAreaOffices() +
                " getSumAreaOffices dw2: " + dw2.getSumAreaOffices());
        System.out.println("getSumRoomsOffices dw1: " + dw1.getSumRoomsOffices() +
                " getSumRoomsOffices dw2: " + dw2.getSumRoomsOffices());
        dw1.addOfficeOnFloor(2, new Office(61,4));
        System.out.print(dw1);
        dw2.deleteOfficeOnFloor(1);
        System.out.print(dw2);
        System.out.println("getBestSpace dw1: " + dw1.getBestSpace() +
                " getBestSpace dw2: " + dw2.getBestSpace());
        System.out.print(d1);
        System.out.println("getSumAreaOfficesInDwelling d1: " + d1.getSumAreaOfficesInBuilding());
        System.out.println("getSumRoomsInDwelling d1: " + d1.getSumRoomsOfficesInBuilding());
        d1.setOfficeFloor(1, dw2);
        System.out.print(d1);
        d1.addOfficeInDwelling(5, new Office(45.2, 3));
        System.out.print(d1);
        d1.deleteOfficeInDwelling(3);
        System.out.print(d1);
        System.out.println("Office №4: " + d1.getOfficeInDwelling(4));
        d1.setOfficeInDwelling(4, new Office(59.0, 3));
        System.out.print(d1);
        Office[] sortOffices = d1.getSortOfficeDown();
        System.out.print("Sort dwelling: ");
        for(Office Office : sortOffices) {
            System.out.print(Office + " ");
        }
    }
}
