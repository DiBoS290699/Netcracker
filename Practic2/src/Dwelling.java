public class Dwelling {
    private DwellingFloor[] dwellingFloors;

    public Dwelling(int numberFloors, int[] arrayNumberFlats) {
        this.dwellingFloors = new DwellingFloor[numberFloors];
        if (numberFloors != arrayNumberFlats.length) {
            throw new IndexOutOfBoundsException("Incorrect array of number flats");
        }

    }

}
