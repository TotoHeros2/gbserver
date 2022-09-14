package ch.hcuge.simed.gbserver.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.hcuge.simed.gbserver.model.Dimension;
import ch.hcuge.simed.gbserver.model.Dimensions;
import ch.hcuge.simed.gbserver.model.Field;



@RestController
@CrossOrigin(origins = "*")
public class DimensionController {
	@RequestMapping(method = RequestMethod.GET,path = "/services/v2/dimensions", produces="application/json")
//	public List<Dimension> getDimensions()
	public Dimensions getDimensions()
	{
		Field field = new Field();
		field.setName("birthDate");
		field.setType("Timestamp");
		Field field2 = new Field();
		field2.setName("sex");
		field2.setType("String");
		Dimension dimension = new Dimension();
		dimension.setDimensionType("subject");
		dimension.setName("patient");
		dimension.setValueType("Object");
		dimension.setFields(Arrays.asList(field,field2));
		
		Dimension dimension2 = new Dimension();
		dimension2.setDimensionType("attribute");
		dimension2.setName("concept");
		dimension2.setValueType("Object");
		Field field3 = new Field();
		field3.setName("conceptPath");
		field3.setType("String");
		Field field4 = new Field();
		field4.setName("conceptCode");
		field4.setType("String");
		dimension2.setFields(Arrays.asList(field3,field4));
		
		
		Dimension dimension3 = new Dimension();
		dimension3.setDimensionType("subject");
		dimension3.setName("measurement");
		dimension3.setValueType("Object");
		
		
		Dimension dimension4 = new Dimension();
		dimension4.setDimensionType("subject");
		dimension4.setName("observation");
		dimension4.setValueType("Object");
		
		Dimensions dimensions = new Dimensions();
		dimensions.getDimensions().add(dimension);
		dimensions.getDimensions().add(dimension2);
		dimensions.getDimensions().add(dimension3);
		dimensions.getDimensions().add(dimension4);

		return dimensions;
//		return Arrays.asList(dimension,dimension2);
	}

}
