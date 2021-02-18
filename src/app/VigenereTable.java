package app;

public class VigenereTable {
    private final int tableLength = 26;
    char[][] table = new char[26][26];

    public void setupTable() {

        char[] tempArr = {'a', 'b', 'c', 'd',
                'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z'};

        int currentPos;
        int startPos = 1; //start pos in char array, is incremented everytime the loop is run

        // tempArr length - startpos == dif, and if not full run array again til full
        for (int x = 0; x < tableLength; x++) {
            for (int y = 0; y < tableLength; y++) {

                currentPos = y + startPos; //update current pos in char array

                if (currentPos >= tableLength) {
                    int dif = currentPos - (tableLength - 1);

                    //Fill the remaining element in the char array by iterating over tempArr again with dif == the number of missing elements
                    for (int i = 0; i < dif; i++) {
                        table[x][y] = tempArr[i];
                    }

                } else {
                    table[x][y] = tempArr[currentPos]; //adds element from tempArr to table
                }

            }
            startPos++;
        }
    }

    public char[][] getTable() {
        return table;
    }

}
