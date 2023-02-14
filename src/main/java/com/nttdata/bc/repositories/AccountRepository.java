package com.nttdata.bc.repositories;

import java.util.List;

import com.nttdata.bc.models.Account;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountRepository implements PanacheRepositoryBase<Account, Integer> {
}
