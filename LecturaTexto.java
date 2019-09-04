import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class LecturaTexto {
    
    static Scanner intro = new Scanner(System.in);
    
    public static void main(String[] args) {
        menu();
    }
    
    /**
     * Método menu que ejecuta el menú de opciones mediante
     * @param option mientras este sea distinto de 3
    * */
    
    public static void menu() {
        int option;
        do {
            option= input();

            if (option > 0 && option < 3) {
                options(option);
            } else if (option == 3) {
                break;
            } else {
                System.out.println("Opcion Inválida");
            }
        } while (option != 3);
    }
    
    /**
     * Método input que muestra las opciones del menú y pide el ingreso
     * del @param input y lo devuelve si es posible.
    * */
    
    public static int input(){
        int input=0;
        System.out.println("Ingrese un número correspondiente a la opción que desea seleccionar");
        System.out.println(" 1 - Leer el archivo de texto");
        System.out.println(" 2 - Agregar una nueva línea al texto");
        System.out.println(" 3 - Salir del programa");
        String in=intro.next();
        try {
            input=err(in);
        }catch(Exception ex) {
        }
        return input;
    }
    
    /**
     * Método err que transforma el @param input a int si 
     * es posible, de lo contrario lo intenta nuevamente.
     * Si lo logra hace @return @param in.
    * */
    
    public static int err(String input) throws Exception {
        int in=0;
        try {
            in=Integer.parseInt(input);
        } catch (Exception ex) {
            System.out.println("Favor ingresar Dígitos y no carácteres");
            in=err(intro.next());
        }
        in=validInt(in);
        return in; 
    }
    
    /**
     * Método validInt recive un @param in y chequea que está
     * dentro de los parametros para el funcionamiento del menú
     * y @retrurn @param in si cumple los requisitos.
    * */
    
    public static int validInt(int in){
        if (in<0 || in >3) {
            System.out.println("Opcion Inválida,favor ingresar un número entre 0 y 3");
            in=input();
        }
        return in;
    }
    
    /**
     * Método options que ejecuta las opciones de acuerdo a
     * un @param option.
    * */

    public static void options(int option) {
        if (option == 1) {
            tryFile("texto.txt");
        }else if (option == 2) {
            newLine("texto.txt");
        }
    }
    
    /**
     *Método tryFile que intenta leer el archivo de texto en la
     * @param ruta si es posible y muestra el texto del archivo.
    * */
    
    public static void tryFile(String path) {
        Path file = Paths.get(path);
        String text = "Empty";
        try {
            text=readFile(file);
            System.out.println(text);
        } catch (IOException e) {
            System.out.println("No fue posible leer el archivo, favor intente nuevamente");
        }
    }
    
    /**
     * Método readFile que lee el archivo en @param path y
     * @return @param text resultante de la lectura.
    * */
    
    public static String readFile(Path path) throws IOException{
        String text=new String(Files.readAllBytes(path));
        return text;
    }

    /**
     *Método newLine recibe el @param path 
     * intenta leer @param file texto.txt y de acuerdo a @param line
     * intenta agregar el @param line al texto y reescribe el archivo en
     * la ruta @param file.
     **/
    
    public static void newLine(String path) {
        Path file = Paths.get(path);
        String text = "Empty";
        String line = lineInput();
        try {
            text=readFile(file);
            text += "\n" + line;
            Files.write(file, text.getBytes());
            System.out.println(text);
        } catch (IOException e) {
            System.out.println("No fue posible agregar una nueva línea al texto, favor intentar nuevamente");
        }
    }
    
    /**
     *Método lineInput lee la siguiente línea en el @param line
     * y @return @param line.
     **/
    
    public static String lineInput() {
        System.out.println("Ingrese la nueva linea a ingrsar al texto");
        intro.next();
        String line=intro.nextLine();
        return line;
    }
    
}
