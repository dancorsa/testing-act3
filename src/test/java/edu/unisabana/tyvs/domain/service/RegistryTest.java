package edu.unisabana.tyvs.domain.service;

import edu.unisabana.tyvs.domain.model.Gender;
import edu.unisabana.tyvs.domain.model.Person;
import edu.unisabana.tyvs.domain.model.RegisterResult;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegistryTest {

    private Registry registry;

    @Before
    public void setUp() {
        registry = new Registry();
    }

    // =========================================================
    // CICLO TDD #1 — Red: falla / Green: implementación mínima
    // =========================================================

    // Given: una persona adulta viva con datos válidos
    // When:  se intenta registrar
    // Then:  el resultado debe ser VALID
    @Test
    public void shouldReturnValidWhenPersonIsAliveAndAdult() {
        // Arrange
        Person person = new Person("Juan Perez", "123456789", 30, Gender.MALE, true);

        // Act
        RegisterResult result = registry.register(person);

        // Assert
        assertEquals(RegisterResult.VALID, result);
    }

    // =========================================================
    // CICLO TDD #2 — persona fallecida
    // =========================================================

    // Given: una persona fallecida
    // When:  se intenta registrar
    // Then:  el resultado debe ser DEAD
    @Test
    public void shouldReturnDeadWhenPersonIsNotAlive() {
        // Arrange
        Person person = new Person("Maria Lopez", "987654321", 45, Gender.FEMALE, false);

        // Act
        RegisterResult result = registry.register(person);

        // Assert
        assertEquals(RegisterResult.DEAD, result);
    }

    // =========================================================
    // CICLO TDD #3 — persona nula
    // =========================================================

    // Given: una referencia null en lugar de persona
    // When:  se intenta registrar
    // Then:  el resultado debe ser INVALID
    @Test
    public void shouldReturnInvalidWhenPersonIsNull() {
        // Arrange
        Person person = null;

        // Act
        RegisterResult result = registry.register(person);

        // Assert
        assertEquals(RegisterResult.INVALID, result);
    }

    // =========================================================
    // CLASES DE EQUIVALENCIA — valores límite de edad
    // =========================================================

    // Given: persona viva con edad exactamente 18 (límite inferior válido)
    // When:  se intenta registrar
    // Then:  el resultado debe ser VALID
    @Test
    public void shouldReturnValidAtMinimumAge18() {
        // Arrange
        Person person = new Person("Ana Perez", "200000001", 18, Gender.FEMALE, true);

        // Act
        RegisterResult result = registry.register(person);

        // Assert
        assertEquals(RegisterResult.VALID, result);
    }

    // Given: persona viva con edad exactamente 120 (límite superior válido)
    // When:  se intenta registrar
    // Then:  el resultado debe ser VALID
    @Test
    public void shouldReturnValidAtMaximumAge120() {
        // Arrange
        Person person = new Person("Elder Person", "200000002", 120, Gender.UNIDENTIFIED, true);

        // Act
        RegisterResult result = registry.register(person);

        // Assert
        assertEquals(RegisterResult.VALID, result);
    }

    // Given: persona viva con edad 17 (límite superior de menores)
    // When:  se intenta registrar
    // Then:  el resultado debe ser UNDERAGE
    @Test
    public void shouldReturnUnderageAt17() {
        // Arrange
        Person person = new Person("Carlos Menor", "200000003", 17, Gender.MALE, true);

        // Act
        RegisterResult result = registry.register(person);

        // Assert
        assertEquals(RegisterResult.UNDERAGE, result);
    }

    // Given: persona viva con edad 0 (recién nacido)
    // When:  se intenta registrar
    // Then:  el resultado debe ser UNDERAGE
    @Test
    public void shouldReturnUnderageWhenAgeIsZero() {
        // Arrange
        Person person = new Person("Bebe Perez", "200000004", 0, Gender.UNIDENTIFIED, true);

        // Act
        RegisterResult result = registry.register(person);

        // Assert
        assertEquals(RegisterResult.UNDERAGE, result);
    }

    // Given: persona viva con edad 121 (supera el límite máximo)
    // When:  se intenta registrar
    // Then:  el resultado debe ser INVALID
    @Test
    public void shouldReturnInvalidWhenAgeExceeds120() {
        // Arrange
        Person person = new Person("Muy Mayor", "200000005", 121, Gender.MALE, true);

        // Act
        RegisterResult result = registry.register(person);

        // Assert
        assertEquals(RegisterResult.INVALID, result);
    }

    // Given: persona con edad negativa
    // When:  se intenta registrar
    // Then:  el resultado debe ser INVALID
    @Test
    public void shouldReturnInvalidWhenAgeIsNegative() {
        // Arrange
        Person person = new Person("Edad Invalida", "200000006", -1, Gender.MALE, true);

        // Act
        RegisterResult result = registry.register(person);

        // Assert
        assertEquals(RegisterResult.INVALID, result);
    }

    // =========================================================
    // CLASE DE EQUIVALENCIA — ID duplicado
    // =========================================================

    // Given: una persona ya registrada
    // When:  se intenta registrar con el mismo ID
    // Then:  el resultado debe ser DUPLICATED
    @Test
    public void shouldReturnDuplicatedWhenIdAlreadyRegistered() {
        // Arrange
        Person first  = new Person("Pedro Ramirez", "300000001", 25, Gender.MALE, true);
        Person second = new Person("Pedro Ramirez", "300000001", 25, Gender.MALE, true);
        registry.register(first);

        // Act
        RegisterResult result = registry.register(second);

        // Assert
        assertEquals(RegisterResult.DUPLICATED, result);
    }

    // =========================================================
    // CLASE DE EQUIVALENCIA — dos personas distintas
    // =========================================================

    // Given: dos personas adultas vivas con diferentes IDs
    // When:  se registran en secuencia
    // Then:  ambas deben retornar VALID
    @Test
    public void shouldAllowTwoDifferentPersonsToRegister() {
        // Arrange
        Person personA = new Person("Laura Gomez",  "400000001", 28, Gender.FEMALE, true);
        Person personB = new Person("Diego Torres", "400000002", 33, Gender.MALE,   true);

        // Act
        RegisterResult resultA = registry.register(personA);
        RegisterResult resultB = registry.register(personB);

        // Assert
        assertEquals(RegisterResult.VALID, resultA);
        assertEquals(RegisterResult.VALID, resultB);
    }
}
