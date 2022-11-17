package org.notebooks;

import org.notebooks.notebook.Notebook;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    // множество ноутбуков
    private static Set<Notebook> notebookSet = new HashSet<>();
    //private Map<>


    public static void main(String[] args) {
        int choise = 1;

        while (choise > 0){
            choise = menu();

            switch (choise){
                case 1:
                    notebookSet.add(askNotebookInfo());
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }

    }

    // меню программы
    private static int menu() {
        System.out.println("1 - ввести данные");
        System.out.println("2 - показать список");
        System.out.println("3 - задать фильтр");
        System.out.println("0 - выход");

        int m = readInt("> ");
        if (m < 0 || m > 3) {
            m = 0;
        }

        return m;
    }


    // запрос данных о ноутбуке у пользователя
    public static Notebook askNotebookInfo() {
        int memorySize;
        String processor;
        int diskVolume;
        String diskType;
        String osName;

        processor = readString("Процессор: ");
        memorySize = readInt("Объем ОЗУ: ");
        diskType = readString("Тип диска: ");
        diskVolume = readInt("Объем диска: ");
        osName = readString("Оп. система: ");

        return new Notebook(memorySize, processor, diskVolume, diskType, osName);
    }

    // ввод символьного значения
    public static String readString(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.next();
    }

    // ввод численного значения
    public static int readInt(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextInt();
    }


}