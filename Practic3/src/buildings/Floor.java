package buildings;

import buildings.Exceptions.SpaceIndexOutOfBoundsException;

public interface Floor {
    int getNumberSpaces();
    double getSumArea();
    int getSumRooms();
    Space[] getSpaces();
    Space getSpaceOnFloor(int index) throws SpaceIndexOutOfBoundsException;
    void setSpaceOnFloor(int numberSpace, Space newSpace) throws SpaceIndexOutOfBoundsException
}
