//Martín Echenique 32774 - Mikael Curbelo 323677
//Versión 29/5

package sistema;

import interfaz.*;
import sistema.tad.ABB.ABB;
import sistema.tad.ABB.IABB;
import sistema.tad.ABB.NodoABB;
import sistema.tad.Cola.ColaDinamica;
import sistema.tad.Cola.ICola;
import sistema.tad.Grafo.Camino;
import sistema.tad.Grafo.Grafo;
import sistema.tad.Grafo.IGrafo;
import sistema.tad.Lista.ILista;
import sistema.tad.Lista.Lista;

import java.util.Comparator;

public class ImplementacionSistema implements Sistema  {

    //DATOS DE CIUDADES
    private int maxCiudades;
    private int cantidadCiudades;

    //ARBOLES PARA VIAJEROS
    private IABB<Viajero> abbCedulas;
    private IABB<Viajero> abbCorreos;
    private ABB<Viajero> abbViajeros;

    //ARBOLES POR CATEGORIA Y RANGO
    private IABB<Viajero>[] viajerosPorCategoria;
    private IABB<Viajero>[] viajerosPorRango;

    //GRAFO
    private IGrafo grafo;

    @Override
    public Retorno inicializarSistema(int maxCiudades) {
        Retorno ret = new Retorno();
        if(maxCiudades <= 4) {
            ret.resultado = Retorno.Resultado.ERROR_1;
            return ret;
        }
        this.maxCiudades = maxCiudades;
        this.cantidadCiudades = 0;

        //Comparadores para cedulas
        Comparator<Viajero> compCedula = (v1,v2) ->{
            String cedula1 = v1.getCedula().replace(".","").replace("-","");
            String cedula2 = v2.getCedula().replace(".","").replace("-","");
            if(cedula1.length()!=cedula2.length()){
                return Integer.compare(cedula1.length(), cedula2.length());
            }
            return cedula1.compareTo(cedula2);
        };

        //comparadores para correos
        Comparator<Viajero> compCorreo = (v1,v2) -> v1.getCorreo().compareTo(v2.getCorreo());

        //comparador por cedula primero (el mismo que arriba) y si empatan, por correo
        Comparator<Viajero> compCedulaCorreo = (v1,v2)->{
            String cedula1 = v1.getCedula().replace(".","").replace("-","");
            String cedula2 = v2.getCedula().replace(".","").replace("-","");
            if(cedula1.length()!=cedula2.length()){
                return Integer.compare(cedula1.length(), cedula2.length());
            }
            int compCed = cedula1.compareTo(cedula2);
            if(compCed!=0){
                return compCed;
            }
            //desempato por el correo
            return v1.getCorreo().compareTo(v2.getCorreo());
        };
        //inicializo arboles con los comparadores
        abbCedulas = new ABB<>(compCedula);
        abbCorreos = new ABB<>(compCorreo);
        abbViajeros = new ABB<>(compCedulaCorreo);

        //Creo array de ABB
        viajerosPorCategoria = new ABB[3];
        viajerosPorRango = new ABB[14];


        //Inicializo ABB dentro de los array
        for (int i = 0; i < viajerosPorCategoria.length; i++) {
            viajerosPorCategoria[i] = new ABB<>(compCedula);
        }

        for (int i = 0; i < viajerosPorRango.length; i++) {
            viajerosPorRango[i] = new ABB<>(compCedula);
        }

        //ciudades = new Lista<Ciudad>();
        //conexiones = new Lista<Conexion>();

        grafo = new Grafo(maxCiudades);

        ret.resultado = Retorno.Resultado.OK;
        return ret;
    }

    @Override
    public Retorno registrarViajero(String cedula, String nombre,
                                    String correo, int edad,
                                    Categoria categoria) {
        if(cedula==null || cedula.isBlank() ||
        nombre==null || nombre.isBlank() ||
        correo==null || correo.isBlank()
        || categoria == null){

            return Retorno.error1("ERROR 1");

        }
        if (!cedulaValidar(cedula)){
            return Retorno.error2("ERROR 2");

        }
        if(!correoValidar(correo)){
            return Retorno.error3("ERROR 3");

        }
        if (edad<0 || edad > 139){
            return Retorno.error4("ERROR 4");
        }
        Retorno buscarCedula = buscarViajeroPorCedula(cedula);
        if(buscarCedula.getResultado() == Retorno.Resultado.OK){
            return Retorno.error5("ERROR 5");
        }

        Retorno buscarCorreo = buscarViajeroPorCorreo(correo);
        if(buscarCorreo.getResultado() == Retorno.Resultado.OK){
            return Retorno.error6("ERROR 6");
        }



            Viajero viajero = new Viajero(cedula, nombre, correo, edad, categoria);
            abbCedulas.insertar(viajero);
            abbCorreos.insertar(viajero);
            abbViajeros.insertar(viajero);
            viajerosPorCategoria[categoria.getIndice()].insertar(viajero);
            viajerosPorRango[edad/10].insertar(viajero);
            return Retorno.ok();

    }

