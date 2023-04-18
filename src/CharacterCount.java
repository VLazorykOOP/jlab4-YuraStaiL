import java.io.*;
import java.util.Scanner;

public class CharacterCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назви файлів (розділені комою): ");
        String[] fileNames = scanner.nextLine().split(",");

        for (String fileName : fileNames) {
            int charCount = countCharacters(fileName); // Виклик методу для підрахунку кількості символів у файлі
            System.out.println("Файл: " + fileName + ", Кількість символів: " + charCount);
        }
    }

    public static int countCharacters(String fileName) {
        int charCount = 0; // Змінна для збереження кількості символів

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Змінна для збереження коду символу
            int c;
            while ((c = reader.read()) != -1) {
                // Перетворення коду символу в символ
                char ch = (char) c;

                // Перевірка, чи символ є ігнорованим
                if (!isIgnoredCharacter(ch)) {
                    charCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return charCount;
    }

    public static boolean isIgnoredCharacter(char ch) {
        // Перевірка, чи символ є пробілом, поверненням каретки, переходом на новий рядок або табуляцією
        return ch == ' ' || ch == '\r' || ch == '\n' || ch == '\t';
    }
}
