package org.notebooks;

import org.notebooks.notebook.FindParameters;
import org.notebooks.notebook.Notebook;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
                case 2:
                    showNotebooks();
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
    private static void setFilter(int mode) {
        findParameters.activeParameter = mode;

        switch (mode) {
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

    // Отображение данных. Если назначен фильтр -
    private static void showNotebooks() {
        Logger logger = Logger.getAnonymousLogger();
        List<Notebook> infoNotebook = null;

        if (findParameters.activeParameter > 0) {
            logger.info("Параметры поиска:");

            switch (findParameters.activeParameter) {
                case 1:
                    logger.info(String.format("Процессор : %s", findParameters.processor));
                    infoNotebook = notebookSet.stream()
                            .filter(e -> e.getProcessor().equals(findParameters.processor))
                            .collect(Collectors.toList());
                    break;
                case 2:
                    logger.info(String.format("ОЗУ от %d до %d", findParameters.minMemorySize, findParameters.maxMemorySize));
                    infoNotebook = notebookSet.stream()
                            .filter(e -> (e.getMemorySize() >= findParameters.minMemorySize && e.getMemorySize() <= findParameters.maxMemorySize))
                            .collect(Collectors.toList());
                    break;
                case 3:
                    logger.info(String.format("Тип диска: %s", findParameters.diskType));
                    infoNotebook = notebookSet.stream()
                            .filter(e -> e.getDiskType().equals(findParameters.diskType))
                            .collect(Collectors.toList());
                    break;
                case 4:
                    logger.info(String.format("Объем диска от %d до %d", findParameters.minDiskVolume, findParameters.maxDiskVolume));
                    infoNotebook = notebookSet.stream()
                            .filter(e -> (e.getDiskVolume() >= findParameters.minDiskVolume && e.getDiskVolume() <= findParameters.maxDiskVolume))
                            .collect(Collectors.toList());
                    break;
                case 5:
                    logger.info(String.format("ОС: %s", findParameters.osName));
                    infoNotebook = notebookSet.stream()
                            .filter(e -> (e.getDiskVolume() >= findParameters.minDiskVolume && e.getDiskVolume() <= findParameters.maxDiskVolume))
                            .collect(Collectors.toList());
                    break;
            }
            if(infoNotebook == null){
                logger.info("Нет данных");
            }else {
                logger.info(infoNotebook.toString());
            }
        }else {
            logger.info(String.valueOf(notebookSet));
        }
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