    private boolean cedulaValidar(String cedula){
        String regexCed = "^(\\d\\.\\d{3}\\.\\d{3}-\\d|\\d{3}\\.\\d{3}-\\d)$";
        return cedula.matches(regexCed);
    }

    private boolean correoValidar(String correo) {
        String regexCorreo = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return correo.matches(regexCorreo);
    }



    @Override
    public Retorno buscarViajeroPorCedula(String cedula) {
        Retorno ret = new Retorno();
        //valido null o vacio
        if(cedula == null || cedula.isBlank()) {
            ret.resultado = Retorno.Resultado.ERROR_1;
            return ret;
        }
        if(!cedulaValidar(cedula)) {
            ret.resultado = Retorno.Resultado.ERROR_2;
            return ret;
        }

        //busco en el abb de cedulas y creo un viajero auxiliar solo con la ci a buscar
        Viajero buscado = new Viajero(cedula, "", "",0,null);
        ABB<Viajero> arbol = (ABB<Viajero>) abbCedulas;
        NodoABB<Viajero> nodo = arbol.getRaiz();
        int pasos= 0;

        while(nodo != null) {
            pasos++;
            int comp = arbol.getComparador().compare(buscado, nodo.getDato());
            if(comp ==0){
                ret.resultado = Retorno.Resultado.OK;
                ret.valorInteger = pasos;
                ret.valorString  = nodo.getDato().toString();
                return ret;
            }else if(comp <0){
                nodo = nodo.getIzq();
            }else {
                nodo = nodo.getDer();
            }
        }

        ret.resultado = Retorno.Resultado.ERROR_3;
        ret.valorInteger = pasos;
        return ret;
    }

    @Override
    public Retorno buscarViajeroPorCorreo(String correo) {
        Retorno ret = new Retorno();
        if(correo == null || correo.isBlank()) {
            ret.resultado = Retorno.Resultado.ERROR_1;
            return ret;
        }
        if(!correoValidar(correo)) {
            ret.resultado = Retorno.Resultado.ERROR_2;
            return ret;
        }
        Viajero buscado = new Viajero("", "", correo,0,null);
        ABB<Viajero> arbol = (ABB<Viajero>) abbCorreos;
        NodoABB<Viajero> nodo = arbol.getRaiz();
        int elementosRecorridos = 0;

        while(nodo != null){
            elementosRecorridos++;
            int comp = arbol.getComparador().compare(buscado, nodo.getDato());
            if(comp ==0){
                ret.resultado = Retorno.Resultado.OK;
                ret.valorInteger = elementosRecorridos;
                ret.valorString  = nodo.getDato().toString();
                return ret;
            }else if (comp < 0){
                nodo = nodo.getIzq();
            }else{
                nodo = nodo.getDer();
            }
        }
        ret.resultado = Retorno.Resultado.ERROR_3;
        return ret;
    }

    //los listar no pueden terminar en |
    @Override
    public Retorno listarViajerosPorCedulaAscendente() {
        Retorno ret = new Retorno();
        //recorro y voy armando el resultado
        StringBuilder strBld = new StringBuilder();
        abbCedulas.inOrder(v ->{
            if(!strBld.isEmpty()) {
                strBld.append("|");
            }
                strBld.append(v.toString());
        });
        ret.resultado = Retorno.Resultado.OK;
        ret.valorString = strBld.toString();
        return ret;
    }

    @Override
    public Retorno listarViajerosPorCedulaDescendente() {
        Retorno ret = new Retorno();
        //recorro y voy armando el resultado
        StringBuilder strBld = new StringBuilder();
        abbCedulas.inOrderDesc(v ->{
            if(!strBld.isEmpty()) {
                strBld.append("|");
            }
            strBld.append(v.toString());
        });
        ret.resultado = Retorno.Resultado.OK;
        ret.valorString = strBld.toString();
        return ret;
    }

    @Override
    public Retorno listarViajerosPorCorreoAscendente() {
        Retorno ret = new Retorno();
        //recorro y vor armando el resultado
        StringBuilder strBld = new StringBuilder();
        abbCorreos.inOrder(v ->{
            if(!strBld.isEmpty()){
                strBld.append("|");
            }
            strBld.append(v.toString());
        });
        ret.resultado = Retorno.Resultado.OK;
        ret.valorString = strBld.toString();
        return ret;
    }

