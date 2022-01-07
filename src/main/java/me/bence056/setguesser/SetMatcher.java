package me.bence056.setguesser;

import me.bence056.setguesser.enums.ECardColor;
import me.bence056.setguesser.enums.ECardFilling;
import me.bence056.setguesser.enums.ECardShape;

import java.util.*;

public class SetMatcher {


    public static List<List<Card>> search2(Card[] cards) {

        long iterations = binomialCoefficient((long)cards.length, (long)2);

        System.out.println("Iterations: " + iterations);

        List<Card> cardlist = Arrays.stream(cards).toList();

        List<List<Card>> CardPairs = new ArrayList<>();

        for(int i=0; i<cards.length; i++) {

            for(int k=0; k<cards.length; k++) {

                Card c1 = cardlist.get(i);
                Card c2 = cardlist.get(k);



                if(c1.cardIdentifier!=c2.cardIdentifier) {



                    boolean bisDuplicate = false;



                    for(List<Card> existingPairs : CardPairs) {
                        if(existingPairs.contains(c2) && existingPairs.contains(c1)) {
                            bisDuplicate = true;
                            break;
                        }
                    }

                    List<Card> cpairs = new ArrayList<>();
                    cpairs.add(c1);
                    cpairs.add(c2);
                    if(!bisDuplicate) {
                        CardPairs.add(cpairs);
                    }

                }
            }
        }

        return CardPairs;

    }

    private static long factOf(long n) {

        long retVal = 1;

        if(n == 0) {
            return 0;
        }

        for(long i=n; i>0; i--) {
            retVal *= i;
        }

        return retVal;

    }

    private static long binomialCoefficient(long n, long k) {

        return factOf(n) / (factOf(k) * factOf(n-k));

    }

    public static List<List<Card>> getSets(Card[] cards) {

        List<List<Card>> doublePairs = search2(cards);

        System.out.println(doublePairs.size());

//        Arrays.stream(ECardFilling.values()).toList().indexOf()

        List<List<Card>> possibleSets = new ArrayList<>();

        for(List<Card> CurrentPair : doublePairs) {

            Card c1 = CurrentPair.get(0);
            Card c2 = CurrentPair.get(1);

            int c1Count = c1.shapeCount - 1;
            int c2Count = c2.shapeCount - 1;

            int c1Shape = Arrays.stream(ECardShape.values()).toList().indexOf(c1.cardShape);
            int c2Shape = Arrays.stream(ECardShape.values()).toList().indexOf(c2.cardShape);

            int c1Color = Arrays.stream(ECardColor.values()).toList().indexOf(c1.cardColor);
            int c2Color = Arrays.stream(ECardColor.values()).toList().indexOf(c2.cardColor);

            int c1Filling = Arrays.stream(ECardFilling.values()).toList().indexOf(c1.cardFilling);
            int c2Filling = Arrays.stream(ECardFilling.values()).toList().indexOf(c2.cardFilling);


            int c3Count = get3rdProperty(c1Count, c2Count);
            int c3Shape = get3rdProperty(c1Shape, c2Shape);
            int c3Color = get3rdProperty(c1Color, c2Color);
            int c3Filling = get3rdProperty(c1Filling, c2Filling);

            String lastCardIDStr = String.valueOf(c3Count) + String.valueOf(c3Shape) + String.valueOf(c3Color) + String.valueOf(c3Filling);
            int lastCardID = Integer.parseInt(lastCardIDStr, 3);

            Card c3 = SetMaker.getCardByID(lastCardID);

            List<Card> currentSet = new ArrayList<>();
            currentSet.add(c1);
            currentSet.add(c2);
            currentSet.add(c3);

            List<Card> cardList = Arrays.stream(cards).toList();

            if(cardList.contains(c3)) {

                //check for duplicates:

                boolean bSetExists = false;

                for(List<Card> set : possibleSets) {

                    List<Integer> IDLIST = new ArrayList<>();
                    IDLIST.add(set.get(0).cardIdentifier);
                    IDLIST.add(set.get(1).cardIdentifier);
                    IDLIST.add(set.get(2).cardIdentifier);

                    IDLIST.remove(Integer.valueOf(currentSet.get(0).cardIdentifier));
                    IDLIST.remove(Integer.valueOf(currentSet.get(1).cardIdentifier));
                    IDLIST.remove(Integer.valueOf(currentSet.get(2).cardIdentifier));

                    if(IDLIST.size()==0) {
                        bSetExists = true;
                        break;
                    }

                }

                if(!bSetExists) {
                    possibleSets.add(currentSet);
                }
            }

        }


        return possibleSets;
    }


    private static int get3rdProperty(int c1prop, int c2prop) {

        if(c1prop == c2prop) {
            return c1prop;
        }else {

            List<Integer> possibleNumbers = new ArrayList<>();
            possibleNumbers.add(0);
            possibleNumbers.add(1);
            possibleNumbers.add(2);

            possibleNumbers.remove(Integer.valueOf(c1prop));
            possibleNumbers.remove(Integer.valueOf(c2prop));

            return possibleNumbers.get(0);

        }

    }

}



//WEIRD COMBO: 2100 2200 1222 1001 1012 2210 1110 1112 2022 0022 2211 1111 0121 2120 1012 2000 1101 0122 1210 0202;