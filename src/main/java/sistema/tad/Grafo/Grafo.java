package sistema.tad.Grafo;

import interfaz.TipoVuelo;
import interfaz.TipoVueloPermitido;
import sistema.Ciudad;
import sistema.Conexion;
import sistema.Vuelo;
import sistema.tad.Cola.ColaDinamica;
import sistema.tad.Cola.ICola;
import sistema.tad.Lista.ILista;
import sistema.tad.Lista.Lista;

public class Grafo implements IGrafo{
    private Lista<Ciudad> vertices;
    private int maxVertices;

    public Grafo(int maxVertices) {
        this.maxVertices = maxVertices;
        this.vertices = new Lista<>();
    }

    //METODOS OVERRIDE

    @Override
    public void agregarCiudad(String codigo, String nombre) {
        //validaciones de null o empty
        if(codigo ==null || codigo.isEmpty() || nombre==null || nombre.isEmpty()){
            return;
        }
        //valido que no se supere el maximo de vertices y el codigo sea unico
        if(vertices.largo() < maxVertices && buscarCiudad(codigo)==null) {
            vertices.insertar(new Ciudad(codigo, nombre));
        }
    }

    @Override
    public Ciudad buscarCiudad(String codigo) {
        int n = vertices.largo();
        for(int i = 0; i < n; i++) {
            Ciudad c = vertices.recuperar(i);
            if(c.getCodigo().equals(codigo)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void agregarConexion(String codorigen, String codDestino) {
        Ciudad origen = this.buscarCiudad(codorigen);
        Ciudad destino = this.buscarCiudad(codDestino);
        if(origen == null || destino == null) return;

        ILista<Conexion> lista = origen.getConexiones();
        int largo = lista.largo();
        for(int i = 0; i < largo; i++) {
            if(lista.recuperar(i).getCiudadDestino().getCodigo().equals(codDestino)) {
                return;
            }
        }
        lista.insertar(new Conexion(destino));
    }


    @Override
    public void agregarVuelo(String ciudadDeOrigen, String ciudadDeDestino,
                             String codigoDeVuelo, double combustible, double minutos,
                             double costoDolares, TipoVuelo tipoVuelo) {
        //validar los parametros
        if(ciudadDeOrigen==null || ciudadDeOrigen.isEmpty()
        ||ciudadDeDestino==null || ciudadDeDestino.isEmpty()
        || codigoDeVuelo==null || codigoDeVuelo.isEmpty()
        || combustible<=0 || minutos<=0 || costoDolares<=0
        || tipoVuelo==null){
            return;
        }

        //busco ciudad origen y destino
        Ciudad origen = this.buscarCiudad(ciudadDeOrigen);
        Ciudad destino = this.buscarCiudad(ciudadDeDestino);
        if( origen == null|| destino == null) return;

        //busco conecciones existentes
        ILista<Conexion> conexiones = origen.getConexiones();
        int largo = conexiones.largo();
        for (int i = 0; i < largo; i++) {
            Conexion c = conexiones.recuperar(i);
            if(c.getCiudadDestino().getCodigo().equals(ciudadDeDestino)) {
                //verifico que no haya un vuelo con ese codigo
                ILista<Vuelo> vuelos = c.getVuelos();
                int largoVuelos = vuelos.largo();
                for (int j = 0; j < largoVuelos; j++) {
                    if(vuelos.recuperar(j).getCodigoDeVuelo().equals(codigoDeVuelo)) {
                        return;
                    }
                }
                vuelos.insertar(new Vuelo(
                        ciudadDeOrigen,ciudadDeDestino,
                        codigoDeVuelo, combustible,
                        minutos,costoDolares,
                        tipoVuelo));
                return;
            }
        }
    }

    @Override
    public void actualizarVuelo(String ciudadDeOrigen, String ciudadDeDestino,
                                String codigoDeVuelo, double combustible,
                                double minutos, double costoDolares,
                                TipoVuelo tipoVuelo) {
        //valido parametros
        if(ciudadDeOrigen==null || ciudadDeOrigen.isEmpty()
        ||ciudadDeDestino==null || ciudadDeDestino.isEmpty()
        || codigoDeVuelo==null || codigoDeVuelo.isEmpty()
        || combustible<=0 || minutos<=0 || costoDolares<=0
        || tipoVuelo==null){
            return;
        }

        Ciudad origen = buscarCiudad(ciudadDeOrigen);
        if(origen == null) return;
        // obtengo lista de conexiones de la ciudad
        ILista<Conexion> conexiones = origen.getConexiones();
        int cantConexiones = conexiones.largo();
        for(int i = 0; i < cantConexiones; i++) {
            Conexion c = conexiones.recuperar(i);
            //destino de la conexion
            Ciudad destino = c.getCiudadDestino();
            if(destino.getCodigo().equals(ciudadDeDestino)){
                //rrecorro los vuelos de esa conexion
                ILista<Vuelo> vuelos = c.getVuelos();
                int cantVuelos = vuelos.largo();
                for(int j = 0; j < cantVuelos; j++) {
                    Vuelo v = vuelos.recuperar(j);
                    if(v.getCodigoDeVuelo().equals(codigoDeVuelo)){
                        //actualizamos datos
                        v.setCombustible(combustible);
                        v.setMinutos(minutos);
                        v.setCostoEnDolares(costoDolares);
                        v.setTipoVuelo(tipoVuelo);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public Lista<Ciudad> ciudadesConEscala(String codigoCiudadOrigen, int escalas) {
        Ciudad origen = buscarCiudad(codigoCiudadOrigen);
        Lista<Ciudad> resultado = new Lista<>();
        if(origen == null || escalas < 0) return resultado;

        // bfs ciudades yu niveles
        ICola<Ciudad> colaCiudades = new ColaDinamica<>();
        ICola<Integer> colaNiveles= new ColaDinamica<>();
        ILista<String> visitados = new Lista<>();

        colaCiudades.encolar(origen);
        colaNiveles.encolar(0);
        visitados.insertar(origen.getCodigo());

        while (!colaCiudades.esVacia()){
            Ciudad actual = colaCiudades.desencolar();
            int nivel = colaNiveles.desencolar();
            if(nivel >= escalas) continue;

            ILista<Conexion> conexiones = actual.getConexiones();
            int cantConexiones = conexiones.largo();
            for(int i = 0; i < cantConexiones; i++) {
                Conexion c = conexiones.recuperar(i);
                Ciudad siguiente = c.getCiudadDestino();
                String codigoSig= siguiente.getCodigo();
                if(!visitados.existe(codigoSig)) {
                    visitados.insertar(codigoSig);
                    resultado.insertar(siguiente);
                    colaCiudades.encolar(siguiente);
                    colaNiveles.encolar(nivel + 1);

                }
            }

        }
        return resultado;
    }

    @Override
    public Camino dijkstra(String ciudadDeOrigen, String codigoCiudadDestino,
                           TipoVueloPermitido tipoPermitido, boolean optimizarminutos) {

        int largoCiudades = vertices.largo();
        int indiceOrigen = indexOf(ciudadDeOrigen);
        int indiceDestino = indexOf(codigoCiudadDestino);
        if(indiceOrigen == -1 || indiceDestino == -1) return null;

        final int infinito = Integer.MAX_VALUE/2;
        int[] distMinOrigen = new int[largoCiudades];
        boolean[] visitado = new boolean[largoCiudades];
        int[] previo = new int[largoCiudades];
        for(int i = 0; i < largoCiudades; i++) {
            distMinOrigen[i] = infinito;
            visitado[i] = false;
            previo[i] = -1;
        }
        distMinOrigen[indiceOrigen] = 0;

        for(int i = 0; i < largoCiudades; i++) {
            int u = -1;
            int inf = infinito;
            for(int j = 0; j < largoCiudades; j++) {
                if(!visitado[j] && distMinOrigen[j] < inf) {
                    inf = distMinOrigen[j];
                    u = j;
                }
            }
            if(u == -1) break;
            visitado[u] = true;
            Ciudad ciudadU = vertices.recuperar(u);

            ILista<Conexion> conexiones = ciudadU.getConexiones();
            int cantConexiones = conexiones.largo();
            for(int l = 0; l < cantConexiones; l++) {
                Conexion c = conexiones.recuperar(l);
                int inxDestino = indexOf(c.getCiudadDestino().getCodigo());
                if(visitado[inxDestino]) continue;

                //peso minimo y optimizar minutos
                int peso = infinito;
                ILista<Vuelo> vuelos = c.getVuelos();
                 int largoVuelos = vuelos.largo();
                 for(int j = 0; j < largoVuelos; j++) {
                     Vuelo v = vuelos.recuperar(j);
                     boolean okTipo =
                             tipoPermitido == TipoVueloPermitido.AMBOS
                             || (tipoPermitido == TipoVueloPermitido.COMERCIAL && v.getTipoVuelo() == TipoVuelo.COMERCIAL)
                             || (tipoPermitido == TipoVueloPermitido.PRIVADO && v.getTipoVuelo() == TipoVuelo.PRIVADO);
                     if(!okTipo) continue;
                     int optimizar = optimizarminutos ? (int) v.getMinutos(): (int)v.getCostoEnDolares();
                     if(optimizar < peso ) peso = optimizar;
                 }
                if(peso == infinito) continue;

                if(distMinOrigen[u] + peso< distMinOrigen[inxDestino]){
                    distMinOrigen[inxDestino] = distMinOrigen[u]+peso;
                    previo[inxDestino] = u;
                }
            }
        }
        if(distMinOrigen[indiceDestino] >= infinito) return null;

        int contador =0;
        for(int i = indiceDestino; i != -1; i = previo[i]) contador++;
        Ciudad[] rutaArr = new Ciudad[contador];
        int idx = contador - 1;
        for(int j = indiceDestino; j !=- 1; j = previo[j]) {
            rutaArr[idx--] = vertices.recuperar(j);
        }
        Lista<Ciudad> ruta = new Lista<>();
        for(Ciudad c : rutaArr) {
            ruta.insertar(c);
        }

        return new Camino(ruta, distMinOrigen[indiceDestino]);
    }

    private int indexOf(String codigo) {
        int n = vertices.largo();
        for (int i = 0; i < n; i++) {
            if(vertices.recuperar(i).getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }
}
