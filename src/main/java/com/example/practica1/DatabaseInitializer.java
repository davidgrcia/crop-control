package com.example.practica1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.practica1.cultivo.Cultivo;
import com.example.practica1.cultivo.RepositorioCultivos;
import com.example.practica1.producto.Producto;
import com.example.practica1.producto.RepositorioProductos;
import com.example.practica1.tratamiento.RepositorioTratamientos;
import com.example.practica1.tratamiento.Tratamiento;




@Component
public class DatabaseInitializer {

	@Autowired
	private RepositorioProductos repoProductos;
	
	@Autowired
	private RepositorioCultivos repoCultivos;
	
	@Autowired
	private RepositorioTratamientos repoTratamientos;

	@PostConstruct
	public void init() {

		//PRODUCTOS
		// Productos fisiosanitarios con todos los atributos
		Producto p1 = new Producto ("Azadiractina","La Azadiractina es un compuesto químico que pertenece a los limonoides. Es un metabolito secundario presente en las semillas del árbol de Nim o margosa.",5,30);
		Producto p2 = new Producto ("Piretrinas","Las piretrinas son una mezcla de compuestos orgánicos que se encuentran de modo natural en las flores de plantas del género Chrysanthemum",2,15);
		Producto p3 = new Producto ("Cuasia","Planta de la familia de las simarubáceas, notable por el amargo sabor de su corteza y raíz, las cuales se emplean en medicina.",7,60);
		
		//Producto sin plazo de reentrada
		Producto p4 = new Producto ("Lecitina", "La fosfatidilcolina o polienilfosfatidilcolina (también llamada lecitina) es un fosfolípido que, junto con las sales biliares, ayuda a la solubilización de los ácidos biliares en la bilis.",10);
		
		//Producto sin plazo de reentrada ni de recoleccion
		Producto p5 = new Producto ("Cera de abejas","La cera no es una producción vegetal, sino una secreción animal que las abejas voluntariamente segregan siempre que la necesitan a través de las glándulas");
		
		repoProductos.save(p1);
		repoProductos.save(p2);
		repoProductos.save(p3);
		repoProductos.save(p4);
		repoProductos.save(p5);
		
		
		//CULTIVOS
		// Cultivo con 3 tratamientos
		Cultivo c1 = new Cultivo ("Patata","Monalisa",LocalDate.of(2021, 5, 13),"Madrid");
		
		// Cultivo con 2 tratamientos
		Cultivo c2 = new Cultivo ("Lechuga","Iceberg",LocalDate.of(2021, 11, 25),"Valencia");
		
		// Cultivo con 1 tratamiento
		Cultivo c3 = new Cultivo ("Tomate","Kumato",LocalDate.of(2021, 12, 29),"Vigo");
		
		// Cultivos
		Cultivo c4 = new Cultivo ("Pimiento","Verde",LocalDate.of(2021, 4, 4),"Badajoz");
		Cultivo c5 = new Cultivo ("Cebolla","Morada",LocalDate.of(2021, 9, 10),"Murcia");
		
		// Cultivo sin tratamientos
		Cultivo c6 = new Cultivo ("Calabacin","Redondo",LocalDate.of(2021, 7, 20),"Avila");

		repoCultivos.saveAndFlush(c1);
		repoCultivos.saveAndFlush(c2);
		repoCultivos.saveAndFlush(c3);
		repoCultivos.saveAndFlush(c4);
		repoCultivos.saveAndFlush(c5);
		repoCultivos.saveAndFlush(c6);
		
		
		//cultivo con 3 tratamientos en la misma fecha
		Tratamiento t1 = new Tratamiento (c1,p1,"123",LocalDate.of(2021, 5, 11)); //ambos
		Tratamiento t2 = new Tratamiento (c1,p2,"456",LocalDate.of(2021, 5, 11)); //ambos
		Tratamiento t3 = new Tratamiento (c1,p4,"789",LocalDate.of(2021, 5, 11)); //plazo recoleccion pero no reentrada
		
		repoTratamientos.save(t1);
		repoTratamientos.save(t2);
		repoTratamientos.save(t3);
		
		List<Tratamiento> l1 = new ArrayList<>();
		l1.add(t1);
		l1.add(t2);
		l1.add(t3);
		c1.setTratamientos(l1);
		
		repoCultivos.saveAndFlush(c1);
		
		//cultivo con 2 tratamientos distintas fechas
		Tratamiento t4 = new Tratamiento (c2,p1,"1234",LocalDate.of(2021, 4, 11));
		Tratamiento t5 = new Tratamiento (c2,p2,"4567",LocalDate.of(2021, 6, 12));

		repoTratamientos.save(t4);
		repoTratamientos.save(t5);
		
		List<Tratamiento> l2 = new ArrayList<>();
		l2.add(t4);
		l2.add(t5);
		c2.setTratamientos(l2);
		
		repoCultivos.saveAndFlush(c2);
		
		//cultivo con 1 tratamientos con ambos plazos
		Tratamiento t6 = new Tratamiento (c3,p3,"4546510",LocalDate.of(2021, 3, 22));

		repoTratamientos.save(t6);
		
		List<Tratamiento> l3 = new ArrayList<>();
		l3.add(t6);
		c3.setTratamientos(l3);
		
		repoCultivos.saveAndFlush(c3);
		
		//cultivo con 1 tratamientos sin plazo de reentrada
		Tratamiento t7 = new Tratamiento (c4,p4,"894951132",LocalDate.of(2021, 6, 23));

		repoTratamientos.save(t7);
		
		List<Tratamiento> l4 = new ArrayList<>();
		l4.add(t7);
		c4.setTratamientos(l4);
		
		repoCultivos.saveAndFlush(c4);
		
		//cultivo con 1 tratamientos sin plazos
		Tratamiento t8 = new Tratamiento (c5,p5,"26120",LocalDate.of(2021, 8, 10));

		repoTratamientos.save(t8);
		
		List<Tratamiento> l5 = new ArrayList<>();
		l5.add(t8);
		c5.setTratamientos(l5);
		
		repoCultivos.saveAndFlush(c5);
		
		
		
		
			
	}
}
