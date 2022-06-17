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
import br.com.kiman.curso.dominio.dao.ListaPrecoDAO;
import br.com.kiman.curso.dominio.dao.ProdutoDAO;
import br.com.kiman.curso.dominio.entity.Cliente;
import br.com.kiman.curso.dominio.entity.ListaPreco;
import br.com.kiman.curso.dominio.entity.Produto;
import br.com.kiman.curso.dominio.exception.DominioException;
import br.com.kiman.curso.dominio.exception.ErroInfo;
import br.com.kiman.curso.dominio.exception.ValidacaoException;

@Stateless
public class ListaPrecoBean {

	public static final String JNDI_NAME = "java:app/projeto-dominio/ListaPrecoBean";
	@Inject
	private Validator validator;
	@Inject
	private ListaPrecoDAO listaPrecoDAO;
	@Inject
	private ClienteDAO clienteDAO;
	@Inject
	private ProdutoDAO produtoDAO;

	public List<ListaPreco> listaPrecoPor(Cliente cliente) {
		return listaPrecoDAO.listaPrecoPor(cliente);
	}

	public ListaPreco salva(ListaPreco listaPreco) {
		List<ErroInfo> erros = new ArrayList<ErroInfo>();

		Cliente cliente = clienteDAO.buscaPorId(listaPreco.getIdCliente());
		if (cliente == null) {
			throw new DominioException("Cliente invalido");
		}
		Produto produto = produtoDAO.buscaPorId(listaPreco.getIdProduto());
		if (produto == null) {
			throw new DominioException("Produto invalido");
		}

		Set<ConstraintViolation<ListaPreco>> validate = validator.validate(listaPreco);

		for (ConstraintViolation<ListaPreco> validation : validate) {
			erros.add(new ErroInfo(validation.getPropertyPath().toString(), validation.getMessage()));
		}
		if (!erros.isEmpty()) {
			throw new ValidacaoException(erros);
		}

		return listaPrecoDAO.salvar(listaPreco);
	}

	public void salva(List<ListaPreco> listaPreco) {
		Optional.ofNullable(listaPreco).ifPresent(lp -> lp.forEach(p -> {
			salva(p);
		}));
	}

	public void removePrecosDo(Cliente cliente) {
		listaPrecoDAO.removePrecosDo(cliente);
	}
}
