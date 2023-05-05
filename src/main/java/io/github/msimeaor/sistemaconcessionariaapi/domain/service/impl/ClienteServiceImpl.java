package io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl;

import io.github.msimeaor.sistemaconcessionariaapi.domain.repository.ClienteRepository;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {

  private final ClienteRepository clienteRepository;

}