    @Override
    public Retorno listarViajerosPorCategoria(Categoria unaCategoria) {
        Retorno ret = new Retorno();
        //recorro y voy armando el resultado
        StringBuilder strBld = new StringBuilder();
        int indice = unaCategoria.getIndice();

        viajerosPorCategoria[indice].inOrder(viajero -> {
            if (!strBld.isEmpty()) {
                strBld.append("|");
            }
            strBld.append(viajero.toString());
        });

        ret.resultado = Retorno.Resultado.OK;
        ret.valorString = strBld.toString();
        return ret;
    }

    @Override
    public Retorno listarViajerosDeUnRangoAscendente(int rango) {
        Retorno ret = new Retorno();
        if(rango<0) return Retorno.error1("ERROR 1");
        if(rango>13) return Retorno.error2("ERROR 2");
        //recorro y voy armando el resultado
        StringBuilder strBld = new StringBuilder();

        viajerosPorRango[rango].inOrder(viajero -> {
            if (!strBld.isEmpty()) {
                strBld.append("|");
            }
            strBld.append(viajero.toString());
        });

        ret.resultado = Retorno.Resultado.OK;
        ret.valorString = strBld.toString();
        return ret;
    }

    @Override
    public Retorno registrarCiudad(String codigo, String nombre) {
        //Error 1 pasado de topa o alcanzado
        if(cantidadCiudades == maxCiudades) return Retorno.error1("ERROR 1");
        //Error 2 parametros nulos o vacios
        if(codigo==null || codigo.isBlank() || nombre==null || nombre.isBlank()) return Retorno.error2("ERROR 2");
        //Error 3 duplicado de codigo de ciudad
        if(grafo.buscarCiudad(codigo)!=null) return Retorno.error3("ERROR 3");
        // inserto en el grafo
        grafo.agregarCiudad(codigo, nombre);
        cantidadCiudades++;

        return Retorno.ok();

    }

    @Override
    public Retorno registrarConexion(String codigoCiudadOrigen, String codigoCiudadDestino) {
        //Error 1 parametros nulos o vacios
        if(codigoCiudadOrigen==null || codigoCiudadOrigen.isBlank()
        || codigoCiudadDestino==null || codigoCiudadDestino.isBlank()) return Retorno.error1("ERROR 1");
        Ciudad origen = grafo.buscarCiudad(codigoCiudadOrigen);
        Ciudad destino = grafo.buscarCiudad(codigoCiudadDestino);
        if(origen == null) return Retorno.error2("ERROR 2");
        if(grafo.buscarCiudad(codigoCiudadDestino) == null) return Retorno.error3("ERROR 3");
        // Error 4 existencia previa entre origen y destino
        ILista<Conexion> conexiones = origen.getConexiones();
        for (int i = 0; i < conexiones.largo(); i++) {
            if(conexiones.recuperar(i).getCiudadDestino() == destino) return Retorno.error4("ERROR 4");
        }

        Conexion nueva = new Conexion(destino);
        conexiones.insertar(nueva);
        grafo.agregarConexion(codigoCiudadOrigen, codigoCiudadDestino);

        return Retorno.ok();
    }

    @Override
    public Retorno registrarVuelo(String codigoCiudadOrigen, String codigoCiudadDestino,
                                  String codigoDeVuelo, double combustible, double minutos,
                                  double costoEnDolares, TipoVuelo tipoDeVuelo) {
        //Error 1 parametros double < 0
        if(combustible <= 0 || minutos <=0 || costoEnDolares <=0) return Retorno.error1("ERROR 1");

        //Error 2 parametros string nullos o vacios o Tipovuelo null
        if(codigoCiudadOrigen==null || codigoCiudadOrigen.isBlank()
        || codigoCiudadDestino==null || codigoCiudadDestino.isBlank()
        || codigoDeVuelo==null || codigoDeVuelo.isBlank() || tipoDeVuelo==null){
            return Retorno.error2("ERROR 2");
        }
        // Error 3 origen no existe
        Ciudad origen = grafo.buscarCiudad(codigoCiudadOrigen);
        if(origen == null) return Retorno.error3("ERROR 3");

        //Error 4 destino no existe
        Ciudad destino = grafo.buscarCiudad(codigoCiudadDestino);
        if(destino == null) return Retorno.error4("ERROR 4");

        //Error 5 no existe conexion entre origen y destino
        ILista<Conexion> conexiones = origen.getConexiones();
        boolean hayConexion = false;
        for (int i = 0; i < conexiones.largo(); i++) {
            if(conexiones.recuperar(i).getCiudadDestino() == destino) {
                hayConexion = true;
                break;
            }
        }
        if(!hayConexion) return Retorno.error5("ERROR 5");

        //Error 6 ya existe un vuelo con ese codigo en esa conexion
        for (int i = 0; i < conexiones.largo(); i++) {
            Conexion c = conexiones.recuperar(i);
            if(c.getCiudadDestino().getCodigo().equalsIgnoreCase(codigoCiudadDestino)){
                ILista<Vuelo> vuelos = c.getVuelos();
                for (int j = 0; j < vuelos.largo(); j++) {
                    if(vuelos.recuperar(j).getCodigoDeVuelo().equalsIgnoreCase(codigoDeVuelo)){
                        return Retorno.error6("ERROR 6");
                    }
                }

                grafo.agregarVuelo(
                        codigoCiudadOrigen,
                        codigoCiudadDestino,
                        codigoDeVuelo,
                        combustible,
                        minutos,
                        costoEnDolares,
                        tipoDeVuelo);
                return Retorno.ok();
            }
        }

        return Retorno.error5("ERROR 5");
    }

