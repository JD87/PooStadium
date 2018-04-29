package com.example.david.poostadium;

/**
 * Created by Nomad on 21/04/2018.
 */

public class MainCharacter {

    // we defined the features of the character

    String name = "";
    int life = 0;
    int level = 0;
    int armor = 0;
    int hitpoints = 0;
    int mana = 0;
    int exp = 0;

    /*
    Constructor is the first code to be executed when the object is created, also where the values of the features areinitialized
    it has the name of the class, therefore dosent need return and is automatically executed
    */

    public MainCharacter(){

        //  we assigned the default values

        name = "new character";
        life = 1000;
        level = 1;
        armor = 50;
        hitpoints = 60;
        mana = 60;
        exp = 0;

    }

    public MainCharacter(String name, int life, int level, int armor, int phisicalattack, int mana, int exp) {
        this.name = name;
        this.life = life;
        this.level = level;
        this.armor = armor;
        this.hitpoints = phisicalattack;
        this.mana = mana;
        this.exp = exp;
    }

    public void drink_lppotion(){

        life = life + 200;

    }

    public void drink_lppotion(int lp){

        life = life + lp;

    }

    public void attack(MainCharacter objective){

        objective.armor = objective.armor - hitpoints;
        if(objective.armor < 0){
            objective.life += objective.armor;
            objective.armor = 0;
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setLife(int life){
        this.life = life;
    }

    public int getLife(){
        return life;
    }
}