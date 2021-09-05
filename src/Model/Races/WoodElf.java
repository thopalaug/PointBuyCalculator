package Model.Races;

public class WoodElf extends Race {
    private final int strength;
    private final int dexterity;
    private final int constitution;
    private final int intelligence;
    private final int wisdom;
    private final int charisma;
    private final String name = "Wood Elf";

    public WoodElf() {
        strength = 0;
        dexterity = 2;
        constitution = 0;
        intelligence = 0;
        wisdom = 1;
        charisma = 0;
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
