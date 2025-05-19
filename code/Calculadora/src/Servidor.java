// === Arquivo: Servidor.java ===
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servidor {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); // inicia o registry
            Calculadora calc = new CalculadoraImpl();
            Naming.rebind("CalculadoraService", calc);
            System.out.println("Servidor RMI ativo e aguardando conexões...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
