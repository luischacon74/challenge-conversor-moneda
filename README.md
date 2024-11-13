Challenge-Alura conversor de moneda

Para este ejericio tome la  api que consume tres parametros, el parametro de moneda local, moneda a la que se desea hacer el cambio y la cantidad de dinero.
Para realizar este ejercicio cree 5 Clases (), un Enumn y un record.
en el record estoy obteniendo los datos llamando a Moneda y a la cual le paso 5 valores o campos necesarios que defini y mas abajo tengo el constructor y
accedo al valor que necesito.

Clase ObtenerNombresMonedas:
en esta clase defino una url, en la cual se tienen todos los codigos de los paises que tiene la api, defino el metodo http apra realizar la consulta y en el response hago un map
para asi poder visualizar en forma de lista los datos de los codigos de paises que estan disponibles.
![image](https://github.com/user-attachments/assets/911c18b4-9a61-4f6b-a70d-e921a9112e58)

Clase Moneda:
En esta clase defino los parametros que voy a necesitar y defino su tipo de datos.
![image](https://github.com/user-attachments/assets/6e7e3058-8916-415b-a78a-f5b8e12cd293)

Clase Menu:
En esta clase defini el menu que deseaba tener mi programa, para lo cual di dos opciones una en ingles y otra en español, para la seleccion defino el scanner para el 
ingreso de los datos de mi menu, el menu lo coloque dentro de un ciclo while para que se ejecutara siempre o hasta que coloquen exit o salir, para el menu del idioma cree
con un switch cos casos para asi indicar las dos opcioes de ingles o español, tengo otra que se encarga de que se evite el ingreso a una opcion diferente a las posibles y un
catch para controlar el error.
para el segundo menu despues de seleccionar la opcion de idioma esta almacenado dentro de un ciclo if el cual dependiendo de la opcion de ingles o español indicara
mostrara el menu en ese idioma.
![image](https://github.com/user-attachments/assets/5e505999-5401-493b-ab8e-84dd3f59cd61)
Aqui tambien me encargo con un switch case de las opciones, en cada opcion llamo a la clase que necesito, si seleccionan la priemra opcion para consultar  los codigos de moneda
vpy y reviso lo disponible con un ciclo if e imprimo los datos, por cada uno de lo que se reecorre mediante un  ciclo for hago un print de la forma que deseo que se visualicen 
los datos.
![image](https://github.com/user-attachments/assets/3b5f7ca5-07eb-4b35-9add-7445d12ec6cd)

Aqui tambien defini para hacer la conversion de moneda la cual llamo a consumirTasaDeCambio para realizar dicho proceso, dentro de un ciclo do, defino los mensajes que deseo,
en el idioma que se dejo disponibles, tambien valido que el codigo que se ingrese sea valido con un ciclo if.
![image](https://github.com/user-attachments/assets/c34a08ad-752f-429a-b983-3401b35b5914)
para el ingreso del valor se valida que se un valor numerico no se permite el ingreso de un valor diferente,
![image](https://github.com/user-attachments/assets/42d71417-bf69-40ec-a9d7-7de06e6e1e59)
dentro de un try y un print le muestro al usuario el equivalente de la forma que yo deseo que se vea dicha conversion
![image](https://github.com/user-attachments/assets/bdb3f9f4-ce4c-4636-ae52-4ab9d11ac174)

Clase ConsumirTasDeCambio:
En esta clase me encargo de definir las url que voy a usar en este caso defini la url con los tres parametros que indique (moneda base, moneda de destino, cantidad o valor a cambiar)
tambien defino el metodo de http y su respuesta, dentro de un try catch para evitar errores que hagan que mi programa se corte o se detenga.
![image](https://github.com/user-attachments/assets/095e1102-9d9d-4e99-8ba7-0fa1e19e9d66)

Clase Main:
es donde llamo a mi clase Menu para comenzar a indicar al usuario las opciones ue tiene disponibles 
![image](https://github.com/user-attachments/assets/075675c0-167f-45d1-b991-34e9d9c7662a)

Clase Enum:
Aqui defino la opcion de español e ingles para mis idiomas que deseo tener como opciones, como son conjuntos de  cosntantes y no vana  estar cambiando, decidi crear esta clase y 
y almacenar dichos datos aqui.
![image](https://github.com/user-attachments/assets/6aad6f5e-ff7d-46ee-b400-3f10ea1160b7)


