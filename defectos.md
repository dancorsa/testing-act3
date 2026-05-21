# Registro de Defectos — Taller Pruebas Unitarias

## Defecto #1

**ID:** DEF-001  
**Caso de prueba:** `shouldReturnInvalidWhenPersonIsNull`  
**Versión detectada:** v1.0-SNAPSHOT  
**Estado:** Resuelto

### Descripción
Al llamar `registry.register(null)`, el sistema lanzaba una `NullPointerException` en lugar de retornar `RegisterResult.INVALID`.

### Entradas
- `person = null`

### Resultado esperado
`RegisterResult.INVALID`

### Resultado real (antes de la corrección)
`NullPointerException` en `Registry.register()` al intentar acceder a `person.isAlive()`

### Causa probable
Falta de validación de nulo al inicio del método `register()` antes de acceder a cualquier campo de `Person`.

### Corrección aplicada
Se añadió como primera instrucción del método:
```java
if (person == null) {
    return RegisterResult.INVALID;
}
```

### Estado final
Resuelto — la prueba `shouldReturnInvalidWhenPersonIsNull` pasa correctamente.

---

## Defecto #2

**ID:** DEF-002  
**Caso de prueba:** `shouldReturnInvalidWhenAgeIsNegative`  
**Versión detectada:** v1.0-SNAPSHOT  
**Estado:** Resuelto

### Descripción
Una persona con edad `-1` era procesada como menor de edad (`UNDERAGE`) en lugar de ser rechazada como entrada inválida (`INVALID`), ya que la condición de edad negativa no estaba separada de la condición de menor de edad.

### Entradas
- `age = -1`, `alive = true`

### Resultado esperado
`RegisterResult.INVALID`

### Resultado real (antes de la corrección)
`RegisterResult.UNDERAGE`

### Causa probable
La validación de rango `(age < 0 || age > MAX_AGE)` estaba ausente; solo existía la validación `age < MIN_AGE`, que capturaba también edades negativas.

### Corrección aplicada
Se añadió una validación explícita de rango antes del chequeo de menor de edad:
```java
if (person.getAge() < 0 || person.getAge() > MAX_AGE) {
    return RegisterResult.INVALID;
}
if (person.getAge() < MIN_AGE) {
    return RegisterResult.UNDERAGE;
}
```

### Estado final
Resuelto — la prueba `shouldReturnInvalidWhenAgeIsNegative` pasa correctamente.

---

## Tabla resumen

| ID      | Caso de prueba                              | Entrada     | Esperado  | Real (bug)          | Estado   |
|---------|---------------------------------------------|-------------|-----------|---------------------|----------|
| DEF-001 | shouldReturnInvalidWhenPersonIsNull          | null        | INVALID   | NullPointerException| Resuelto |
| DEF-002 | shouldReturnInvalidWhenAgeIsNegative         | age = -1    | INVALID   | UNDERAGE            | Resuelto |
