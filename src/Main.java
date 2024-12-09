import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

abstract class Shopper {
    protected String lastName;
    protected String firstName;
    protected String patronymic;
    protected String birthDate; // В формате YYYY-MM-DD

    public Shopper(String lastName, String firstName, String patronymic, String birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
    }

    public abstract String getContactInfo();

    public boolean isBirthday(String currentDate) {
        return currentDate.equals(birthDate);
    }
}

class OldShopper extends Shopper {
    public OldShopper(String lastName, String firstName, String patronymic, String birthDate) {
        super(lastName, firstName, patronymic, birthDate);
    }

    @Override
    public String getContactInfo() {
        return String.format("Старый покупатель: %s %s %s, Дата рождения: %s",
                lastName, firstName, patronymic, birthDate);
    }
}

class NewShopper extends Shopper {
    private String phoneNumber;

    public NewShopper(String lastName, String firstName, String patronymic, String birthDate, String phoneNumber) {
        super(lastName, firstName, patronymic, birthDate);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getContactInfo() {
        return String.format("Новый покупатель: %s %s %s, Дата рождения: %s, Номер телефона: %s",
                lastName, firstName, patronymic, birthDate, phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

class Store {
    private List<Shopper> shoppers = new ArrayList<>();

    public void addShopper(Shopper shopper) {
        shoppers.add(shopper);
    }

    public void displayShoppers() {
        for (Shopper shopper : shoppers) {
            System.out.println(shopper.getContactInfo());
        }
    }

    public Optional<String> findPhoneNumber(String lastName, String firstName, String patronymic) {
        for (Shopper shopper : shoppers) {
            if (shopper instanceof NewShopper
                    && shopper.lastName.equals(lastName)
                    && shopper.firstName.equals(firstName)
                    && shopper.patronymic.equals(patronymic)) {
                return Optional.of(((NewShopper) shopper).getPhoneNumber());
            }
        }
        return Optional.empty();
    }

    public int countOldAndNewShoppers() {
        int oldCount = 0;
        int newCount = 0;

        for (Shopper shopper : shoppers) {
            if (shopper instanceof OldShopper) {
                oldCount++;
            } else if (shopper instanceof NewShopper) {
                newCount++;
            }
        }
        return oldCount + newCount; // Возвращаем общее количество покупателей
    }
}
