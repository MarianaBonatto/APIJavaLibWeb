package br.com.kiman.curso.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import br.com.kiman.curso.dominio.dao.ListaPrecoDAO;
import br.com.kiman.curso.dominio.dao.ProdutoDAO;
import br.com.kiman.curso.dominio.entity.Produto;
import br.com.kiman.curso.dominio.exception.DominioException;
import br.com.kiman.curso.dominio.exception.ErroInfo;
import br.com.kiman.curso.dominio.exception.ValidacaoException;

@Stateless
public class ProdutoBean {

	public static final String JNDI_NAME = "java:app/projeto-dominio/ProdutoBean";

	@Inject
	private Validator validator;
	@Inject
	private ProdutoDAO produtoDAO;
	@Inject
	private ListaPrecoDAO listaPrecoDAO;

	public List<Produto> buscaTodos() {
		return produtoDAO.buscaTodos();

	}

	public Produto buscaPorId(Integer idProduto) {
		Produto produto = produtoDAO.buscaPorId(idProduto);
		if (produto == null) {
			throw new DominioException("Produto não encontrado.");
		}
		return produto;
	}

	public Produto salvaProduto(Produto produto) {
		valida(produto);

		return produtoDAO.salvar(produto);
	}

	private void valida(Produto produto) {
		if (produto == null) {
			throw new DominioException("Produto não pode ser nulo.");
		}
		if (produto.getId() != null) {
			buscaPorId(produto.getId());
		}
		Produto produtoBD = produtoDAO.buscaPor(produto.getDescricao());
		if (produtoBD != null && !produtoBD.getId().equals(produto.getId())) {
			throw new DominioException("Produto já cadastrado.");
		}

		List<ErroInfo> erros = new ArrayList<ErroInfo>();
		Set<ConstraintViolation<Produto>> validate = validator.validate(produto);
		for (ConstraintViolation<Produto> validation : validate) {
			erros.add(new ErroInfo(validation.getPropertyPath().toString(), validation.getMessage()));
		}
		if (!erros.isEmpty()) {
			throw new ValidacaoException(erros);
		}

	}

	public void remove(Integer idProduto) {
		Produto produto = buscaPorId(idProduto);
		if (listaPrecoDAO.existePrecoPara(produto)) {
			throw new DominioException("Não é possível excluir um produto com cliente associado.");
		}
		produtoDAO.remove(produto);
	}
}
