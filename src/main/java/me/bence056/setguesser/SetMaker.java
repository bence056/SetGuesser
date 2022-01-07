package me.bence056.setguesser;

import me.bence056.setguesser.enums.ECardColor;
import me.bence056.setguesser.enums.ECardFilling;
import me.bence056.setguesser.enums.ECardShape;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SetMaker {

    public static Random rand = new Random();
    public static List<Card> cards = new ArrayList<Card>();
    public static List<Card> cards_static = new ArrayList<Card>();

    /*public static Card[] generateSet(int numOfCards) {

        Card[] newSet = new Card[numOfCards];

        for(int i=0; i<numOfCards; i++) {

            int random1 = rand.nextInt(2) + 1;
            int random2 = rand.nextInt(2) + 1;
            int random3 = rand.nextInt(2) + 1;

            int numOfShapes = rand.nextInt(2) + 1;
            ECardColor newColor = (ECardColor.values())[random1];
            ECardShape newShape = (ECardShape.values())[random2];
            ECardFilling newFilling = (ECardFilling.values())[random3];

            String cardIDx3 = String.valueOf(random1) + String.valueOf(random2) + String.valueOf(random3);
            int cardIdentifier = Integer.parseInt(cardIDx3, 3);


            //Safecheck
            boolean bDuplicate = false;
            for(Card c : newSet) {
                if(c == null) break;
                if(c.cardIdentifier == cardIdentifier) {
                    i--;
                    bDuplicate = true;
                    break;
                }
            }
            if(!bDuplicate) {
                Card newCard = new Card(numOfShapes, newColor, newFilling, newShape, cardIdentifier);
                newSet[i] = newCard;
            }

        }

        return newSet;
    }*/

    public static void GenerateSet() {

        for(int num=0; num<3; num++) {
            for(int shape=0; shape<3; shape++) {
                for(int color=0; color<3; color++) {
                    for(int filling=0; filling<3; filling++) {

                        int numOfShapes = num + 1;
                        ECardColor newColor = (ECardColor.values())[color];
                        ECardShape newShape = (ECardShape.values())[shape];
                        ECardFilling newFilling = (ECardFilling.values())[filling];

                        String cardIDx3 = String.valueOf(num) + String.valueOf(shape) + String.valueOf(color) + String.valueOf(filling);
                        int cardIdentifier = Integer.parseInt(cardIDx3, 3);


                        Card newCard = new Card(numOfShapes, newColor, newFilling, newShape, cardIdentifier);
                        cards.add(newCard);
                        cards_static.add(newCard);

                    }
                }
            }
        }

    }

    public static Card[] selectCards(int numOfCards) {

        Card[] selectedCards = new Card[numOfCards];

        for(int i=0; i<numOfCards; i++) {
            int guess = -1;
            if(cards.size() == 1) {
                guess = 0;
            }else {
                guess = rand.nextInt(cards.size() - 1);
            }
            selectedCards[i] = cards.get(guess);
            cards.remove(guess);

        }

        return selectedCards;
    }

    public static Card getCardByID(int id) {

        for(Card c : cards_static) {

            if(c.cardIdentifier == id) {
                return c;
            }
        }


        return null;
    }

    public static void AddCardToSet(Card card) {
        cards.add(card);
        cards_static.add(card);
    }

}
