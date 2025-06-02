# Encriptador 🔐

**Encriptador** es una aplicación en Java que permite codificar y decodificar textos mediante un sistema personalizado de cifrado.  
⚠️ Este proyecto tiene fines estrictamente académicos, por lo tanto, **no debe utilizarse para proteger información sensible o confidencial en contextos profesionales o reales.**

---

## 🚀 Características

1. Codifica mensajes en un formato "seguro":
   - Convierte cada carácter en un elemento de 13 dígitos.
   - Cada elemento contiene una clave y un orden seudoaleatorios, además de su posición y valor ASCII codificado.
   - La secuencia final organiza todos los elementos de forma seudoaleatoria.

3. Incluye el Incriptador.exe
   - Copia automaticamente el texto codificada.

---

## 📝 Notas

- Solo admite caracteres con códigos ASCII del **32 al 126** (alfabeto inglés, símbolos y caracteres comunes).

---

## 🔍 ¿Cómo funciona el cifrado y descifrado?

### 1. Codificación de cada carácter del texto:

- Se genera una **clave de dos dígitos** entre 22 y 99.
- Se obtiene la **posición del carácter** en el texto, representada como un número de 4 dígitos (0000 a 9999).
- Se extrae el **valor ASCII** del carácter.
- Se selecciona un **orden aleatorio** entre las permutaciones posibles de los tres valores: clave (1), posición (2) y valor codificado (3).
  - Ejemplo: si el orden es `"231"`, primero se coloca la posición, luego el valor codificado y finalmente la clave.
- El valor ASCII se transforma de la siguiente forma:
  - Se **multiplica por el primer dígito de la clave**.
  - Luego se **suma el segundo dígito** de la clave.
- El elemento final de 13 dígitos se compone así:
  - Los primeros **3 dígitos** representan el orden utilizado.
  - Luego, según ese orden, se concatenan:
    - La **clave** (2 dígitos)
    - La **posición** (4 dígitos)
    - El **valor codificado** (4 dígitos)
- Este proceso se repite para cada carácter del texto original.

### 2. Decodificación de cada elemento en la secuencia:

- Se valida que:
  - La longitud de la secuencia sea divisible por 13.
  - La secuencia contenga **solo caracteres numéricos**.
- Cada bloque de 13 dígitos se separa en:
  - **Orden de codificación**
  - **Clave**
  - **Posición**
  - **Valor codificado**
- Para recuperar el carácter original:
  - Se **resta el segundo dígito de la clave** al valor codificado.
  - Luego se **divide el resultado entre el primer dígito** de la clave (el resultado será un número entero correspondiente al valor ASCII original).
  - Se convierte este número en su carácter correspondiente.
  - El carácter se inserta en la posición correcta dentro del mensaje reconstruido.
- El proceso se repite con todos los bloques de la secuencia cifrada.

---

## 🧱 Estructura del proyecto (desde la carpeta `src`)

```plaintext
src/
│   Encriptador.java
│
├───Codificador
│       Codificador.java
│
└───Decodificador
        Decodificador.java
```

---

## 🧑‍💻 Autor
> Jonathan (MrFraullen) – github.com/MrFraullen

--

## 📄 Licencia
Este proyecto está licenciado bajo los términos de la Licencia MIT Académica No Comercial.
Puedes usar, modificar y compartir este software únicamente con fines académicos o personales no comerciales.

> El uso comercial, la redistribución o la venta están prohibidos sin autorización escrita del autor.

Consulta el archivo LICENSE para ver los términos completos en español e inglés.