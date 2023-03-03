package ru.amalysheva;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Алгоритм действия метода main: считывается число n из fileForInput, а затем парсится в long.
 * Цикл заполняет массив long числами от 0 до n включительно. Заводится stringBuilder. Массив long представляется в виде строки, а далее - в виде массива char.
 * Далее происходит перебор массива символов: всё, что не число - пропускается. Так в stringBuilder попадают только численные значения, которые
 * после перебора, представляются массивом char.
 * Заводится mapForRepetitions, ключ - char, значение - long. Происходит учёт всех повторяющихся чисел.
 * Так как нас интересует повторяющееся количество единиц, в "output.txt" попадает число повторений оных в строке.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        File fileForInput = new File("input.txt");
        BufferedReader fileForRead = new BufferedReader(new FileReader(fileForInput));
        String str = fileForRead.readLine();
        long value = Long.parseLong(str);
        long[] arrayFromFile = new long[(int) (value + 1)];
        for (int i = 0; i < arrayFromFile.length; i++) {
            arrayFromFile[i] = i;
        }
        String arrayToString = Arrays.toString(arrayFromFile);
        StringBuilder stringBuilder = new StringBuilder();

        char[] stringToChar = arrayToString.toCharArray();
        for (int i = 0; i < stringToChar.length; i++) {
            if (stringToChar[i] == ' ' || stringToChar[i] == ',' || stringToChar[i] == '[' || stringToChar[i] == ']') {
                stringBuilder.append("");
            } else {
                stringBuilder.append(stringToChar[i]);
            }
        }
        char[] arrayIntoChars = stringBuilder.toString().toCharArray();

        HashMap<Character, Long> mapForRepetitions = new HashMap<>();

        for (int i = 0; i < arrayIntoChars.length; ++i) {
            char item = arrayIntoChars[i];

            if (mapForRepetitions.containsKey(item) && item == 49)
                mapForRepetitions.put(item, mapForRepetitions.get(item) + 1);
            else
                mapForRepetitions.put(item, 1L);
        }

        StringBuilder stringFromHash = new StringBuilder();

        for (Map.Entry<Character, Long> element : mapForRepetitions.entrySet()) {
            if (element.getValue() > 1) {
                stringFromHash.append(element.getValue());
            }
        }

        System.out.print(stringFromHash);

        FileWriter writer = new FileWriter("output.txt");
        writer.write(stringFromHash.toString());
        writer.close();
    }
}