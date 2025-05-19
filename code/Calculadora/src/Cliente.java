// === Arquivo: Cliente.java ===
import java.rmi.Naming;

public class Cliente {
    public static void main(String[] args) {
        try {
            Calculadora calc = (Calculadora) Naming.lookup("rmi://localhost/CalculadoraService");
            System.out.println("5 + 3 = " + calc.somar(5, 3));
            System.out.println("10 - 4 = " + calc.subtrair(10, 4));
            System.out.println("6 * 7 = " + calc.multiplicar(6, 7));
            System.out.println("20 / 5 = " + calc.dividir(20, 5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
