import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Schedule {
    private static final List<Action> actions = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    private static void printMenu() {
        System.out.println("1. Добавить событие");
        System.out.println("2. Просмотреть список событий");
        System.out.println("3. Фильтр по дате");
        System.out.println("4. Удалить событие");
        System.out.println("5. Выход");
        System.out.print("Выберите действие (введите номер): ");
    }

    private static int getChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Некорректный ввод. Введите число.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void addEvent() {
        scanner.nextLine();
        System.out.print("Введите описание события: ");
        String description = scanner.nextLine();

        System.out.print("Введите дату и время начала события (в формате \"ГГГГ-ММ-ДД ЧЧ:ММ\"): ");
        String dateTimeString = scanner.nextLine();

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Action event = new Action(description, dateTime);
            actions.add(event);
            System.out.println("Событие успешно добавлено.");
        } catch (Exception e) {
            System.out.println("Некорректный формат даты и времени. Попробуйте снова.");
        }
    }

    private static void viewEvents() {
        System.out.println("Список событий:");
        if (actions.isEmpty()) {
            System.out.println("(пусто)");
        } else {
            for (int i = 0; i < actions.size(); i++) {
                System.out.println((i + 1) + "." + actions.get(i));
            }
        }
    }

    private static void filterByDate() {
        scanner.nextLine(); // consume the newline character
        System.out.print("Введите дату для фильтрации (в формате \"ГГГГ-ММ-ДД\"): ");
        String dateString = scanner.nextLine();

        try {
            LocalDateTime filterDate = LocalDateTime.parse(dateString + " 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            System.out.println("Список событий на " + dateString + ":");
            List<Action> fitActions = actions.stream()
                    .filter(s -> s.getDate().toLocalDate().isEqual(filterDate.toLocalDate()))
                    .collect(Collectors.toList());
            if(fitActions.isEmpty()){
                System.out.println("(пусто)");
            }
            else{
                for(int i = 0; i < fitActions.size(); i++){
                    System.out.println((i + 1) + "." + fitActions.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println("Некорректный формат даты. Попробуйте снова.");
        }
    }

    private static void deleteEvent() {
        viewEvents();
        if (!actions.isEmpty()) {
            System.out.print("Введите номер события для удаления: ");
            int eventNumber = getChoice();

            if (eventNumber >= 1 && eventNumber <= actions.size()) {
                Action removedAction = actions.remove(eventNumber - 1);
                System.out.println("Событие \"" + removedAction.getDescription() + "\" успешно удалено.");
            } else {
                System.out.println("Некорректный номер события. Попробуйте снова.");
            }
        }
    }

    public void run(){
        System.out.println("Добро пожаловать в приложение \"Планировщик событий\"!");
        boolean working = true;

        while (working) {
            printMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    addEvent();
                    break;
                case 2:
                    viewEvents();
                    break;
                case 3:
                    filterByDate();
                    break;
                case 4:
                    deleteEvent();
                    break;
                case 5:
                    System.out.println("До свидания!");
                    working = false;
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }
}
