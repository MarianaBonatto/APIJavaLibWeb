package br.com.kiman.curso.dto.factory;

import br.com.kiman.curso.dominio.entity.Cliente;
import br.com.kiman.curso.dto.ClienteDTO;

public class ClienteFactory {

	public static ClienteDTO toDTO(Cliente cliente) {
		ClienteDTO dto = new ClienteDTO();
		dto.setIdCliente(cliente.getId());
		dto.setNome(cliente.getNome());
		return dto;
	}

	public static Cliente toEntity(ClienteDTO clienteDTO) {
		if (clienteDTO == null) {
			return null;
		}
		Cliente cliente = new Cliente();
		cliente.setNome(clienteDTO.getNome());
		cliente.setId(clienteDTO.getIdCliente());
		return cliente;
	}

}
