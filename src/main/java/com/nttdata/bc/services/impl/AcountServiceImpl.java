package com.nttdata.bc.services.impl;

import java.util.List;
import java.util.Optional;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.nttdata.bc.clients.IClientRestClient;
import com.nttdata.bc.exceptions.NotFoundException;
import com.nttdata.bc.models.Account;
import com.nttdata.bc.models.Client;
import com.nttdata.bc.repositories.AccountRepository;
import com.nttdata.bc.services.IAccountService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AcountServiceImpl implements IAccountService {
    @Inject
    Logger logger;

    // @Inject
    // @RestClient
    // IProductRestClient productRestClient;

    @Inject
    @RestClient
    IClientRestClient clientRestClient;

    @Inject
    private AccountRepository repository;

    @Override
    public Account insert(Account obj) {
        logger.info("entro");
        Client client = clientRestClient.fintById(obj.getClientId());
        // TODO: Validar si existe cliente

        List<Account> accounts = this.repository.list("clientId", obj.getClientId());
        logger.info("accounts ::: " + accounts);
        if (accounts.size() > 0) {
            Optional<Account> mainAccount = accounts.stream().filter(a -> a.getIsMain() == true)
                    .findFirst();

            if (mainAccount.isPresent() == true) {
                obj.setIsMain(false);
            }
        } else {
            obj.setIsMain(true);
        }

        obj.setIsActive(true);
        this.repository.persist(obj);

        return obj;
    }

    @Override
    public Account update(Account obj) {
        List<Account> accounts = this.repository.list("clientId", obj.getClientId());
        Account mainAccount = accounts.stream().filter(a -> a.getIsMain() == true)
                .findFirst()
                .get();

        if (mainAccount != null) {
            mainAccount.setIsMain(false);
        }

        Account account = this.findById(obj.getAccountId());
        account.setIsMain(obj.getIsMain());
        return account;
    }

    @Override
    public List<Account> findAll() {
        return this.repository.listAll();
    }

    @Override
    public Account findById(Integer id) {
        Account account = this.repository.findById(id);
        if (account == null) {
            throw new NotFoundException("La cuenta con id: " + id.toString() + ", no existe.");
        }

        return account;
    }

    @Override
    public void delete(Integer id) {
        Account account = this.findById(id);
        account.setIsActive(false);
    }
}