    @Override
    public Retorno actualizarVuelo(String codigoCiudadOrigen, String codigoCiudadDestino,
                                   String codigoDeVuelo, double combustible, double minutos,
                                   double costoEnDolares, TipoVuelo tipoDeVuelo) {
        //Error 1 parametros double < 0
        if(combustible <= 0 || minutos <=0 || costoEnDolares <=0) return Retorno.error1("ERROR 1");

        //Error 2 parametros string nullos o vacios o Tipovuelo null
        if(codigoCiudadOrigen==null || codigoCiudadOrigen.isBlank()
                || codigoCiudadDestino==null || codigoCiudadDestino.isBlank()
                || codigoDeVuelo==null || codigoDeVuelo.isBlank() || tipoDeVuelo==null){
            return Retorno.error2("ERROR 2");
        }

        // Error 3 origen no existe
        Ciudad origen = grafo.buscarCiudad(codigoCiudadOrigen);
        if(origen == null) return Retorno.error3("ERROR 3");

        //Error 4 destino no existe
        Ciudad destino = grafo.buscarCiudad(codigoCiudadDestino);
        if(destino == null) return Retorno.error4("ERROR 4");

        //Error 5 no existen coneccion entre origen y destino
        ILista<Conexion> conexiones = origen.getConexiones();
        Conexion conexion = null;
        for (int i = 0; i < conexiones.largo(); i++) {
            Conexion c = conexiones.recuperar(i);
            if (conexiones.recuperar(i).getCiudadDestino() == destino) {
                conexion = c;
                break;
            }
        }
        if(conexion==null) return Retorno.error5("ERROR 5");

        ILista<Vuelo> vuelos = conexion.getVuelos();
        boolean encontrado = false;
        for (int i = 0; i < vuelos.largo(); i++) {
            if(vuelos.recuperar(i).getCodigoDeVuelo().equalsIgnoreCase(codigoDeVuelo)){
                encontrado = true;
                break;
            }
        }
        if(!encontrado) return Retorno.error6("ERROR 6");

        grafo.actualizarVuelo(
                codigoCiudadOrigen,
                codigoCiudadDestino,
                codigoDeVuelo,
                combustible,
                minutos,
                costoEnDolares,
                tipoDeVuelo
        );
        return Retorno.ok();
    }

