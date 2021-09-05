package Controller;

import Model.ListOfRaces;
import Model.Races.Race;
import Model.ChangePointCost;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // Spinners for each attribute
    @FXML
    private Spinner<Integer> strengthSpinner = new Spinner<>();
    @FXML
    private Spinner<Integer> dexteritySpinner = new Spinner<>();
    @FXML
    private Spinner<Integer> constitutionSpinner = new Spinner<>();
    @FXML
    private Spinner<Integer> intelligenceSpinner = new Spinner<>();
    @FXML
    private Spinner<Integer> wisdomSpinner = new Spinner<>();
    @FXML
    private Spinner<Integer> charismaSpinner = new Spinner<>();

    final int initialValue = 8;

    // Labels for the total for each attribute
    @FXML
    private Label totalStrengthScore;
    @FXML
    private Label totalDexterityScore;
    @FXML
    private Label totalConstitutionScore;
    @FXML
    private Label totalIntelligenceScore;
    @FXML
    private Label totalWisdomScore;
    @FXML
    private Label totalCharismaScore;

    // Labels for each modifier for each attribute
    @FXML
    private Label strengthModifier;
    @FXML
    private Label dexterityModifier;
    @FXML
    private Label constitutionModifier;
    @FXML
    private Label intelligenceModifier;
    @FXML
    private Label wisdomModifier;
    @FXML
    private Label charismaModifier;

    // Labels for each racial bonus for each attribute
    @FXML
    private Label raceStrength;
    @FXML
    private Label raceDexterity;
    @FXML
    private Label raceConstitution;
    @FXML
    private Label raceIntelligence;
    @FXML
    private Label raceWisdom;
    @FXML
    private Label raceCharisma;

    // Labels for the point cost of each attribute
    @FXML
    private Label strengthPointCost;
    @FXML
    private Label dexterityPointCost;
    @FXML
    private Label constitutionPointCost;
    @FXML
    private Label intelligencePointCost;
    @FXML
    private Label wisdomPointCost;
    @FXML
    private Label charismaPointCost;
    @FXML
    private Label totalPoints;


    @FXML
    private ChoiceBox<Race> raceChoiceBox;

    private int strCost = 0;
    private int dexCost = 0;
    private int conCost = 0;
    private int intCost = 0;
    private int wisCost = 0;
    private int chaCost = 0;

    ChangePointCost changePointsCost = new ChangePointCost();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Creates an listOfRaces object, which in turn creates an observable list with all the races inn it.
        ListOfRaces listOfRaces = new ListOfRaces();

        // Adds the races to the choice box
        raceChoiceBox.setItems(listOfRaces.getListOfRaces());

        // Sets the first race as selected
        raceChoiceBox.getSelectionModel().select(0);

        setAttributeSpinners();
        calcAll();
        setPointCost();

        //Spinner listeners to listen for changes in the spinners used to control the different values of each attribute.
        strengthSpinner.valueProperty().addListener((observableValue, integer, t1) -> {
            calcStr();
            if(integer < t1){
                strCost = changePointsCost.incrementStrengthPointCost(strCost);
            }
            if(integer > t1){
                strCost = changePointsCost.decrementStrengthPointCost(strCost);
            }
            strengthPointCost.setText(Integer.toString(strCost));
            calculateTotalPointsCost();
        });
        dexteritySpinner.valueProperty().addListener((observableValue, integer, t1) -> {
            calcDex();
            if(integer < t1)
                dexCost = changePointsCost.incrementStrengthPointCost(dexCost);
            if(integer > t1){
                dexCost = changePointsCost.decrementStrengthPointCost(dexCost);
            }
            dexterityPointCost.setText(Integer.toString(dexCost));
            calculateTotalPointsCost();

        });
        constitutionSpinner.valueProperty().addListener((observableValue, integer, t1) -> {
            calcCon();
            if(integer < t1)
                conCost = changePointsCost.incrementStrengthPointCost(conCost);
            if(integer > t1)
                conCost = changePointsCost.decrementStrengthPointCost(conCost);
            constitutionPointCost.setText(Integer.toString(conCost));
            calculateTotalPointsCost();
        });
        intelligenceSpinner.valueProperty().addListener((observableValue, integer, t1) -> {
            calcInt();
            if(integer < t1)
                intCost = changePointsCost.incrementStrengthPointCost(intCost);
            if(integer > t1)
                intCost = changePointsCost.decrementStrengthPointCost(intCost);
            intelligencePointCost.setText(Integer.toString(intCost));
            calculateTotalPointsCost();
        });
        wisdomSpinner.valueProperty().addListener((observableValue, integer, t1) -> {
            calcWis();
            if(integer < t1)
                wisCost = changePointsCost.incrementStrengthPointCost(wisCost);
            if(integer > t1)
                wisCost = changePointsCost.decrementStrengthPointCost(wisCost);
            wisdomPointCost.setText(Integer.toString(wisCost));
            calculateTotalPointsCost();
        });
        charismaSpinner.valueProperty().addListener((observableValue, integer, t1) -> {
            calcCha();
            if(integer < t1)
                chaCost = changePointsCost.incrementStrengthPointCost(chaCost);
            if(integer > t1)
                chaCost = changePointsCost.decrementStrengthPointCost(chaCost);
            charismaPointCost.setText(Integer.toString(chaCost));
            calculateTotalPointsCost();
        });

        //Listener for the race choice box. Calls the calcAll() method to update the attributes
        raceChoiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, race, t1) -> calcAll());

    }

    // Sets the point cost for each attribute to 0
    private void setPointCost(){
        strengthPointCost.setText(Integer.toString(strCost = 0));
        dexterityPointCost.setText(Integer.toString(dexCost = 0));
        constitutionPointCost.setText(Integer.toString(conCost = 0));
        intelligencePointCost.setText(Integer.toString(intCost = 0));
        wisdomPointCost.setText(Integer.toString(wisCost = 0));
        charismaPointCost.setText(Integer.toString(chaCost = 0));
        calculateTotalPointsCost();
    }

    private void calculateTotalPointsCost(){
        int totalCost = strCost + dexCost + conCost + intCost + wisCost + chaCost;
        totalPoints.setText(totalCost + "/27");
    }

    //This series of "calc methods" calculates the total score and the modifier for each attribute, and updates the values.
    private void calcStr(){
        totalStrengthScore.setText(calculateTotalScore(strengthSpinner.getValue(),raceChoiceBox.getSelectionModel().getSelectedItem().getStrength()));
        strengthModifier.setText(calculateModifier(strengthSpinner.getValue(), raceChoiceBox.getSelectionModel().getSelectedItem().getStrength()));
        raceStrength.setText(Integer.toString(raceChoiceBox.getSelectionModel().getSelectedItem().getStrength()));
    }
    private void calcDex(){
        totalDexterityScore.setText(calculateTotalScore(dexteritySpinner.getValue(),raceChoiceBox.getSelectionModel().getSelectedItem().getDexterity()));
        dexterityModifier.setText(calculateModifier(dexteritySpinner.getValue(), raceChoiceBox.getSelectionModel().getSelectedItem().getDexterity()));
        raceDexterity.setText(Integer.toString(raceChoiceBox.getSelectionModel().getSelectedItem().getDexterity()));
    }
    private void calcCon(){
        totalConstitutionScore.setText(calculateTotalScore(constitutionSpinner.getValue(),raceChoiceBox.getSelectionModel().getSelectedItem().getConstitution()));
        constitutionModifier.setText(calculateModifier(constitutionSpinner.getValue(),raceChoiceBox.getSelectionModel().getSelectedItem().getConstitution()));
        raceConstitution.setText(Integer.toString(raceChoiceBox.getSelectionModel().getSelectedItem().getConstitution()));
    }
    private void calcInt(){
        totalIntelligenceScore.setText(calculateTotalScore(intelligenceSpinner.getValue(),raceChoiceBox.getSelectionModel().getSelectedItem().getIntelligence()));
        intelligenceModifier.setText(calculateModifier(intelligenceSpinner.getValue(),raceChoiceBox.getSelectionModel().getSelectedItem().getIntelligence()));
        raceIntelligence.setText(Integer.toString(raceChoiceBox.getSelectionModel().getSelectedItem().getIntelligence()));
    }
    private void calcWis(){
        totalWisdomScore.setText(calculateTotalScore(wisdomSpinner.getValue(),raceChoiceBox.getSelectionModel().getSelectedItem().getWisdom()));
        wisdomModifier.setText(calculateModifier(wisdomSpinner.getValue(),raceChoiceBox.getSelectionModel().getSelectedItem().getWisdom()));
        raceWisdom.setText(Integer.toString(raceChoiceBox.getSelectionModel().getSelectedItem().getWisdom()));
    }
    private void calcCha(){
        totalCharismaScore.setText(calculateTotalScore(charismaSpinner.getValue(),raceChoiceBox.getSelectionModel().getSelectedItem().getCharisma()));
        charismaModifier.setText(calculateModifier(charismaSpinner.getValue(),raceChoiceBox.getSelectionModel().getSelectedItem().getCharisma()));
        raceCharisma.setText(Integer.toString(raceChoiceBox.getSelectionModel().getSelectedItem().getCharisma()));
    }


    //This methods runs all the calc methods for the stats.
    private void calcAll(){
        calcStr();
        calcDex();
        calcCon();
        calcInt();
        calcWis();
        calcCha();
    }


    //Calculates the modifier value for each attribute. Adds a "+" to the returned string if the modifier is greater than 0
    private String calculateModifier(int stat, int score){
        String calculatedModifier;
        int modifier = (int) Math.floor(((double)score - 10 + stat) / 2);
        if(modifier > 0){
            calculatedModifier = "+" + modifier;
        }else{
            calculatedModifier = Integer.toString(modifier);
        }
        return calculatedModifier;
    }


    //Calculates the total score of an attribute.
    private String calculateTotalScore(int stat, int score){
        int value = stat + score;
        return Integer.toString(value);
    }


    // This button reset the ability score array
    @FXML
    private void resetAttributes(){
        setAttributeSpinners();
        setPointCost();
    }

    // This method defines spinnerValueFactories and binds them to the spinners
    private void setAttributeSpinners(){

        // Defines one SpinnerValueFactory for each spinner.
        SpinnerValueFactory<Integer> valueFactoryStrength =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 15, initialValue);

        SpinnerValueFactory<Integer> valueFactoryDexterity =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 15, initialValue);

        SpinnerValueFactory<Integer> valueFactoryConstitution =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 15, initialValue);

        SpinnerValueFactory<Integer> valueFactoryIntelligence =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 15, initialValue);

        SpinnerValueFactory<Integer> valueFactoryWisdom =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 15, initialValue);

        SpinnerValueFactory<Integer> valueFactoryCharisma =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 15, initialValue);

        //Binds the valueFactory to each spinner
        strengthSpinner.setValueFactory(valueFactoryStrength);
        dexteritySpinner.setValueFactory(valueFactoryDexterity);
        constitutionSpinner.setValueFactory(valueFactoryConstitution);
        intelligenceSpinner.setValueFactory(valueFactoryIntelligence);
        wisdomSpinner.setValueFactory(valueFactoryWisdom);
        charismaSpinner.setValueFactory(valueFactoryCharisma);
    }

}
