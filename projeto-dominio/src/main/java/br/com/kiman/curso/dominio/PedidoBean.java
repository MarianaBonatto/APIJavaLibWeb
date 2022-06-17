package br.com.kiman.curso.dominio;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.kiman.curso.dominio.dao.ClienteDAO;
import br.com.kiman.curso.dominio.dao.ListaPrecoDAO;
import br.com.kiman.curso.dominio.dao.PedidoDAO;
import br.com.kiman.curso.dominio.dao.ProdutoDAO;
import br.com.kiman.curso.dominio.entity.Cliente;
import br.com.kiman.curso.dominio.entity.ItemPedido;
import br.com.kiman.curso.dominio.entity.ListaPreco;
import br.com.kiman.curso.dominio.entity.Pedido;
import br.com.kiman.curso.dominio.entity.Produto;
import br.com.kiman.curso.dominio.entity.id.ListaPrecoID;
import br.com.kiman.curso.dominio.exception.DominioException;
import br.com.kiman.curso.dominio.filtro.PedidoFiltro;

@Stateless
public class PedidoBean {

	public static final String JNDI_NAME = "java:app/projeto-dominio/PedidoBean";

	@Inject
	private PedidoDAO pedidoDAO;
	@Inject
	private ClienteDAO clienteDAO;
	@Inject
	private ProdutoDAO produtoDAO;
	@Inject
	private ListaPrecoDAO listaPrecoDAO;

	public List<Pedido> buscaPor(PedidoFiltro pedidoFiltro) {
		return pedidoDAO.buscaPor(pedidoFiltro);
	}

	public Pedido buscaPorId(Integer idPedido) {
		return pedidoDAO.buscaPorId(idPedido);
	}

	public Pedido criaPedido(Cliente cliente, List<ItemPedido> itens) {
		Cliente clienteDB = validaCliente(cliente);
		Pedido pedido = new Pedido();
		pedido.setCliente(clienteDB);
		pedido.setDataPedido(LocalDate.now());
		pedido.setDataEntrega(LocalDate.now().plusDays(4));
		pedido = pedidoDAO.salvar(pedido);

		return salvaItens(pedido, itens);
	}

	private Pedido salvaItens(Pedido pedido, List<ItemPedido> itens) {
		Cliente cliente = pedido.getCliente();
		if (itens == null || itens.isEmpty()) {
			throw new DominioException("Necessário os itens do pedido.");
		}
		itens.forEach(i -> {
			Produto produto = i.getProduto();
			Produto produtoDB = null;
			if (produto != null && produto.getId() != null) {
				produtoDB = produtoDAO.buscaPorId(produto.getId());
				if (produtoDB == null) {
					throw new DominioException("Produto inválido");
				}
			}
			ListaPreco listaPreco = listaPrecoDAO.buscaPorId(new ListaPrecoID(cliente, produto));
			Double preco = 0d;
			if (listaPreco != null) {
				preco = listaPreco.getPreco();
			}
			pedido.novoItem(produtoDB, i.getQuantidade(), preco);

		});
		return pedidoDAO.salvar(pedido);
	}

	private Cliente validaCliente(Cliente cliente) {
		if (cliente == null || cliente.getId() == null) {
			throw new DominioException("Necessário informar o cliente.");
		}
		Cliente clienteDB = clienteDAO.buscaPorId(cliente.getId());
		if (clienteDB == null) {
			throw new DominioException("Cliente não encontrado.");
		}
		return clienteDB;
	}

}