    @Override
    public Retorno listadoCiudadesCantDeEscalas(String codigoCiudadOrigen, int cantidad) {
        Retorno ret = new Retorno();
        //validaciones de parametros
        if(cantidad <0) return Retorno.error1("ERROR 1");
        if(codigoCiudadOrigen == null || codigoCiudadOrigen.isBlank()) return Retorno.error2("ERROR 2");
        Ciudad origen = grafo.buscarCiudad(codigoCiudadOrigen);
        if(origen == null) return Retorno.error3("ERROR 3");

        //caso sin escalas(cantidad)
        if(cantidad == 0){
            ret.resultado = Retorno.Resultado.OK;
            ret.valorString = origen.getCodigo()+ ";"+ origen.getNombre();
            return ret;
        }

        //listas y colea para BFS
        ILista<Ciudad> resultado = new Lista<>();
        ICola<Ciudad> cola = new ColaDinamica<>();
        ILista<Integer> niveles = new Lista<>();
        ILista<Ciudad> visitados = new Lista<>();

        //inserto ciudad de origen
        resultado.insertarOrdenado(origen);

        //inicializo el BSF, ciudad origen y nivel 0
        cola.encolar(origen);
        niveles.insertar(0);
        visitados.insertar(origen);

        //Recorrido por niveles
        while(!cola.esVacia()) {
            Ciudad actual = cola.desencolar();
            int nivelActual = niveles.recuperar(0);
            niveles.eliminar(0);


            if(nivelActual >= cantidad) {
                continue;
            }

            ILista<Conexion> listaConexiones = actual.getConexiones();
            int largoConexiones = listaConexiones.largo();
            for (int i = 0; i < largoConexiones; i++) {
                Conexion arista = listaConexiones.recuperar(i);
                Ciudad destino = arista.getCiudadDestino();

                if(arista.getVuelos().largo() ==0){
                    continue;
                }
                if(!visitados.existe(destino)){
                    visitados.insertar(destino);
                    cola.encolar(destino);
                    niveles.insertar(nivelActual+1);
                }

                if(nivelActual + 1 <= cantidad){
                    resultado.insertarOrdenado(destino);
                }
            }
        }

        StringBuilder strBld = new StringBuilder();
        for(int i = 0 ; i < resultado.largo(); i++) {
            if(i > 0) {
                strBld.append("|");
            }
            Ciudad c = resultado.recuperar(i);
            strBld.append(c.getCodigo()).append(";").append(c.getNombre());
        }
        ret.resultado = Retorno.Resultado.OK;
        ret.valorString = strBld.toString();
        return ret;
    }

    @Override
    public Retorno viajeCostoMinimoMinutos(String codigoCiudadOrigen, String codigoCiudadDestino,
                                           TipoVueloPermitido tipoVueloPermitido) {
        //valido parametros
        if((codigoCiudadOrigen == null) || codigoCiudadOrigen.isBlank() ||
                (codigoCiudadDestino == null) || codigoCiudadDestino.isBlank() ||
                (tipoVueloPermitido == null)) {
            return Retorno.error1("ERROR 1");
        }
        //existe origen?
        Ciudad origen = grafo.buscarCiudad(codigoCiudadOrigen);
        if(origen == null) return  Retorno.error2("ERROR 2");
        //existe destino?
        Ciudad destino = grafo.buscarCiudad(codigoCiudadDestino);
        if(destino == null) return Retorno.error3("ERROR 3");
        //minutos con dijkstra
        Camino camino = grafo.dijkstra(codigoCiudadOrigen, codigoCiudadDestino, tipoVueloPermitido, true);
        if(camino == null) {
            return Retorno.error4("ERROR 4");
        }

        StringBuilder strBld = new StringBuilder();
        camino.getRecorrido().recorrer(ciudad ->{
            if(strBld.length() > 0) strBld.append("|");
            strBld.append(ciudad.getCodigo()).append(";").append(ciudad.getNombre());
        });
        String ruta = strBld.toString();


        Retorno ret = new Retorno();
        ret.resultado = Retorno.Resultado.OK;
        ret.valorInteger = camino.getCostoTotal();
        ret.valorString = ruta;
        return ret;
    }

    @Override
    public Retorno viajeCostoMinimoDolares(String codigoCiudadOrigen, String codigoCiudadDestino,
                                           TipoVueloPermitido tipoVueloPermitido) {
        if(codigoCiudadOrigen == null || codigoCiudadOrigen.isBlank() ||
        codigoCiudadDestino == null || codigoCiudadDestino.isBlank()
                || tipoVueloPermitido == null) {
            return Retorno.error1("ERROR 1");
        }

        Ciudad origen = grafo.buscarCiudad(codigoCiudadOrigen);
        if(origen == null) return  Retorno.error2("ERROR 2");

        Ciudad destino = grafo.buscarCiudad(codigoCiudadDestino);
        if(destino == null) return Retorno.error3("ERROR 3");

        Camino camino = grafo.dijkstra(codigoCiudadOrigen, codigoCiudadDestino, tipoVueloPermitido, false);
        if(camino == null) return Retorno.error4("ERROR 4");


        StringBuilder strBld = new StringBuilder();
        camino.getRecorrido().recorrer(ciudad ->{
            if(strBld.length() > 0) strBld.append("|");
            strBld.append(ciudad.getCodigo()).append(";").append(ciudad.getNombre());
        });

        String ruta = strBld.toString();

        Retorno ret = new Retorno();
        ret.resultado = Retorno.Resultado.OK;
        ret.valorInteger =camino.getCostoTotal();
        ret.valorString = ruta;
        return ret;
    }

}
