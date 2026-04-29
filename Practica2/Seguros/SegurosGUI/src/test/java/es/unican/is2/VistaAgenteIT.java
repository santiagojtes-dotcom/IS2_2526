package es.unican.is2;

import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VistaAgenteIT {

    private FrameFixture window;
    private Robot robot;

    @BeforeEach
    public void setUp() throws Exception {
        if (H2ServerConnectionManager.connection != null) {
            H2ServerConnectionManager.connection.close();
            H2ServerConnectionManager.connection = null;
        }

        IClientesDAO daoClientes = new ClientesDAO();
        ISegurosDAO daoSeguros = new SegurosDAO();
        GestionSeguros negocio = new GestionSeguros(daoClientes, daoSeguros);

        robot = BasicRobot.robotWithNewAwtHierarchy();

        VistaAgente vista = GuiActionRunner.execute(
            () -> new VistaAgente(negocio, negocio)
        );
        window = new FrameFixture(robot, vista);
        window.show();
    }

    @AfterEach
    public void tearDown() throws Exception {
        window.cleanUp();
        if (H2ServerConnectionManager.connection != null) {
            H2ServerConnectionManager.connection.close();
            H2ServerConnectionManager.connection = null;
        }
    }

    // IT1 - Cliente existente con 3 seguros, sin minusvalía (Juan)
    @Test
    public void testConsultaClienteConSeguros() throws InterruptedException {
        window.textBox("txtDNICliente").setText("11111111A");
        window.button("btnBuscar").click();
        Thread.sleep(500);

        window.textBox("txtNombreCliente").requireText("Juan");
        window.textBox("txtTotalCliente").requireText("1278.0");
        window.list().requireItemCount(3);
    }

    // IT2 - Cliente existente sin seguros, con minusvalía (Luis)
    @Test
    public void testConsultaClienteSinSeguros() throws InterruptedException {
        window.textBox("txtDNICliente").setText("33333333A");
        window.button("btnBuscar").click();
        Thread.sleep(500);

        window.textBox("txtNombreCliente").requireText("Luis");
        window.textBox("txtTotalCliente").requireText("0.0");
        window.list().requireItemCount(0);
    }

    // IT3 - Cliente no existente en BBDD
    @Test
    public void testConsultaClienteNoExistente() throws InterruptedException {
        window.textBox("txtDNICliente").setText("99999999Z");
        window.button("btnBuscar").click();
        Thread.sleep(500);

        window.textBox("txtNombreCliente").requireText("Error en BBDD");
        window.textBox("txtTotalCliente").requireText("");
        window.list().requireItemCount(0);
    }

    // IT4 - DNI vacío (valor límite)
    @Test
    public void testConsultaDNIVacio() throws InterruptedException {
        window.button("btnBuscar").click();
        Thread.sleep(500);

        window.textBox("txtNombreCliente").requireText("Error en BBDD");
        window.textBox("txtTotalCliente").requireText("");
        window.list().requireItemCount(0);
    }

    // IT5 - Caja blanca: forzar DataAccessException (cubre rama catch)
    @Test
    public void testConsultaDataAccessException() throws Exception {
        IInfoSeguros infoFallida = new IInfoSeguros() {
            @Override
            public Cliente cliente(String dni) throws DataAccessException {
                throw new DataAccessException();
            }
            @Override
            public Seguro seguro(String matricula) throws DataAccessException {
                throw new DataAccessException();
            }
        };

        Robot robot2 = BasicRobot.robotWithNewAwtHierarchy();
        VistaAgente vistaFallida = GuiActionRunner.execute(
            () -> new VistaAgente(null, infoFallida)
        );
        FrameFixture windowFallida = new FrameFixture(robot2, vistaFallida);
        windowFallida.show();

        windowFallida.textBox("txtDNICliente").setText("11111111A");
        windowFallida.button("btnBuscar").click();
        Thread.sleep(500);

        windowFallida.textBox("txtNombreCliente").requireText("Error en BBDD");
        windowFallida.textBox("txtTotalCliente").requireText("");
        windowFallida.list().requireItemCount(0);

        windowFallida.cleanUp();
    }
}