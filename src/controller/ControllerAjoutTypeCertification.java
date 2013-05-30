/**
 * 
 */
package controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import domain.Log;
import domain.TypeLog;

import tools.ToolsXML;
import model.TagParameter;
import model.TagParameters;

/**
 * @author dtadmi
 * 
 */
public class ControllerAjoutTypeCertification
{
	
	private String xmlTagParametersFile = "\\tagParameters.xml";
	
	final Marker marker1 = MarkerFactory.getMarker(TypeLog.ERREUR_LECTURE_CONFIGURATION.toString());
	
	final Logger logger = LoggerFactory.getLogger(ControllerAjoutTypeCertification.class);
	Log l;

	// SINGLETON
	public static ControllerAjoutTypeCertification getInstance()
	{
		if (null == instance)
		{
			instance = new ControllerAjoutTypeCertification();
		}
		return instance;
	}

	/**
		 * 
		 */
	private ControllerAjoutTypeCertification()
	{
		super();
		// TODO Auto-generated constructor stub
		parameters = new TagParameters();
	}

	private static ControllerAjoutTypeCertification instance;

	private TagParameters parameters = null;

	public boolean addType(String id, String name, String DATA_METIER,
			String PDF_REASON, String PDF_LOCATION, String PDF_CONTACT)
	{

		if (!((null == id || "".equals(id)) || (null == name || "".equals(name))))
		{
			if (null == parameters)
				parameters = new TagParameters();
			TagParameter type = new TagParameter(name, id, DATA_METIER,
					PDF_REASON, PDF_LOCATION, PDF_CONTACT);
			return parameters.addType(type);
		}

		return false;

	}

	public boolean addType(TagParameter type)
	{
		if (null != type)
		{
			return addType(type.getId_type(), type.getName(),
					type.getDATA_METIER(), type.getPDF_REASON(),
					type.getPDF_LOCATION(), type.getPDF_LOCATION());
		}

		return false;

	}

	public boolean addTypes(TagParameters types)
	{
		if (null != types)
		{
			for (TagParameter type : types.getTypes())
			{
				return addType(type);
			}
		}

		return false;
	}

	
	/**
	 * @return the parameters
	 */
	public TagParameters getParameters()
	{
		return parameters;
	}
	
	public TagParameters getParameters(String parameterPath)
	{
		try
		{
			if ((new File(parameterPath + xmlTagParametersFile)).exists())
			{
				parameters = ToolsXML.readTagConfig(parameterPath + xmlTagParametersFile);
				//ICI LOG				
			}
		}
		catch (Exception e)
		{
			logger.info(marker1, "Erreur de lecture des configurations", l);
			e.printStackTrace();
			System.out.println("ControllerCertification: Exception\n"
					+ "certificationPDFFromXML: " + e.getMessage());
		}
		
		return parameters;
	}

	
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(TagParameters parameters)
	{
		this.parameters = parameters;
	}	
	
	
	public TagParameter getType(String id, String name)
	{
		return parameters.getType(id, name);
	}
	
	public boolean saveTagXml(String savePath)
	{
		return ToolsXML.createTagXMLFile(savePath);
	}
	
}
