# CRC Tx
Programa que simula el transmisor del "Cyclic Redundancy Check" implementando MVP y Dagger Hilt.

<<<<<<< HEAD
## ¿Qué es? :thinking:
Es un codigo de detección de errores comúnmente usado en redes digitales y en almacenamiento de dispositivos aplicado para detectar cambios accidentales en los datos enviados.

## Herramientas destacables :wrench:
* :bar_chart: **MVP**: Es un patrón de arquitectura/presentación que se utiliza para desacoplar la lógica del negocio de la vista.
* :dagger: **[Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=es-419)**: Herramienta para inyección de dependencias.

<img src="https://user-images.githubusercontent.com/36385394/186287196-261b83d3-0d38-47eb-9fc5-9084935fe209.jpeg" width="30%" height="20%"> <img src="https://user-images.githubusercontent.com/36385394/186287200-4eb9baca-4c53-4b04-81fc-6fc6cf5c9135.png">
=======
## Qué es? :thinking:
Es un codigo de detección de errores comúnmente usado en redes digitales y en almacenamiento de dispositivos usado para detectar cambios accidentales en los datos enviados.

## MVP :wrench:
El patrón de arquitectura o patrón de presentación Model-View-Presenter es una técnica de desacoplamiento en la que se separan las funcionalidades de una aplicación por medio de 3 "capas":
* **Model**: Es la capa que se encarga de manejar los datos, obtenerlos y proporcionarlos. Puede ser el acceso a una BD o una API por ejemplo. Sólo tiene comunicación con el presenter.

* **Presenter**: Es la capa que se encarga de comunicar con el modelo, obtener los datos, realizar la lógica del negocio y actualizar la vista.

* **View**: Es la capa que se encarga de manejar lo que el usuario ve, usualmente es un Activity o Fragment. Sólo tiene comunicación con el presenter.

## [Dagger Hilt](https://dagger.dev/hilt/) :crossed_swords:
La biblioteca de inyección de dependencias basada en el framework [Dagger 2](https://dagger.dev/).
>>>>>>> 807d074597ffbcdbbc07b32f5606c14476e21e53
