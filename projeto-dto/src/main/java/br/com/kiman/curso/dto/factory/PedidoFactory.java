package br.com.kiman.curso.dto.factory;

import java.util.List;
import java.util.stream.Collectors;

import br.com.kiman.curso.dominio.entity.ItemPedido;
import br.com.kiman.curso.dominio.entity.Pedido;
import br.com.kiman.curso.dto.ItemPedidoDTO;
import br.com.kiman.curso.dto.PedidoDTO;

public class PedidoFactory {

	public static PedidoDTO toDTO(Pedido pedido) {
		PedidoDTO dto = new PedidoDTO();
		dto.setIdPedido(pedido.getId());
		dto.setDataPedido(pedido.getDataPedido());
		dto.setDataEntrega(pedido.getDataEntrega());
		dto.setCliente(ClienteFactory.toDTO(pedido.getCliente()));
		dto.setValorTotal(pedido.getValorTotal());
		dto.setItens(pedido.getItens().stream().map(i -> {
			ItemPedidoDTO itemDTO = new ItemPedidoDTO();
			itemDTO.setNumItem(i.getNumItem());
			itemDTO.setPreco(i.getPreco());
			itemDTO.setProduto(ProdutoFactory.toDTO(i.getProduto()));
			itemDTO.setQuantidade(i.getQuantidade());
			return itemDTO;
		}).collect(Collectors.toList()));
		return dto;
	}

	public static List<PedidoDTO> toDTO(List<Pedido> pedidos) {
		return pedidos.stream().map(p -> toDTO(p)).collect(Collectors.toList());
	}

	public static List<ItemPedido> toEntity(List<ItemPedidoDTO> itens) {
		if (itens == null) {
			return null;
		}
		return itens.stream().map(i -> {
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(i.getQuantidade());
			itemPedido.setProduto(ProdutoFactory.toEntity(i.getProduto()));
			return itemPedido;
		}).collect(Collectors.toList());
	}

}
