# CRC Tx
Programa que simula el transmisor del "Cyclic Redundancy Check" implementando MVP y Dagger Hilt.

## Qué es? :thinking:
Es un codigo de detección de errores comúnmente usado en redes digitales y en almacenamiento de dispositivos usado para detectar cambios accidentales en los datos enviados.

## MVP :wrench:
El patrón de arquitectura o patrón de presentación Model-View-Presenter es una técnica de desacoplamiento en la que se separan las funcionalidades de una aplicación por medio de 3 "capas":
* **Model**: Es la capa que se encarga de manejar los datos, obtenerlos y proporcionarlos. Puede ser el acceso a una BD o una API por ejemplo. Sólo tiene comunicación con el presenter.

* **Presenter**: Es la capa que se encarga de comunicar con el modelo, obtener los datos, realizar la lógica del negocio y actualizar la vista.

* **View**: Es la capa que se encarga de manejar lo que el usuario ve, usualmente es un Activity o Fragment. Sólo tiene comunicación con el presenter.

## [Dagger Hilt](https://dagger.dev/hilt/) :crossed_swords:
La biblioteca de inyección de dependencias basada en el framework [Dagger 2](https://dagger.dev/).
