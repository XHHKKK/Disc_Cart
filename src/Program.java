import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Store store = new Store();

        store.addShopper(new OldShopper("Ньютон", "Исаак", "Иванович", "1980-10-15"));
        store.addShopper(new NewShopper("Дарвин", "Чарльз", "Петрович", "1990-05-20", "1234567890"));
        store.addShopper(new NewShopper("Эйнштейн", "Альберт", "Сидорович", "1995-08-25", "0987654321"));

        System.out.println("Информация о покупателях:");
        store.displayShoppers();

        // Пример поиска телефонного номера
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ФИО для поиска номера телефона (фамилия имя отчество):");
        String[] nameParts = scanner.nextLine().split(" ");
        if (nameParts.length == 3) {
            String lastName = nameParts[0];
            String firstName = nameParts[1];
            String patronymic = nameParts[2];
            Optional<String> phoneNumber = store.findPhoneNumber(lastName, firstName, patronymic);
            if (phoneNumber.isPresent()) {
                System.out.println("Номер телефона: " + phoneNumber.get());
            } else {
                System.out.println("Покупатель не найден или у него нет номера телефона.");
            }
        } else {
            System.out.println("Некорректный ввод.");
        }

        System.out.println("Количество покупателей: " + store.countOldAndNewShoppers());
    }
}
