package ua.advanced.task6.factory;

public class PlotFactories {

    public PlotFactory classicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        return new PlotFactory() {
            @Override
            public Plot plot() {
                return new Plot() {
                    @Override
                    public String toString() {
                        return hero.name()+" is great. "+hero.name()+" and "+beloved.name()+" love each other. "+
                        villain.name()+" interferes with their happiness but loses in the end.";
                    }
                };

            }
        };
    }

    public PlotFactory contemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        return new PlotFactory() {
            @Override
            public Plot plot() {
                return new Plot() {
                    @Override
                    public String toString() {
                        return hero.name()+" feels a bit awkward and uncomfortable. "+
                                "But personal issues fades, when a big trouble comes - "+epicCrisis.name()+". "+
                                hero.name()+" stands up against it, but with no success at first.But putting self "+
                                "together and help of friends, including spectacular funny "+funnyFriend.name()+
                                " restore the spirit and "+hero.name()+" overcomes the crisis and gains gratitude and respect";
                    }
                };

            }
        };
    }

    public PlotFactory marvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        return new PlotFactory() {
            @Override
            public Plot plot() {
                return new Plot() {
                    @Override
                    public String toString() {
                        String charactersHeroes = "";
                            for (int i = 0; i < heroes.length; ++i) {
                                charactersHeroes = charactersHeroes + "brave " + heroes[i].name();
                                if (i == heroes.length - 1)
                                    charactersHeroes += " ";
                                else
                                    charactersHeroes += ", ";
                            }
                        return epicCrisis.name()+" threatens the world. But "+charactersHeroes+"on guard. "+
                                "So, no way that intrigues of "+villain.name()+" overcome the willpower of inflexible heroes";
                    }
                };

            }
        };
    }
}
