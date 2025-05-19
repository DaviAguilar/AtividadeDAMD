// === Arquivo: CalculadoraImpl.java ===

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraImpl extends UnicastRemoteObject implements Calculadora {
    protected CalculadoraImpl() throws RemoteException {
        super();
    }

    public int somar(int a, int b) {
        return a + b;
    }

    public int subtrair(int a, int b) {
        return a - b;
    }

    public int multiplicar(int a, int b) {
        return a * b;
    }

    public double dividir(int a, int b) {
        if (b == 0) throw new ArithmeticException("Divisão por zero");
        return (double) a / b;
    }
}
