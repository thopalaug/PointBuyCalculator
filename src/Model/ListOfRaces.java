package Model;

import Model.Races.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListOfRaces {

    private ObservableList<Race> listOfRaces = FXCollections.observableArrayList();

    public ListOfRaces(){
        generateRaces();
    }

    public ObservableList<Race> getListOfRaces() {
        return listOfRaces;
    }

    public void generateRaces(){
        Race human = new Human();
        listOfRaces.add(human);

        Race highElf = new HighElf();
        listOfRaces.add(highElf);

        Race woodElf = new WoodElf();
        listOfRaces.add(woodElf);

        Race mountainDwarf = new MountainDwarf();
        listOfRaces.add(mountainDwarf);

        Race hillDwarf = new HillDwarf();
        listOfRaces.add(hillDwarf);

        Race halfOrc = new HalfOrc();
        listOfRaces.add(halfOrc);

        Race tiefling = new Tiefling();
        listOfRaces.add(tiefling);
    }
}
