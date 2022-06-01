import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Secret {

    static Scanner scanner = new Scanner(System.in);
    static String logo = "Daktah™";
    public static int KEY = 3;
    static String encode = "";
    static String decode = "";
    static int count = (int) (Math.random() * 1000);
    static String homePath = System.getProperty("user.home") + "\\Desktop\\newSecret";
    static String finalPathEncode = homePath + "\\newEncode" + count + ".txt";
    static String finalPathDecode = homePath + "\\newDecode" + count + ".txt";
    static String buffer;

    public static String encode(String message) {
        //Зашифровать сообщение
        for (int x = 0; x < message.length(); x++) {
            encode = encode + (char) (message.charAt(x) ^ KEY);
        }
        return encode;
    }

    public static String decode(String message) {
        //Дешифровать сообщение
        for (int x = 0; x < message.length(); x++) {
            decode = decode + (char) (message.charAt(x) ^ KEY);
        }
        return decode;
    }

    public static void encodeSecretFile() {
        //Работа с зашифрованным файлом
        System.out.println((char) 27 + "[33m\nВведите полный путь к файлу: " + (char)27 + "[0m");
        Path pathScanner = Path.of(scanner.nextLine());
        System.out.print((char) 27 + "[33m\nВведите ключ: " + (char)27 + "[0m");
        KEY = scanner.nextInt();

        if (Files.isRegularFile(pathScanner)) {
            try {
                buffer = encode(Files.readString(pathScanner)) + "\n\n" + "Ключ шифрования: " + KEY + "\n" + logo;

                if (!Files.isDirectory(Path.of(homePath)))
                    Files.createDirectory(Path.of(homePath));
                Files.createFile(Path.of(finalPathEncode));
                String result = String.valueOf(Files.writeString(Path.of(finalPathEncode), buffer));

                System.out.println("\n=================================================");
                System.out.println((char) 27 + "[33mВаш файл зашифрован и лежит по адресу:" + (char)27 + "[0m");
                System.out.println((char) 27 + "[34m" + result + (char)27 + "[0m");

            } catch (IOException e) {
                System.err.println("Системная ошибка!");
            }
        } else System.err.println("Фаил не найден!");
    }

    public static void decodeSecretFile() {
        //Работа с дешифрованным файлом
        System.out.println((char) 27 + "[33m\nВведите полный путь к файлу: " + (char)27 + "[0m");
        Path pathScanner = Path.of(scanner.nextLine());
        System.out.print((char) 27 + "[33m\nВведите ключ: " + (char)27 + "[0m");
        KEY = scanner.nextInt();

        if (Files.isRegularFile(pathScanner)) {
            try {
                buffer = Files.readString(pathScanner);
                String temp = decode(buffer) + "\n\n" + "Ключ шифрования: " + KEY + "\n" + logo;

                if (!Files.isDirectory(Path.of(homePath)))
                    Files.createDirectory(Path.of(homePath));
                Files.createFile(Path.of(finalPathDecode));
                String result = String.valueOf(Files.writeString(Path.of(finalPathDecode), temp));

                System.out.println("\n=================================================");
                System.out.println((char) 27 + "[33mВаш файл расшифрован и лежит по адресу:" + (char)27 + "[0m");
                System.out.println((char) 27 + "[34m" + result + (char)27 + "[0m");

            } catch (IOException e) {
                System.err.println("Системная ошибка!");
            }
        } else System.err.println("Фаил не найден!");
    }

    public static void main(String[] args) {
        //Меню
        System.out.println((char) 27 + "[35m ***SECRET*** " + (char)27 + "[0m");

        System.out.println((char) 27 + "[33m\nВыберите действие: " + (char)27 + "[0m");
        System.out.println((char) 27 + "[32m1. " + (char)27 + "[0m" + "Зашифровать текст");
        System.out.println((char) 27 + "[32m2. " + (char)27 + "[0m" + "Расшифровать текст");

        System.out.print((char) 27 + "[33mВаш ответ: " + (char)27 + "[0m");
        String answer = scanner.nextLine();
        //Вывод на экран
        if (answer.equals("1")) {
            encodeSecretFile();
            System.out.println("=================================================");
            System.out.println((char) 27 + "[33m\nКлюч шифрования: " + (char)27 + "[0m" + (char) 27 + "[32m" + KEY + (char)27 + "[0m");
            System.out.println((char) 27 + "[35m" + logo + (char)27 + "[0m");
        } else if (answer.equals("2")) {
            decodeSecretFile();
            System.out.println("=================================================");
            System.out.println((char) 27 + "[33m\nКлюч шифрования: " + (char)27 + "[0m" + (char) 27 + "[32m" + KEY + (char)27 + "[0m");
            System.out.println((char) 27 + "[35m" + logo + (char)27 + "[0m");
        } else System.err.println("\nОшибка ввода!");
    }
}