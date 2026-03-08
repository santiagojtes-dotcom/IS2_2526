package es.unican.is2;

import es.unican.is2.IClientesDAO;
import es.unican.is2.ISegurosDAO;
import es.unican.is2.IGestionClientes;
import es.unican.is2.IGestionSeguros;
import es.unican.is2.IInfoSeguros;
import es.unican.is2.Cliente;
import es.unican.is2.Seguro;
import es.unican.is2.DataAccessException;
import es.unican.is2.OperacionNoValida;

public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {

    private IClientesDAO clientesDAO;
    private ISegurosDAO segurosDAO;

    public GestionSeguros(IClientesDAO clientesDAO, ISegurosDAO segurosDAO) {
        this.clientesDAO = clientesDAO;
        this.segurosDAO = segurosDAO;
    }

    // IGestionClientes
    @Override
    public Cliente nuevoCliente(Cliente c) throws DataAccessException {
        // TODO: implementar
        return null;
    }

    @Override
    public Cliente bajaCliente(String dni) throws OperacionNoValida, DataAccessException {
        // TODO: implementar
        return null;
    }

    // IGestionSeguros
    @Override
    public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
        // TODO: implementar
        return null;
    }

    @Override
    public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
        // TODO: implementar
        return null;
    }

    @Override
    public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
        // TODO: implementar
        return null;
    }

    // IInfoSeguros
    @Override
    public Cliente cliente(String dni) throws DataAccessException {
        // TODO: implementar
        return null;
    }

    @Override
    public Seguro seguro(String matricula) throws DataAccessException {
        // TODO: implementar
        return null;
    }
}
