# Practica1-LFP

## Practica1 Fernando Rodríguez 202030542

Se uso el siguiente Automata Finito Deterministico(DFA) para el reconocimiento de tokens

| Estados  |      a...z      |  0...9 | '{','}','[' , ']' , ';' , ',' | '.' | ESPACIO |
|----------|:-------------:|:-----:|:---:|:-:|:-:|
| &#8594; A   | B | C | F | X | A |
| -B- | B | B | F | X | A |
| -C- | X | C | F | D | A |
| D   | X | E | F | X | A |
| -E- | X | E | F | X | A |
| -F- | B | C | F | X | A |
| X   | X | X | F | X | A |

*Los estados rodeados por guiones representan los estados aceptados
