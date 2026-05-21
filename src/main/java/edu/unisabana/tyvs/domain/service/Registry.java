package edu.unisabana.tyvs.domain.service;

import edu.unisabana.tyvs.domain.model.Person;
import edu.unisabana.tyvs.domain.model.RegisterResult;

import java.util.HashSet;
import java.util.Set;

public class Registry {

    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 120;

    private final Set<String> registeredIds = new HashSet<>();

    public RegisterResult register(Person person) {
        if (person == null) {
            return RegisterResult.INVALID;
        }
        if (!person.isAlive()) {
            return RegisterResult.DEAD;
        }
        if (person.getAge() < 0 || person.getAge() > MAX_AGE) {
            return RegisterResult.INVALID;
        }
        if (person.getAge() < MIN_AGE) {
            return RegisterResult.UNDERAGE;
        }
        if (registeredIds.contains(person.getId())) {
            return RegisterResult.DUPLICATED;
        }
        registeredIds.add(person.getId());
        return RegisterResult.VALID;
    }
}
