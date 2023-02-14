package com.nttdata.bc.services.impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import jakarta.ws.rs.InternalServerErrorException;

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
        if (accounts.size() > 0) {
            Optional<Account> mainAccount = accounts.stream().filter(a -> a.getIsMain() == true).findFirst();

            if (mainAccount.isPresent() == true) {
                obj.setIsMain(false);
            }
        } else {
            obj.setIsMain(true);
        }

        obj.setIsActive(true);
        obj.setCreatedAt(Instant.now());
        this.repository.persist(obj);

        return obj;
    }

    @Override
    public Account update(Account obj) {
        // List<Account> accounts = this.repository.list("clientId", obj.getClientId());
        // if (accounts.size() > 0) {
        // Account mainAccount = accounts.stream().filter(a -> a.getIsMain() ==
        // true).findFirst().get();

        // if (mainAccount != null) {
        // mainAccount.setIsMain(false);
        // }
        // }
        try {
            logger.info("INI ::: update ::: " + obj);
            Account account = this.findById(obj.getAccountId());
            logger.info("PASO findById ::: account ::: " + account);
            account.setAmount(obj.getAmount());
            account.setUpdateddAt(Instant.now());

            return account;
        } catch (Exception e) {
            logger.info("ERROR ::: " + e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    @Override
    public List<Account> findAll() {
        return this.repository.listAll();
    }

    @Override
    public Account findById(Integer id) {
        logger.info("INI ::: findById ::: " + id);
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

    @Override
    public List<Account> findDebitCardId(Integer debitCardId) {
        return this.repository.list("debitCard.debitCardId", debitCardId);
    }
}
