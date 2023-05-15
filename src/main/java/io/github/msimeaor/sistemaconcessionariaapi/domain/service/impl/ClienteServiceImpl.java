package io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl;

import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ClienteModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.repository.ClienteRepository;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {

  private final ClienteRepository clienteRepository;

  public boolean existsByCpf(String cpf) {
    return clienteRepository.existsByCpf(cpf);
  }

  public boolean existsByRg(String rg) {
    return clienteRepository.existsByRg(rg);
  }

  public boolean existsByEmail(String email) {
    return clienteRepository.existsByEmail(email);
  }

  @Transactional
  public ClienteModel save(ClienteModel clienteModel) {
    return clienteRepository.save(clienteModel);
  }

  public Optional<ClienteModel> getById(UUID id) {
    return clienteRepository.findById(id);
  }

  public Optional<ClienteModel> getByCpf(String cpf) {
    return clienteRepository.findByCpf(cpf);
  }

  @Transactional
  public void delete(ClienteModel clienteModel) {
    desvincularPedidosAoCliente(clienteModel);
    clienteRepository.delete(clienteModel);
  }

  private void desvincularPedidosAoCliente(ClienteModel clienteModel) {
    if (clienteModel.getListaPedidos().isEmpty()) {
      System.out.println("O CLIENTE NÃO POSSUI PEDIDOS!");
    } else {
      clienteModel.getListaPedidos().forEach(pedidoModel -> {
        pedidoModel.setClienteModel(null);
      });
    }
  }

}
