package Model;

public class ChangePointCost {

    public int incrementStrengthPointCost(int value){
        if(value >= 5){
            value += 2;
        }else{
            value += 1;
        }
        return value;
    }

    public int decrementStrengthPointCost(int value){
        if(value == 0){
            value = 0;
        }else if(value > 5){
            value -= 2;
        }else{
            value -= 1;
        }
        return value;
    }

}
