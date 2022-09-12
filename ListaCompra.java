import java.util.ArrayList;
import java.io.File;//Lectura nombres archivos
import java.util.Collections;
public class ListaCompra{	
	public static void main(String[]args){
		//MENÚ
		System.out.println("Elija una de las siguientes opciones:");
		System.out.println("1-Añadir almuerzo");
		System.out.println("2-Añadir cena");;
		System.out.println("3-Elegir menú semanal(5 días)");
		System.out.println("4-Salir");
		
		int eleccion = Integer.parseInt(System.console().readLine());
		ArrayList<String> vIngredientes = new ArrayList<String>();
		ArrayList<Integer> vUdsIngredientes = new ArrayList<Integer>();
		ArrayList<Double> vGramos = new ArrayList<Double>();
		int comensales = 0;
		
		//SEGÚN ELECCIÓN TOMAMOS UN MÉTODO U OTRO
		
		switch(eleccion){
		
			//INTRODUCCIÓN DE ALMUERZO:
			case 1:
				System.out.println("Introduzca denominación comida");
				String denominacionComida = System.console().readLine();
				System.out.println("Introduzca para cuántas personas es esta comida");
				comensales = Integer.parseInt(System.console().readLine());
				int udsIngredientes = 0;
				double gramos = 0.0;
				System.out.println("Introduzca ingredientes de "+denominacionComida+". Para finalizar pulse '*' y presione enter:");
				String ingredientes = System.console().readLine();
				if(!ingredientes.equals("*")){
					System.out.println("Introduzca unidades de "+ingredientes+":");
					udsIngredientes = Integer.parseInt(System.console().readLine());
					System.out.println("Introduzca gramos "+ingredientes+":");
					gramos = Double.parseDouble(System.console().readLine());
					vIngredientes.add(ingredientes);
					vUdsIngredientes.add(udsIngredientes);
					vGramos.add(gramos);
					}
				while(!ingredientes.equals("*")){
				System.out.println("Introduzca ingredientes de "+denominacionComida+":");
				ingredientes = System.console().readLine();
				if (!ingredientes.equals("*")){
					System.out.println("Introduzca unidades "+ingredientes+":");
					udsIngredientes = Integer.parseInt(System.console().readLine());
					System.out.println("Introduzca gramos "+ingredientes+":");
					gramos = Double.parseDouble(System.console().readLine());
					vIngredientes.add(ingredientes);
					vUdsIngredientes.add(udsIngredientes);
					vGramos.add(gramos);
					}
				}
				//PASAMOS AL CONSTRUCTOR CARACTERISTICAS:
				Comida introduccionComida = new Comida(denominacionComida,vIngredientes,vUdsIngredientes,vGramos,comensales);
				//CREAMOS UN FICHERO CON TODAS LAS CARACTERISTICAS:
				introduccionComida.EscrituraFichero("almuerzo");
				break;
				
			//INTRODUCCIÓN DE CENA:
			case 2:
				System.out.println("Introduzca denominación comida");
				denominacionComida = System.console().readLine();
				System.out.println("Introduzca para cuántas personas es esta comida");
				comensales = Integer.parseInt(System.console().readLine());
				udsIngredientes = 0;
				gramos = 0.0;
				System.out.println("Introduzca ingredientes de "+denominacionComida+". Para finalizar pulse '*' y presione enter:");
				ingredientes = System.console().readLine();
				if(!ingredientes.equals("*")){
					System.out.println("Introduzca unidades de "+ingredientes+":");
					udsIngredientes = Integer.parseInt(System.console().readLine());
					System.out.println("Introduzca gramos "+ingredientes+":");
					gramos = Double.parseDouble(System.console().readLine());
					vIngredientes.add(ingredientes);
					vUdsIngredientes.add(udsIngredientes);
					vGramos.add(gramos);
					}
				while(!ingredientes.equals("*")){
				System.out.println("Introduzca ingredientes de "+denominacionComida+":");
				ingredientes = System.console().readLine();
				if (!ingredientes.equals("*")){
					System.out.println("Introduzca unidades "+ingredientes+":");
					udsIngredientes = Integer.parseInt(System.console().readLine());
					System.out.println("Introduzca gramos "+ingredientes+":");
					gramos = Double.parseDouble(System.console().readLine());
					vIngredientes.add(ingredientes);
					vUdsIngredientes.add(udsIngredientes);
					vGramos.add(gramos);
					}
				}
				//PASAMOS AL CONSTRUCTOR CARACTERISTICAS:
				Comida introduccionCena = new Comida(denominacionComida,vIngredientes,vUdsIngredientes,vGramos,comensales);
				//CREAMOS UN FICHERO CON TODAS LAS CARACTERISTICAS:
				introduccionCena.EscrituraFichero("cenas");
				break;
			
				
			
			//ELEGIR MENÚ SEMANAL
			case 3:
					//Array almuerzo
					ArrayList<String>listaMenu = new ArrayList<String>();//Array que contiene el nombre de los ficheros del almuerzo
					ArrayList<String>listaMenuElegida = new ArrayList<String>();//Array que contiene el nombre de los ficheros del almuerzo elegidos
					Comida.BusquedaFicheroAlmuerzo(listaMenu);
					//Array cena
					ArrayList<String>listaMenuCena = new ArrayList<String>();//Array que contiene el nombre de los ficheros de la cena
					ArrayList<String>listaMenuCenaElegida = new ArrayList<String>();//Array que contiene el nombre de los ficheros de la cena elegida
					Comida.BusquedaFicheroCena(listaMenuCena);
					//ELECCIÓN ALMUERZOS
					Collections.sort(listaMenu); //ordenamos lista almuerzo
					for(int n=0;n<5;n++){
						int pos=n+1;
						for(int l=0;l<listaMenu.size();l++){
							if(l==0){
								System.out.println("ALMUERZOS DISPONIBLES:");
							}
							System.out.println(l+1+"-"+listaMenu.get(l).replace(".txt",""));
						}
						System.out.println("Seleccione un almuerzo para el día "+pos+" de la semana:");
						int posComida = Integer.parseInt(System.console().readLine());
						try{
							listaMenuElegida.add(listaMenu.get(posComida-1));
						}
						catch(Exception e){
							System.out.println("Lo siento esa opción NO ESTÁ DISPONIBLE");
							n=n-1;//Para que vuelva a preguntar por el dia de la semana en que ha saltado el error
						}
					}	
					//ELECCIÓN CENAS
					Collections.sort(listaMenuCena);//ordenamos lista cena
					for(int n=0;n<5;n++){
						int pos=n+1;
						for(int l=0;l<listaMenuCena.size();l++){
							if(l==0){
								System.out.println("CENAS DISPONIBLES:");
							}
							System.out.println(l+1+"-"+listaMenuCena.get(l).replace(".txt",""));
						}
						System.out.println("Seleccione una cena para el día "+pos+" de la semana:");
						int posComida = Integer.parseInt(System.console().readLine());
					
						try{
								listaMenuCenaElegida.add(listaMenuCena.get(posComida-1));
						}
						catch(Exception e){
							System.out.println("Lo siento esa opción NO ESTÁ DISPONIBLE");
							n=n-1;//Para que vuelva a preguntar por el dia de la semana en que ha saltado el error
						}
					}
					
					
					
					//menu elegido
					System.out.println("Los menús elegidos para los almuerzos son:");
					for(int l=0; l<listaMenuElegida.size();l++){
						int dia =1+l;
						System.out.println("DIA "+dia+" = "+listaMenuElegida.get(l).replace(".txt",""));	
						}
					System.out.println("Los menús elegidos para las cenas son:");
					for(int l=0; l<listaMenuCenaElegida.size();l++){
						int dia =1+l;
						System.out.println("DIA "+dia+" = "+listaMenuCenaElegida.get(l).replace(".txt",""));
						}
					
				//PASAMOS LOS MENÚS ELEGIDOS PARA OBTENER INGREDIENTES:
					Comida.obtenerIngredientesAlmuerzo(listaMenuElegida);
					Comida.obtenerIngredientesCena(listaMenuCenaElegida);
			
			case 4:
				break;
				
			default:
				System.out.println("Lo siento esa opción no está registrada!");
			
		}
	}
}



