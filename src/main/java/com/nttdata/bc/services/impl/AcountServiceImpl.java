package com.nttdata.bc.services.impl;

import java.util.List;

import org.jboss.logging.Logger;

import com.nttdata.bc.exceptions.NotFoundException;
import com.nttdata.bc.models.Account;
import com.nttdata.bc.repositories.AccountRepository;
import com.nttdata.bc.services.IAccountService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AcountServiceImpl implements IAccountService {
    @Inject
    Logger logger;

    @Inject
    private AccountRepository repository;

    @Override
    public Account insert(Account obj) {
        // TODO: Obtener el tipo de producto
        // TODO: Validar para que tipo de producto se va registrar

        this.repository.persist(obj);
        return obj;
    }

    @Override
    public Account update(Account obj) {
        Account account = this.repository.findById(obj.getAccountId());
        if (account == null) {
            throw new NotFoundException("la cuenta con id: " + obj.getProductId() + ", no existe.");
        }

        return account;
    }

    @Override
    public List<Account> findAll() {
        return this.repository.listAll();
    }

    @Override
    public Account fintById(Integer id) {
        Account account = this.repository.findById(id);
        if (account == null) {
            throw new NotFoundException("La cuenta con id: " + id.toString() + ", no existe.");
        }

        return this.repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }
}
