# Incriptador 🔐

Incriptador es una aplicación en Java que permite codificar y decodificar textos usando un sistema personalizado de cifrado. Es un proyecto academico por lo que no debe ser usar para
incriptar información privada de forma profesional.

---

## 🚀 Características

1. Codifica mensajes en un formato "seguro".
    - Codifica cada caracter como un elemento de 13 numeros.
    - Cada elemento contiene una clave y orden seudoaleatorios, además de su orden y valor ASCII codificado.
    - En la secuencia final organiza los diferentes elementos de forma seudoaleatoria.
2. Decodifica mensajes previamente codificados mediante el metodo personalizado.
   - Verifica que la secuencia a decodificar sea valida.
   - Verficia que los subelementos de cada elemento sean validos.
   - Decodifica cada elemento de la secuencia y devuelve el texto decodificado.

3. Incluye el .exe para ejecutar sin la necesidad de tener Java instalado.

---
## 🔍 ¿Cómo funciona el cifrado y descifrado?

### 1. Codificación de cada carácter del texto:

- Se genera una **clave de dos dígitos** entre 22 y 99.
- Se obtiene la **posición del carácter**, representada como un número de 4 dígitos (0000 a 9999).
- Se extrae el **valor ASCII** del carácter.
- Se elige un **orden aleatorio** entre las permutaciones posibles de los valores clave (1), posición (2) y valor ASCII (3).  
  - Por ejemplo, si el orden es "231", primero se ubicará la posición, luego el valor ASCII y finalmente la clave.
- El valor ASCII se transforma:  
  - Se **multiplica por el primer dígito de la clave**.  
  - Luego se **suma el segundo dígito** de la clave.
- El elemento final de 13 dígitos se compone así:
  - Los primeros **3 dígitos representan el orden** generado.
  - Luego, según ese orden, se colocan la clave (2 dígitos), la posición (4 dígitos) y el valor codificado (4 dígitos).
- El proceso se repite para todos los caracteres del texto original.

### 2. Decodificación de cada elemento en la secuencia:

- Se valida que:
  1. La secuencia tenga una longitud divisible por 13.
  2. La secuencia contenga **solo caracteres numéricos**.
- Cada elemento se separa en:
  - **Orden de codificación**
  - **Clave**
  - **Posición**
  - **Valor codificado**
- El valor codificado se decodifica así:
  - Se **resta el segundo dígito de la clave**.
  - Se **divide entre el primer dígito** de la clave (el resultado siempre será entero).
  - Se obtiene el **valor ASCII original**.
- El carácter correspondiente al ASCII se inserta en su posición.
- Se repite el proceso con todos los elementos.

---

## 🧱 Estructura del proyecto desde el src

```plaintext
src/
│   Incriptador.java
│
├───Codificador
│       Codificador.java
│
└───Decodificador
        Decodificador.java
```
---

## 🧑‍💻 Autor

**Jonathan** *(MrFraullen)* – <a href="https://github.com/MrFraullen">github.com/MrFraullen</a>

---

## 📄 Licencia
Este proyecto está licenciado bajo los términos de la Licencia MIT Académica No Comercial.
Puedes usar, modificar y compartir este software únicamente con fines académicos o personales no comerciales.

> El uso comercial, redistribución o venta está prohibido sin autorización escrita del autor.

Consulta el archivo LICENSE para ver los términos completos en español e inglés.

