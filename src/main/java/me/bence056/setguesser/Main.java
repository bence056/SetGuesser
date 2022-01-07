package me.bence056.setguesser;

import me.bence056.setguesser.enums.ECardColor;
import me.bence056.setguesser.enums.ECardFilling;
import me.bence056.setguesser.enums.ECardShape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        System.out.println("Starting set-sequencer..");

        if(args.length == 0) {

            System.out.println("No argument specified! - Exiting.");

        }else if(args[0].equals("--random") || args[0].equals("-r")) {

            if(args.length <= 1) {
                System.out.println("Set length not specified! - Exiting.");
                return;
            }

            SetMaker.GenerateSet();

            Card[] cards = SetMaker.selectCards(Integer.parseInt(args[1]));

            List<Card> PLACED_CARDS = Arrays.stream(cards).toList();

            System.out.println("PLACED CARDS: " + PLACED_CARDS.size());

            for(Card CURRENT_CARD : PLACED_CARDS) {

                System.out.println(CURRENT_CARD.shapeCount);
                System.out.println(CURRENT_CARD.cardColor);
                System.out.println(CURRENT_CARD.cardFilling);
                System.out.println(CURRENT_CARD.cardShape);
                System.out.println("------------------------");

            }

            List<List<Card>> SETS = SetMatcher.getSets(cards);

            System.out.println("POSSIBLE SETS: " + SETS.size());

            for(List<Card> CURRENT_SET : SETS) {

                System.out.println("===========================================================");

                for(Card c : CURRENT_SET) {
                    System.out.println(c.shapeCount);
                    System.out.println(c.cardColor);
                    System.out.println(c.cardFilling);
                    System.out.println(c.cardShape);
                    System.out.println("------------------------");
                }
                System.out.println("===========================================================");
            }
        }else if(args[0].equals("--custom") || args[0].equals("-c")){

            if(args.length <= 1) {
                System.out.println("No custom set code specified! - Exiting.");
                return;
            }

            List<String> redArgs = new ArrayList<>(Arrays.asList(args));
            redArgs.remove(0);

            List<String> cardIDs = new ArrayList<>(redArgs);



            for(String cardID : cardIDs) {

                List<Integer> CurrentData = new ArrayList<>();

                for(char c : cardID.toCharArray()) {
                    CurrentData.add(Integer.parseInt(String.valueOf(c)));
                }

                int numOfShapes = CurrentData.get(0) + 1;
                ECardColor newColor = (ECardColor.values())[CurrentData.get(1)];
                ECardFilling newFilling = (ECardFilling.values())[CurrentData.get(2)];
                ECardShape newShape = (ECardShape.values())[CurrentData.get(3)];


                String cardIdentifierStr = String.valueOf(CurrentData.get(0)) + String.valueOf(CurrentData.get(3)) + String.valueOf(CurrentData.get(1)) + String.valueOf(CurrentData.get(2));
                int cardIdentifier = Integer.parseInt(cardIdentifierStr, 3);


                Card newCard = new Card(numOfShapes, newColor, newFilling, newShape, cardIdentifier);

                SetMaker.AddCardToSet(newCard);


            }

            //PRINT RESULTS:

            Card[] cards = SetMaker.selectCards(SetMaker.cards_static.size());

            List<Card> PLACED_CARDS = Arrays.stream(cards).toList();

            System.out.println("PLACED CARDS: " + PLACED_CARDS.size());

            for(Card CURRENT_CARD : PLACED_CARDS) {

                System.out.println(CURRENT_CARD.shapeCount);
                System.out.println(CURRENT_CARD.cardColor);
                System.out.println(CURRENT_CARD.cardFilling);
                System.out.println(CURRENT_CARD.cardShape);
                System.out.println("------------------------");

            }

            List<List<Card>> SETS = SetMatcher.getSets(cards);

            System.out.println("POSSIBLE SETS: " + SETS.size());

            for(List<Card> CURRENT_SET : SETS) {

                System.out.println("===========================================================");

                for(Card c : CURRENT_SET) {
                    System.out.println(c.shapeCount);
                    System.out.println(c.cardColor);
                    System.out.println(c.cardFilling);
                    System.out.println(c.cardShape);
                    System.out.println("------------------------");
                }
                System.out.println("===========================================================");
            }


        }





    }

}
