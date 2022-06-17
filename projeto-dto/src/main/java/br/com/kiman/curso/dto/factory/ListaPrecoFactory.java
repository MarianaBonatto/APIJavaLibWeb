package br.com.kiman.curso.dto.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.kiman.curso.dominio.entity.Cliente;
import br.com.kiman.curso.dominio.entity.ListaPreco;
import br.com.kiman.curso.dto.ClienteDTO;

public class ListaPrecoFactory {

	public static List<ListaPreco> toEntity(ClienteDTO clienteDTO) {
		List<ListaPreco> listaPreco = new ArrayList<ListaPreco>();
		if (clienteDTO != null && clienteDTO.getProdutos() != null) {
			Cliente cliente = ClienteFactory.toEntity(clienteDTO);
			clienteDTO.getProdutos().forEach(produto -> {
				ListaPreco preco = new ListaPreco();
				preco.setCliente(cliente);
				preco.setProduto(ProdutoFactory.toEntity(produto));
				preco.setPreco(produto.getPreco());
				listaPreco.add(preco);
			});
		}
		return listaPreco;
	}

	public static ClienteDTO toDTO(Cliente cliente, List<ListaPreco> listaPreco) {
		ClienteDTO dto = ClienteFactory.toDTO(cliente);
		dto.setProdutos(listaPreco.stream().map(lp -> ProdutoFactory.toDTO(lp.getProduto(), lp.getPreco()))
				.collect(Collectors.toList()));
		return dto;
	}

}
