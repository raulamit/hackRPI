package com.example.uremote.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class PersonDAO extends AbstractDAO<com.example.uremote.core.Person> {
    public PersonDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<com.example.uremote.core.Person> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public com.example.uremote.core.Person create(com.example.uremote.core.Person person) {
        return persist(person);
    }

    public List<com.example.uremote.core.Person> findAll() {
        return list(namedQuery("Person.findAll"));
    }
}
