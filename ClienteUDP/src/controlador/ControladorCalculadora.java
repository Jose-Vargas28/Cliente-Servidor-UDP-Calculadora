package controlador;

import entidades.Cliente;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControladorCalculadora {

    @FXML
    private TextField txtn1;

    @FXML
    private TextField txtn2;

    @FXML
    private Label lblResult;

    private final Cliente cliente = new Cliente();

    private static final String HOST = "192.168.18.36"; // o localhost
    private static final int PUERTO = 5000;

    // OPERACIONES

    @FXML
    private void sumar() {
        operar(1);
    }

    @FXML
    private void restar() {
        operar(2);
    }

    @FXML
    private void multiplicar() {
        operar(3);
    }

    @FXML
    private void dividir() {
        operar(4);
    }

    // LÓGICA UDP

    private void operar(int op) {
        try {

            double n1 = Double.parseDouble(txtn1.getText());
            double n2 = Double.parseDouble(txtn2.getText());

            // División por cero
            if (op == 4 && n2 == 0) {
                lblResult.setText("ERROR: No existe división para cero");
                return;
            }

            // Hilo para NO congelar la interfaz
            new Thread(() -> {
                try {
                    String resultado =
                            cliente.enviar(HOST, PUERTO, op, n1, n2);

                    Platform.runLater(() ->
                            lblResult.setText("Resultado: " + resultado)
                    );

                } catch (Exception e) {
                    Platform.runLater(() ->
                            lblResult.setText("ERROR de conexión")
                    );
                }
            }).start();

        } catch (NumberFormatException e) {
            lblResult.setText("ERROR: Ingrese solo números");
        }
    }

    //  LIMPIAR

    @FXML
    private void limpiar() {
        txtn1.clear();
        txtn2.clear();
        lblResult.setText("Resultado");
    }
}