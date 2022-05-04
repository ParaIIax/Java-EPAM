package ua.advanced.task6.strategy;

import java.util.*;

public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return new CardDealingStrategy() {
            final int cardsPerPlayer = 2;
            final int cardsForCommunity = 5;

            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int players) {
                Map<String, List<Card>> map = new TreeMap<>();
                List<Card> communityCardList = new ArrayList<>();
                List<Card> playersCardList = new ArrayList<>();
                for (int i = 0; i < players * cardsPerPlayer; ++i)
                    playersCardList.add(deck.dealCard());
                for (int i = 0; i < cardsForCommunity; ++i)
                    communityCardList.add(deck.dealCard());
                map.put("Community", communityCardList);
                for (int i = 1; i < (players + 1); ++i) {
                    String player = "Player " + i;
                    List<Card> playerCardList = new ArrayList<>();
                    playerCardList.add(playersCardList.get(0));
                    playerCardList.add(playersCardList.get(players));
                    playersCardList.remove(0);
                    map.put(player, playerCardList);
                }
                List<Card> rest = deck.restCards();
                map.put("Remaining", rest);
                return map;
            }
        };
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return new CardDealingStrategy() {
            final int cardsPerPlayer = 5;

            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int players) {
                Map<String, List<Card>> map = new TreeMap<>();
                List<Card> playersCardList = new ArrayList<>();
                for (int i = 0; i < players * cardsPerPlayer; ++i) {
                    playersCardList.add(deck.dealCard());
                }
                for (int i = 1; i < (players + 1); ++i) {
                    String player = "Player " + i;
                    List<Card> playerCardList = new ArrayList<>();
                    for (int j = 0; j < cardsPerPlayer; ++j)
                        playerCardList.add(playersCardList.get(j * players));
                    playersCardList.remove(0);
                    map.put(player, playerCardList);
                }
                List<Card> rest = deck.restCards();
                map.put("Remaining", rest);
                return map;
            }
        };
    }

    public static CardDealingStrategy bridgeCardDealingStrategy(){
        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int players) {
                int cardsPerPlayer = deck.size() / players;
                Map<String, List<Card>> map = new TreeMap<>();
                List<Card> playersCardList = new ArrayList<>();
                for (int i = 0; i < players * cardsPerPlayer; ++i) {
                    playersCardList.add(deck.dealCard());
                }
                for (int i = 1; i < (players + 1); ++i) {
                    String player = "Player " + i;
                    List<Card> playerCardList = new ArrayList<>();
                    for (int j = 0; j < cardsPerPlayer; ++j) {
                        playerCardList.add(playersCardList.get(j * players));
                    }
                    playersCardList.remove(0);
                    map.put(player, playerCardList);
                }
                return map;
            }
        };
    }

    public static CardDealingStrategy foolCardDealingStrategy(){
        return new CardDealingStrategy() {
            final int cardsPerPlayer = 6;

            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int players) {
                Map<String, List<Card>> map = new TreeMap<>();
                List<Card> playersCardList = new ArrayList<>();
                for (int i = 0; i < players * cardsPerPlayer; ++i) {
                    playersCardList.add(deck.dealCard());
                }
                for (int i = 1; i < (players + 1); ++i) {
                    String player = "Player " + i;
                    List<Card> playerCardList = new ArrayList<>();
                    for (int j = 0; j < cardsPerPlayer; ++j)
                        playerCardList.add(playersCardList.get(j * players));
                    playersCardList.remove(0);
                    map.put(player, playerCardList);
                }
                List<Card> trumpCardList = new ArrayList<>();
                trumpCardList.add(deck.dealCard());
                List<Card> rest = deck.restCards();
                map.put("Remaining", rest);
                map.put("Trump card", trumpCardList);
                return map;
            }
        };
    }

}
