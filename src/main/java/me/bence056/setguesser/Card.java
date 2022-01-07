package me.bence056.setguesser;

import me.bence056.setguesser.enums.ECardColor;
import me.bence056.setguesser.enums.ECardFilling;
import me.bence056.setguesser.enums.ECardShape;

public class Card {

    public int shapeCount;
    public ECardColor cardColor;
    public ECardFilling cardFilling;
    public ECardShape cardShape;
    public int cardIdentifier;


    public Card(int shapeCount, ECardColor cardColor, ECardFilling cardFilling, ECardShape cardShape, int cardIdentifier) {
        this.shapeCount = shapeCount;
        this.cardColor = cardColor;
        this.cardFilling = cardFilling;
        this.cardShape = cardShape;
        this.cardIdentifier = cardIdentifier;
    }



}
