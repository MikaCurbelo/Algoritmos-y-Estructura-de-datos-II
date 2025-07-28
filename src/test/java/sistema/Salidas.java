package sistema;

public class Salidas {

    public static String separador = "|";

    public static String ana = "2.614.689-5;Ana;ana@ort.edu.uy;25;Platino";
    public static String guille = "1.914.689-5;Guillermo;guille@ort.edu.uy;35;Estándar";
    public static String pedro = "614.689-5;Pedro;pedro@ort.edu.uy;75;Frecuente";
    public static String maria = "3.614.689-5;María;maria@ort.edu.uy;45;Estándar";
    public static String estandar1 = "314.689-5;Estandar1;estandar1@ort.edu.uy;20;Estándar";
    public static String estandar2 = "4.314.689-5;Estandar2;estandar2@ort.edu.uy;20;Estándar";
    public static String estandar3 = "5.314.689-5;Estandar3;estandar3@ort.edu.uy;20;Estándar";

    public static String ciudad1 = "COD01;Ciudad1";
    public static String ciudad2 = "COD02;Ciudad2";
    public static String ciudad3 = "COD03;Ciudad3";
    public static String ciudad4 = "COD04;Ciudad4";
    public static String ciudad5 = "COD05;Ciudad5";
    public static String ciudad6 = "COD06;Ciudad6";
    public static String ciudad7 = "COD07;Ciudad7";
    public static String ciudad8 = "COD08;Ciudad8";
    public static String ciudad9 = "COD09;Ciudad9";
    public static String ciudad10 = "COD10;Ciudad10";
    public static String ciudad11 = "COD11;Ciudad11";
    public static String ciudad12 = "COD12;Ciudad12";
    public static String ciudad13 = "COD13;Ciudad13";
    public static String ciudad14 = "COD14;Ciudad14";
    public static String ciudad15 = "COD15;Ciudad15";
    public static String ciudad16 = "COD16;Ciudad16";
    public static String ciudad17 = "COD17;Ciudad17";
    public static String ciudad18 = "COD18;Ciudad18";
    public static String ciudad19 = "COD19;Ciudad19";
    public static String ciudad20 = "COD20;Ciudad20";
    public static String ciudad21 = "COD21;Ciudad21";
    public static String ciudad22 = "COD22;Ciudad22";
    public static String ciudad23 = "COD23;Ciudad23";
    public static String[] ciudades = {"", ciudad1, ciudad2, ciudad3, ciudad4, ciudad5, ciudad6, ciudad7, ciudad8, ciudad9, ciudad10, ciudad11, ciudad12, ciudad13, ciudad14, ciudad15, ciudad16, ciudad17, ciudad18, ciudad19, ciudad20, ciudad21, ciudad22, ciudad23};

    public static String distancia1() {
        StringBuilder salida = new StringBuilder();
        for (int i = 1; i < 8; i++) {
            salida.append(ciudades[i]);
            salida.append(separador);
        }
        for (int i = 16; i <= 18; i++) {
            salida.append(ciudades[i]);
            salida.append(separador);
        }
        salida.append(ciudad23);
        return salida.toString();
    }

    public static String distancia2() {
        StringBuilder salida = new StringBuilder();
        for (int i = 1; i < 13; i++) {
            salida.append(ciudades[i]);
            salida.append(separador);
        }
        for (int i = 16; i <= 21; i++) {
            salida.append(ciudades[i]);
            salida.append(separador);
        }
        salida.append(ciudad23);
        return salida.toString();
    }

    public static String distancia3() {
        StringBuilder salida = new StringBuilder();
        for (int i = 1; i < 23; i++) {
            salida.append(ciudades[i]);
            salida.append(separador);
        }
        salida.append(ciudad23);
        return salida.toString();
    }


}
