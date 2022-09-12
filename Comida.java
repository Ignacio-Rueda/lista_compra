import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;//Lectura nombres archivos
import java.io.FileNotFoundException;

public class Comida{
	private static ArrayList<String>ingredientesDef = new ArrayList<String>();
	private static ArrayList<Double>gramosDef = new ArrayList<Double>();
	private static ArrayList<Integer>unidadesDef = new ArrayList<Integer>();

	private ArrayList<String>ingredientes = new ArrayList<String>();
	private String denominacionComida = "";
	private ArrayList<Integer>udsIngredientes = new ArrayList<Integer>();
	private ArrayList<Double>gIngredientes = new ArrayList<Double>();
	private int numeroComensales = 0;

	public Comida (String dC,ArrayList<String>i,ArrayList<Integer>uI,ArrayList<Double>g,int c){
		this.denominacionComida = dC;
		this.numeroComensales = c;
		for(int n=0;n<i.size();n++){
			this.ingredientes.add(i.get(n));
			this.udsIngredientes.add(uI.get(n));
			this.gIngredientes.add(g.get(n));
		}
	}
	
	public void EscrituraFichero(String comida){
	//ESCRITURA EN CARPETA ALMUERZO
	if(comida.equals("almuerzo")){
		try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("/home/rueda/Escritorio/scripts_java/lista_compra/almuerzo/"+this.denominacionComida.toUpperCase()+".txt"));
					//ESCRIBIMOS LOS INGREDIENTES
					bw.write("*INGREDIENTES*:\n");
					for(int n=0;n<this.ingredientes.size();n++){
					bw.write(ingredientes.get(n).toUpperCase()+"\n");
					}
					//ESCRIBIMOS GRAMOS
					bw.write("*GRAMOS*:\n");
					for(int n=0;n<this.gIngredientes.size();n++){
					bw.write(gIngredientes.get(n)+"\n");
					}
					//ESCRIBIMOS UNIDADES
						bw.write("*UNIDADES*:\n");
					for(int n=0;n<this.udsIngredientes.size();n++){
					bw.write(udsIngredientes.get(n)+"\n");
					}
					//NUMERO COMENSALES
						bw.write("*COMENSALES*:\n");
					bw.write(this.numeroComensales+"\n");
					
					bw.close();
				} 
		catch (IOException ioe) {
					System.out.println("No se ha podido escribir en el fichero");
				}
			}
		//ESCRITURA EN CARPETA CENA	
				
			if(comida.equals("cenas")){
		try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("/home/rueda/Escritorio/scripts_java/lista_compra/cenas/"+this.denominacionComida.toUpperCase()+".txt"));
					//ESCRIBIMOS LOS INGREDIENTES
					bw.write("*INGREDIENTES*:\n");
					for(int n=0;n<this.ingredientes.size();n++){
					bw.write(ingredientes.get(n).toUpperCase()+"\n");
					}
					//ESCRIBIMOS GRAMOS
					bw.write("*GRAMOS*:\n");
					for(int n=0;n<this.gIngredientes.size();n++){
					bw.write(gIngredientes.get(n)+"\n");
					}
					//ESCRIBIMOS UNIDADES
						bw.write("*UNIDADES*:\n");
					for(int n=0;n<this.udsIngredientes.size();n++){
					bw.write(udsIngredientes.get(n)+"\n");
					}
					//NUMERO COMENSALES
						bw.write("*COMENSALES*:\n");
					bw.write(this.numeroComensales+"\n");
					
					bw.close();
				} 
		catch (IOException ioe) {
					System.out.println("No se ha podido escribir en el fichero");
				}
			}	
	}
	
	//BUSQUEDA NOMBRE FICHEROS ALMUERZO
		public static ArrayList<String> BusquedaFicheroAlmuerzo(ArrayList<String>l){
			File f = new File("/home/rueda/Escritorio/scripts_java/lista_compra/almuerzo/");
			String[] listaArchivos = f.list();
			for(int n=0;n<listaArchivos.length;n++){
				String valor = listaArchivos[n];
				l.add(valor);
			}
			return l;
	}
	//BUSQUEDA NOMBRE FICHEROS CENA
	public static ArrayList<String> BusquedaFicheroCena(ArrayList<String>l){
			File f = new File("/home/rueda/Escritorio/scripts_java/lista_compra/cenas/");
			String[] listaArchivosCena = f.list();
			for(int n=0;n<listaArchivosCena.length;n++){
				String valor = listaArchivosCena[n];
				l.add(valor);
			}
			return l;
	}
	
	
	//SACAMOS LOS INGREDIENTES DEL ALMUERZO Y CANTIDADES DEL FICHERO
	public static void obtenerIngredientesAlmuerzo(ArrayList<String>m){
		ArrayList<String>obtenerIngredientes = new ArrayList<String>();
		ArrayList<Double>obtenerGramos = new ArrayList<Double>();
		ArrayList<Integer>obtenerUnidades = new ArrayList<Integer>();
		for (int n=0;n<m.size();n++){
		try{
		BufferedReader br = new BufferedReader(new FileReader("/home/rueda/Escritorio/scripts_java/lista_compra/almuerzo/"+m.get(n)));
		
		String linea = "";
		int posIngredientes=0;
		int posGramos=0;
		int posUnidades = 0;
		boolean boolGramos=false;
		boolean boolUnidades=false;
		boolean boolComensales=false;
	
		int contador = 0;
		
		while (linea != null) {
			if (linea.equals("*INGREDIENTES*:")){
				posIngredientes = contador;
			}
			if (linea.equals("*UNIDADES*:")){
				posUnidades = contador;
				boolUnidades=true;
			}
			if (linea.equals("*GRAMOS*:")){
				posGramos=contador;
				boolGramos=true;
			}
			if (linea.equals("*COMENSALES*:")){
				boolComensales=true;
			}
			
			
			if((contador>posIngredientes)&&(boolGramos==false)){
				obtenerIngredientes.add(linea);
			}
	
			if(((contador>posGramos)&&(boolGramos==true))&&(boolUnidades==false)){
				double gramos = Double.parseDouble(linea);
				obtenerGramos.add(gramos);
			}
			
			if(((contador>posUnidades)&&(boolUnidades==true))&&(boolComensales==false)){
				int unidades = Integer.parseInt(linea);
				obtenerUnidades.add(unidades);
			}
			
			linea = br.readLine();
			contador++;
		}
		br.close();
	}
	
	 catch (FileNotFoundException fnfe) { // qué hacer si no se encuentra el fichero
		System.out.println("No se encuentra el fichero");
	} 
	catch (IOException ioe) { // qué hacer si hay un error en la lectura del fichero
		System.out.println("No se puede leer el fichero");
	}
	
	}
	//ELIMINAR ELEMENTOS REPETIDOS
	for(int n=0;n<obtenerIngredientes.size();n++){
		boolean estado = true;
		double doubleSumaGramos =0;
		int intSumaUnidades =0;
		
		for(int pos=0;pos<ingredientesDef.size();pos++){
		
			if(obtenerIngredientes.get(n).equals(ingredientesDef.get(pos))){
				//SUMAMOS GRAMOS
				doubleSumaGramos+=gramosDef.get(pos)+obtenerGramos.get(n);
				gramosDef.set(pos,doubleSumaGramos);
				//SUMAMOS UNIDADES
				intSumaUnidades+=unidadesDef.get(pos)+obtenerUnidades.get(n);
				unidadesDef.set(pos,intSumaUnidades);
				//INDICAMOS QUE YA EXISTE ESE INGREDIENTE
				estado=false;
				}
		}
		doubleSumaGramos=0;
		intSumaUnidades=0;
		if(estado==true){
				ingredientesDef.add(obtenerIngredientes.get(n));
				gramosDef.add(obtenerGramos.get(n));
				unidadesDef.add(obtenerUnidades.get(n));
				
				}
			
			
		}
	
}



	//SACAMOS LOS INGREDIENTES DE LA CENA Y CANTIDADES DEL FICHERO
	public static void obtenerIngredientesCena(ArrayList<String>m){
		ArrayList<String>obtenerIngredientes = new ArrayList<String>();
		ArrayList<Double>obtenerGramos = new ArrayList<Double>();
		ArrayList<Integer>obtenerUnidades = new ArrayList<Integer>();
		for (int n=0;n<m.size();n++){
		try{
		BufferedReader br = new BufferedReader(new FileReader("/home/rueda/Escritorio/scripts_java/lista_compra/cenas/"+m.get(n)));
		
		String linea = "";
		int posIngredientes=0;
		int posGramos=0;
		int posUnidades = 0;
		boolean boolGramos=false;
		boolean boolUnidades=false;
		boolean boolComensales=false;
	
		int contador = 0;
		
		while (linea != null) {
			if (linea.equals("*INGREDIENTES*:")){
				posIngredientes = contador;
			}
			if (linea.equals("*UNIDADES*:")){
				posUnidades = contador;
				boolUnidades=true;
			}
			if (linea.equals("*GRAMOS*:")){
				posGramos=contador;
				boolGramos=true;
			}
			if (linea.equals("*COMENSALES*:")){
				boolComensales=true;
			}
			
			
			if((contador>posIngredientes)&&(boolGramos==false)){
				obtenerIngredientes.add(linea);
			}
	
			if(((contador>posGramos)&&(boolGramos==true))&&(boolUnidades==false)){
				double gramos = Double.parseDouble(linea);
				obtenerGramos.add(gramos);
			}
			
			if(((contador>posUnidades)&&(boolUnidades==true))&&(boolComensales==false)){
				int unidades = Integer.parseInt(linea);
				obtenerUnidades.add(unidades);
			}
			
			linea = br.readLine();
			contador++;
		}
		br.close();
	}
	
	 catch (FileNotFoundException fnfe) { // qué hacer si no se encuentra el fichero
		System.out.println("No se encuentra el fichero");
	} 
	catch (IOException ioe) { // qué hacer si hay un error en la lectura del fichero
		System.out.println("No se puede leer el fichero");
	}
	
	}
	//ELIMINAR ELEMENTOS REPETIDOS
	for(int n=0;n<obtenerIngredientes.size();n++){
		boolean estado = true;
		double doubleSumaGramos =0;
		int intSumaUnidades =0;
		
		for(int pos=0;pos<ingredientesDef.size();pos++){
		
			if(obtenerIngredientes.get(n).equals(ingredientesDef.get(pos))){
				//SUMAMOS GRAMOS
				doubleSumaGramos+=gramosDef.get(pos)+obtenerGramos.get(n);
				gramosDef.set(pos,doubleSumaGramos);
				//SUMAMOS UNIDADES
				intSumaUnidades+=unidadesDef.get(pos)+obtenerUnidades.get(n);
				unidadesDef.set(pos,intSumaUnidades);
				//INDICAMOS QUE YA EXISTE ESE INGREDIENTE
				estado=false;
				}
		}
		doubleSumaGramos=0;
		intSumaUnidades=0;
		if(estado==true){
				ingredientesDef.add(obtenerIngredientes.get(n));
				gramosDef.add(obtenerGramos.get(n));
				unidadesDef.add(obtenerUnidades.get(n));
				
				}
			
			
		}
		//MUESTRO INGREDIENTES DE CENA
	System.out.println("LOS INGREDIENTES QUE NECESITA SON:");	
	for(int l=0;l<ingredientesDef.size();l++){
		System.out.print(ingredientesDef.get(l)+" ");
		if(gramosDef.get(l)>0){
			System.out.println(gramosDef.get(l)+" gramos");
		}
		if(unidadesDef.get(l)>0){
			if(unidadesDef.get(l)==1){
			System.out.println(unidadesDef.get(l)+" unidad");
			}
			if(unidadesDef.get(l)>1){
			System.out.println(unidadesDef.get(l)+" unidades");
			}
			
		}
	}
	
}



}

	
