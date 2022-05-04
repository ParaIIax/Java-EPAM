package ua.advanced.task6;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Iterators {
    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array) {
        return new Iterator<Integer>() {
            int index = 0;
            boolean switcher = true;

            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    int element = array[index];
                    if (switcher) {
                        switcher = false;
                        return element;
                    }
                    index++;
                    switcher = true;
                    return element;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };

    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return new Iterator<Integer>() {
            int index = 0;
            boolean switcher = true;
            boolean switcher2 = true;

            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    int element = array[index];
                    if (switcher) {
                        switcher = false;
                        return element;
                    }
                    if (switcher2) {
                        switcher2 = false;
                        return element;
                    }
                    index++;
                    switcher = true;
                    switcher2 = true;
                    return element;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new Iterator<Integer>() {
            int index = 0;
            boolean switchers[] = new boolean[4];

            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    int element = array[index];
                    for (int i = 0; i < switchers.length; ++i) {
                        if (switchers[i] == false) {
                            switchers[i] = true;
                            return element;
                        }
                    }
                    index++;
                    fillArr();
                    return element;
                } else {
                    throw new NoSuchElementException();
                }
            }

            public void fillArr() {
                for (int i = 0; i < switchers.length; ++i)
                    switchers[i] = false;
            }
        };
    }

    public static Iterable<String> table(String[] columns, int[] rows) {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    int columnID = 0;
                    int rowID = 0;

                    @Override
                    public boolean hasNext() {
                        return columnID < columns.length;
                    }

                    @Override
                    public String next() {
                        String cell = columns[columnID] + rows[rowID];
                        if (rowID < rows.length - 1)
                            rowID++;
                        else {
                            columnID++;
                            rowID = 0;
                        }
                        return cell;
                    }
                };
            }
        };
    }

}
