
Reglas del Proyecto - LAB IV

Este repositorio sigue un flujo de trabajo basado en ramas para mantener la estabilidad del código y permitir un desarrollo estructurado. A continuación, se describen las reglas a seguir.

#  Estructura de Ramas

main: Contiene la versión final de cada Trabajo Práctico (TP). No se deben subir cambios directamente a esta rama.

master: Es la rama de desarrollo donde se suben los avances revisados y "sin bugs". No se deben realizar cambios directos en esta rama.

Ramas personales: Cada participante debe crear su propia rama basada en master para realizar cambios y avances. Estas ramas deben seguir el formato rama-nombre (ejemplo: rama-abraham).

# 🔄 Flujo de Trabajo

# 1️⃣ Clonar el repositorio 

Si aún no tienen el repositorio en la máquina:

- git clone -b master https://github.com/user52689/LAB-IV.git
- cd LAB-IV

# 2️⃣ Actualizar el repositorio local
Antes de comenzar a trabajar, asegurense de que master está actualizado:

- git checkout master
- git pull origin master

# 3️⃣ Crear una nueva rama desde master

Cada participante debe crear su propia rama para trabajar en una nueva funcionalidad o corrección:

- git checkout -b rama-nombre - ejemplo : git checkout -b rama-abraham

# 4️⃣ Hacer cambios y subirlos

Después de realizar modificaciones en los archivos:

- git add .
- git commit -m "Descripción clara del cambio realizado"
- git push origin rama-nombre

# 5️⃣ Yo me encargare de fusionar los cambios a master, o informar de la existencia de algun problema, que no permita hacerlo
