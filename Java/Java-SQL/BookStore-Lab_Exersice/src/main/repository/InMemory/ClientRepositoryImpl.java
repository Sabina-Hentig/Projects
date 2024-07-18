package main.repository.InMemory;

import main.domain.Client;
import main.domain.validators.Validator;

public class ClientRepositoryImpl extends InMemoryRepository<Long, Client> {
    public ClientRepositoryImpl(Validator<Client> clientValidator) {
        super(clientValidator);

    }
}

