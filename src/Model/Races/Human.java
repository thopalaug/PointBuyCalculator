package Model.Races;

public class Human extends Race {

    private final int strength;
    private final int dexterity;
    private final int constitution;
    private final int intelligence;
    private final int wisdom;
    private final int charisma;
    private final String name = "Human";

    public Human() {
        strength = 1;
        dexterity = 1;
        constitution = 1;
        intelligence = 1;
        wisdom = 1;
        charisma = 1;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public int getDexterity() {
        return dexterity;
    }

    @Override
    public int getConstitution() {
        return constitution;
    }

    @Override
    public int getIntelligence() {
        return intelligence;
    }

    @Override
    public int getWisdom() {
        return wisdom;
    }

    @Override
    public int getCharisma() {
        return charisma;
    }

    @Override
    public String getName() {
        return name;
    }
}