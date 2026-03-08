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
        return clientesDAO.creaCliente(c);
    }

    @Override
    public Cliente bajaCliente(String dni) throws OperacionNoValida, DataAccessException {
        Cliente c = clientesDAO.cliente(dni);
        if (c == null) {
            return null;
        }
        if (!c.getSeguros().isEmpty()) {
            throw new OperacionNoValida("El cliente tiene seguros a su nombre");
        }
        return clientesDAO.eliminaCliente(dni);
    }

    // IGestionSeguros
    @Override
    public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
        // Verificar que el cliente existe
        Cliente c = clientesDAO.cliente(dni);
        if (c == null) {
            return null;
        }
        // Verificar que la matricula no existe ya
        Seguro existente = segurosDAO.seguroPorMatricula(s.getMatricula());
        if (existente != null) {
            throw new OperacionNoValida("Ya existe un seguro con esa matricula");
        }
        // Crear el seguro y asociarlo al cliente
        Seguro creado = segurosDAO.creaSeguro(s);
        c.getSeguros().add(creado);
        clientesDAO.actualizaCliente(c);
        return creado;
    }

    @Override
    public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
        // Verificar que el cliente existe
        Cliente c = clientesDAO.cliente(dni);
        if (c == null) {
            return null;
        }
        // Verificar que el seguro existe
        Seguro s = segurosDAO.seguroPorMatricula(matricula);
        if (s == null) {
            return null;
        }
        // Verificar que el seguro pertenece al cliente
        if (!c.getSeguros().contains(s)) {
            throw new OperacionNoValida("El seguro no pertenece al cliente indicado");
        }
        // Eliminar el seguro y actualizar el cliente
        c.getSeguros().remove(s);
        clientesDAO.actualizaCliente(c);
        return segurosDAO.eliminaSeguro(s.getId());
    }

    @Override
    public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
        Seguro s = segurosDAO.seguroPorMatricula(matricula);
        if (s == null) {
            return null;
        }
        s.setConductorAdicional(conductor);
        return segurosDAO.actualizaSeguro(s);
    }

    // IInfoSeguros
    @Override
    public Cliente cliente(String dni) throws DataAccessException {
        return clientesDAO.cliente(dni);
    }

    @Override
    public Seguro seguro(String matricula) throws DataAccessException {
        return segurosDAO.seguroPorMatricula(matricula);
    }
}
