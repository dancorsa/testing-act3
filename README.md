# Taller de Pruebas Unitarias — Registraduría

Proyecto académico desarrollado con **Test-Driven Development (TDD)** para la asignatura *Testing y Validación de Software* — Universidad de La Sabana.

El sistema implementa un servicio de registro de votantes que aplica reglas de negocio sobre los datos de una persona para determinar si puede ser inscrita.

---

## Integrantes

| Nombre |
|--------|
| Danilo Andres Cortes Saavedra |

---

## Tecnologías

| Herramienta | Versión | Uso |
|-------------|---------|-----|
| Java | 8+ | Lenguaje principal |
| Maven | 3.6+ | Gestión de dependencias y ciclo de vida |
| JUnit | 4.13.2 | Framework de pruebas unitarias |
| JaCoCo | 0.8.12 | Medición de cobertura de código |
| Maven Surefire | 3.2.5 | Ejecución de pruebas con Maven |

---

## Estructura del proyecto

```
src/
├── main/java/edu/unisabana/tyvs/domain/
│   ├── model/
│   │   ├── Person.java           — entidad principal del votante
│   │   ├── Gender.java           — enum: MALE, FEMALE, UNIDENTIFIED
│   │   └── RegisterResult.java   — enum: VALID, DUPLICATED, INVALID, DEAD, UNDERAGE
│   └── service/
│       └── Registry.java         — lógica de negocio del registro
└── test/java/edu/unisabana/tyvs/domain/service/
    └── RegistryTest.java         — 11 pruebas unitarias
```

---

## Reglas de negocio

| Condición | Resultado |
|-----------|-----------|
| `person == null` | `INVALID` |
| Persona fallecida (`alive = false`) | `DEAD` |
| Edad negativa o mayor a 120 | `INVALID` |
| Edad entre 0 y 17 años | `UNDERAGE` |
| ID ya registrado | `DUPLICATED` |
| Vivo, 18 ≤ edad ≤ 120, ID nuevo | `VALID` |

---

## Ejecución

```bash
# Compilar y ejecutar todas las pruebas
mvn clean test

# Generar reporte de cobertura JaCoCo
mvn clean test
# El reporte queda en: target/site/jacoco/index.html
```

---

## Resultados de cobertura

| Métrica | Cobertura |
|---------|-----------|
| Instrucciones | **96%** |
| Ramas | **100%** |
| Líneas | **93%** |
| Clases | **100%** |

> Cobertura mínima requerida por la guía: **80%**

---

## Documentación

La documentación completa del taller está en la [Wiki del repositorio](../../wiki):

- [Inicio](../../wiki/Home) — descripción del dominio y alcance
- [TDD](../../wiki/TDD) — 3 ciclos Red → Green → Refactor documentados
- [Patrón AAA](../../wiki/Patron-AAA) — estructura Arrange–Act–Assert con ejemplos
- [Clases de Equivalencia](../../wiki/Clases-de-Equivalencia) — tabla de particiones y valores límite
- [Escenarios BDD](../../wiki/Escenarios-BDD) — Given–When–Then para cada caso
- [Resultados JaCoCo](../../wiki/Resultados-JaCoCo) — reporte de cobertura con capturas
- [Reflexión Final](../../wiki/Reflexion-Final) — escenarios no cubiertos y mejoras propuestas

---

## Defectos detectados

Durante el desarrollo TDD se detectaron 2 defectos documentados en [`defectos.md`](defectos.md):

| ID | Descripción | Estado |
|----|-------------|--------|
| DEF-001 | `NullPointerException` al registrar persona nula | Resuelto |
| DEF-002 | Edad negativa clasificada como `UNDERAGE` en vez de `INVALID` | Resuelto |

---

## Licencia

Uso educativo — Universidad de La Sabana.
