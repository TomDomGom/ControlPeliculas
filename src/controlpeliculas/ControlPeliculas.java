/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlpeliculas;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tomas
 */
public class ControlPeliculas {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws ParseException, SQLException {
        // TODO code application logic here
        Map<String, String> emfProperties = new HashMap<>();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ControlPeliculasPU", emfProperties); 
        EntityManager em = emf.createEntityManager();
        
        // Conexión con la base de datos.
        emfProperties.put("java.persistence.schema-generation.database.action","create");
        emfProperties.put("java.persistence.jdbc.url", "jdbc:derby:BDPeliculas;create=true");
        
        // Creamos datos para las categorias.
           Datoscategoria cat0 = new Datoscategoria();
           cat0.setNombre("Documental");
           Datoscategoria cat1 = new Datoscategoria();
           cat1.setNombre("Biográfico");
           Datoscategoria cat2 = new Datoscategoria();
           cat2.setNombre("Histórico");
           Datoscategoria cat3 = new Datoscategoria();
           cat3.setNombre("Musical");
           Datoscategoria cat4 = new Datoscategoria();
           cat4.setNombre("Comedia");
           Datoscategoria cat5 = new Datoscategoria();
           cat5.setNombre("Infantil");
           Datoscategoria cat6 = new Datoscategoria();
           cat6.setNombre("Aventura & Acción");
           Datoscategoria cat7 = new Datoscategoria();
           cat7.setNombre("Bélico");
           Datoscategoria cat8 = new Datoscategoria();
           cat8.setNombre("Ciencia ficción");
           Datoscategoria cat9 = new Datoscategoria();
           cat9.setNombre("Drama");
           Datoscategoria cat10 = new Datoscategoria();
           cat10.setNombre("Suspense");
           Datoscategoria cat11 = new Datoscategoria();
           cat11.setNombre("Terror / Horror");
           Datoscategoria cat12 = new Datoscategoria();
           cat12.setNombre("Porno-erótico");
           
           // Convertimos los datos de fecha. para Así poder generar las fechas sin problema.
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            String dateInString = "12-04-2019";
            Date peli2Fec = sdf.parse(dateInString);

            String dateInString1 = "11-03-2019";
            Date peli0Fec = sdf.parse(dateInString1);

            String dateInString2 = "26-04-2019";
            Date peli1Fec = sdf.parse(dateInString2);

            // Pasamos los datos de Recaudación de cada Pelicula.
            // Problema con el BigDecimal... a la hora de pasar los datos a las tablas.
            BigDecimal peli0Rec = new BigDecimal (45000000.00);
            BigDecimal peli1Rec = new BigDecimal (1209000000.00);
            BigDecimal peli2Rec = new BigDecimal (1500000.00);

            // Creamos algunos ejemplo para añadir a la base de datos.
            // Pelicula 0
            Datospeliculas peli0 = new Datospeliculas();
            peli0.setTitulo("Dumbo");
            peli0.setDirector("Tim Burton");
            peli0.setCalificacion("TP");
            peli0.setFechaestreno(peli0Fec);
            peli0.setProyectada(Boolean.TRUE);
            peli0.setProductora("Walt Disney");
            peli0.setRecaudacion(peli0Rec);

            // Pelicula 1
            Datospeliculas peli1 = new Datospeliculas();
            peli1.setTitulo("Avengers Endgame");
            peli1.setDirector("Anthony Russo");
            peli1.setCalificacion("07");
            peli1.setFechaestreno(peli1Fec);
            peli1.setProyectada(Boolean.FALSE);
            peli1.setProductora("Marvel Studio");
            peli1.setRecaudacion(peli1Rec);

            // Pelicula 2
            Datospeliculas peli2 = new Datospeliculas();
            peli2.setTitulo("Lo Dejo Cuando Quieras");
            peli2.setDirector("Carlos Therón");
            peli2.setProyectada(Boolean.TRUE);
            peli2.setFechaestreno(peli2Fec);
            peli2.setProductora("Telecinco Cinema");
            peli2.setCalificacion("16");
            peli2.setRecaudacion(peli2Rec);

            //Vamos a pasar a insertar datos a la base de datos.
            em.getTransaction().begin();
            // Añadimos las categorias.
            em.persist(cat0);
            em.persist(cat1);
            em.persist(cat2);
            em.persist(cat3);
            em.persist(cat4);
            em.persist(cat5);
            em.persist(cat6);
            em.persist(cat7);
            em.persist(cat8);
            em.persist(cat9);
            em.persist(cat10);
            em.persist(cat11);
            em.persist(cat12);
            
            // Añadimos las películas.
            em.persist(peli0);
            em.persist(peli1);
            em.persist(peli2);

            // Con el commit conseguimos guardar los datos en la base de datos.
            em.getTransaction().commit();
            
            // Cerramos la conexión con la base de datos.
            em.close();
            emf.close();
            try {
                DriverManager.getConnection("jdbc:derby:BDPeliculas;create=true");
            } catch (SQLException ex) {
                
            }

        
        
    
    }// Final Main.
    
} // Final Clase ControlPeliculas.
