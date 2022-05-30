public class Secret {

    public static final int KEY = 3;

    static String encode = "";
    static String decode = "";

    public static String encode(String message) {
        //Зашифровать сообщение
        for (int x = 0; x < message.length(); x++) {
            //Построение зашифрованной строки сообщения
            encode = encode + (char) (message.charAt(x) ^ KEY);
        }
        return encode;
    }
    public static String decode(String message) {
        //Дешифровать сообщение
        for (int x = 0; x < message.length(); x++) {
            //Построение дешифрованной строки сообщения
            decode = decode + (char) (encode.charAt(x) ^ KEY);
        }
        return decode;
    }

    public static void main(String[] args) {

        String message = "Шифр Цезаря";

        System.out.println("Исходное сообщение: " + message);
        System.out.println("Ключ шифрования: " + KEY);
        System.out.println("Зашифрованное сообщение: " + encode(message));
        System.out.println("Дешифрованное сообщение: " + decode(message));
    }
}