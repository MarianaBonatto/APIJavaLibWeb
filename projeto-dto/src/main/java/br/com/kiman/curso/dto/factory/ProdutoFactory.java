package br.com.kiman.curso.dto.factory;

import br.com.kiman.curso.dominio.entity.Produto;
import br.com.kiman.curso.dto.ProdutoDTO;

public class ProdutoFactory {

	public static ProdutoDTO toDTO(Produto produto) {
		ProdutoDTO dto = new ProdutoDTO();
		dto.setDescricao(produto.getDescricao());
		dto.setIdProduto(produto.getId());
		return dto;
	}

	public static ProdutoDTO toDTO(Produto produto, Double preco) {
		ProdutoDTO dto = toDTO(produto);
		dto.setPreco(preco);
		return dto;
	}

	public static Produto toEntity(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setId(produtoDTO.getIdProduto());
		return produto;
	}

}
