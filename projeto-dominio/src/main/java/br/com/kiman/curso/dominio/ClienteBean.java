package br.com.kiman.curso.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import br.com.kiman.curso.dominio.dao.ClienteDAO;
import br.com.kiman.curso.dominio.dao.PedidoDAO;
import br.com.kiman.curso.dominio.entity.Cliente;
import br.com.kiman.curso.dominio.entity.ListaPreco;
import br.com.kiman.curso.dominio.exception.DominioException;
import br.com.kiman.curso.dominio.exception.ErroInfo;
import br.com.kiman.curso.dominio.exception.ValidacaoException;

@Stateless
public class ClienteBean {

	public static final String JNDI_NAME = "java:app/projeto-dominio/ClienteBean";

	@Inject
	private Validator validator;
	@Inject
	private ClienteDAO clienteDAO;
	@Inject
	private PedidoDAO pedidoDAO;
	@Inject
	private ListaPrecoBean listaPrecoBean;

	public List<Cliente> buscaTodos() {
		return clienteDAO.buscaTodos();

	}

	public Cliente buscaPorId(Integer idCliente) {
		Cliente cliente = clienteDAO.buscaPorId(idCliente);
		if (cliente == null) {
			throw new DominioException("Cliente não encontrado.");
		}
		return cliente;
	}

	public Cliente salva(Cliente cliente) {
		valida(cliente);

		return clienteDAO.salvar(cliente);
	}

	private void valida(Cliente cliente) {
		if (cliente == null) {
			throw new DominioException("Cliente não pode ser nulo.");
		}

		if (cliente.getId() != null) {
			buscaPorId(cliente.getId());
		}

		Cliente clienteBD = clienteDAO.buscaPor(cliente.getNome());
		if (clienteBD != null && !clienteBD.getId().equals(cliente.getId())) {
			throw new DominioException("Cliente já cadastrado.");
		}

		List<ErroInfo> erros = new ArrayList<ErroInfo>();

		Set<ConstraintViolation<Cliente>> validate = validator.validate(cliente);
		for (ConstraintViolation<Cliente> validation : validate) {
			erros.add(new ErroInfo(validation.getPropertyPath().toString(), validation.getMessage()));
		}
		if (!erros.isEmpty()) {
			throw new ValidacaoException(erros);
		}

	}

	public void remove(Integer idCliente) {
		Cliente cliente = buscaPorId(idCliente);
		if (pedidoDAO.existePedidoPara(cliente)) {
			throw new DominioException("Não é possível excluir um cliente que possui pedido.");
		}
		listaPrecoBean.removePrecosDo(cliente);
		clienteDAO.remove(cliente);
	}

	public Cliente salva(Cliente cliente, List<ListaPreco> listaPreco) {
		Cliente clienteDB = salva(cliente);
		Optional.ofNullable(listaPreco).ifPresent(lp -> lp.forEach(preco -> preco.setCliente(clienteDB)));
		listaPrecoBean.salva(listaPreco);
		return clienteDB;
	}

}