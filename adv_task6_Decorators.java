package ua.advanced.task6;

import java.util.ArrayList;
import java.util.List;

public class Decorators {
    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
        List<String> evenIndicesList = new ArrayList<>();
        for (int i = 0; i < sourceList.size(); ++i) {
            if (i % 2 == 0)
                evenIndicesList.add(sourceList.get(i));
        }
        return evenIndicesList;
    }

}
