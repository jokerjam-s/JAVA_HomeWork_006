package org.notebooks;

import org.notebooks.notebook.FindParameters;
import org.notebooks.notebook.Notebook;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    // Множество ноутбуков.
    private static Set<Notebook> notebookSet = new HashSet<>();
    // Pojo класс для хранения параметров поиска.
    private static FindParameters findParameters = new FindParameters();

    public static void main(String[] args) {
        int choise = 1;
        setFilter(0);   // Сбросим фильтр.

        while (choise > 0) {
            choise = menu();

            switch (choise) {
                case 1:
                    notebookSet.add(askNotebookInfo());
                    break;
                case 2: // TODO: 18.11.2022 реализовать вывод инфы по ноутбукам
                    break;
                case 3:
                    setFilter(menuFind());
                    break;
            }
        }

    }

    // Меню программы.
    private static int menu() {
        System.out.println("1 - ввести данные");
        System.out.println("2 - показать список");
        System.out.println("3 - задать фильтр");
        System.out.println("0 - выход");

        int m = readInt("> ");
        return (m < 0 || m > 3) ? 0 : m;
    }

    // Меню поиска.
    private static int menuFind() {
        System.out.println("1 - процессор");
        System.out.println("2 - объем ОЗУ");
        System.out.println("3 - тип диска");
        System.out.println("4 - объем диска");
        System.out.println("5 - ОС");
        System.out.println("0 - сбросить фильтр");

        int m = readInt(">");
        return (m < 0 || m > 5) ? 0 : m;
    }


    // Запрос данных о ноутбуке у пользователя.
    private static Notebook askNotebookInfo() {
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

    // Установка параметров фильтра.
    private static void setFilter(int mode){
        findParameters.activeParameter = mode;

        switch (mode){
            case 1:         // Процессор.
                findParameters.processor = readString("Процессор: ");
                break;
            case 2:         // Объем ОЗУ.
                findParameters.minMemorySize = readInt("Минимум ОЗУ: ");
                findParameters.maxMemorySize = readInt("Максимум ОЗУ: ");
                break;
            case 3:         // Тип диска.
                findParameters.diskType = readString("Тип диска: ");
                break;
            case 4:         // Объем диска.
                findParameters.minDiskVolume = readInt("Минимальный объем диска: ");
                findParameters.maxDiskVolume = readInt("Максимальный объем диска: ");
                break;
            case 5:         // ОС.
                findParameters.osName = readString("ОС: ");
                break;
            default:
                findParameters.diskType = "";
                findParameters.osName = "";
                findParameters.processor = "";
                findParameters.maxMemorySize = 0;
                findParameters.minMemorySize = 0;
                findParameters.minDiskVolume = 0;
                findParameters.maxDiskVolume = 0;
                break;
        }
    }

    private static void showNotebooks() {

    }


    // Ввод символьного значения.
    private static String readString(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.next();
    }

    // Ввод численного значения.
    private static int readInt(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.nextInt();
    }


}