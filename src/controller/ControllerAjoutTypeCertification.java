/**
 * 
 */
package controller;

import java.io.File;
import java.util.ArrayList;

import model.TagParameter;
import model.TagParameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import tools.ToolsXML;
import dao.DAOCertificationType;
import dao.DAODocumentPDF;
import domain.CertificationType;
import domain.Log;
import domain.TypeLog;

/**
 * @author dtadmi
 * 
 */
public class ControllerAjoutTypeCertification
{

	private String xmlTagParametersFile = "\\tagParameters.xml";

	final Marker marker1 = MarkerFactory
			.getMarker(TypeLog.ERREUR_LECTURE_CONFIGURATION.toString());

	final Logger logger = LoggerFactory
			.getLogger(ControllerAjoutTypeCertification.class);
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
			String PDF_REASON, String PDF_LOCATION, String PDF_CONTACT, boolean defaut)
	{

		if (!((null == id || "".equals(id)) || (null == name || "".equals(name))))
		{
			if (null == parameters)
				parameters = new TagParameters();
			TagParameter type = new TagParameter(name, id, DATA_METIER,
					PDF_REASON, PDF_LOCATION, PDF_CONTACT, defaut);
			parameters.addType(type);
			return DAOCertificationType.getInstance().addCertificationType(type);
		}

		return false;

	}

	public boolean addType(TagParameter type)
	{
		if (null != type)
		{
			return addType(type.getId_type(), type.getName(),
					type.getDATA_METIER(), type.getPDF_REASON(),
					type.getPDF_LOCATION(), type.getPDF_LOCATION(), type.isDefaut());
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

	public boolean modifyParameters(String editId, String editName,
			String id_type, String name, String pDF_REASON,
			String pDF_LOCATION, String pDF_CONTACT, boolean defaut)
	{
		getType(editId, editName).modifyParameters(name, id_type, pDF_REASON,
				pDF_LOCATION, pDF_CONTACT, defaut);

		return DAOCertificationType.getInstance().updateCertificationType(
				editId, editName, id_type, name);
	}

	public boolean removeType(String id_type, String name)
	{
		DAOCertificationType.getInstance().deleteCertificationType(id_type,
				name);
		return parameters.removeType(this.getType(id_type, name));
	}

	public boolean removeType(TagParameter type)
	{
		DAOCertificationType.getInstance().deleteCertificationType(
				type.getId_type(), type.getName());
		return parameters.removeType(type);
	}

	/**
	 * @return the parameters
	 */
	public TagParameters getParameters()
	{
		if(null != DAOCertificationType.getInstance().getAllCertifis())
			parameters = getParametersFromDAO();
		return parameters;
	}

	public TagParameters getParameters(String parameterPath)
	{
		try
		{
			File param = (new File(parameterPath + xmlTagParametersFile));
			if (param.exists())
			{
				parameters = ToolsXML.readTagConfig(parameterPath
						+ xmlTagParametersFile); 
				// ICI LOG
			}
			/*if(param.delete())
			{
				System.out.println(param.getName() + " is deleted!");
			}
			else
			{
				System.out.println(param.getName()+ ": Delete operation failed!");
			}*/
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
	
	public TagParameters getParametersFromDAO()
	{
		ArrayList<CertificationType> certifs = DAOCertificationType.getInstance().getAllCertifis();
		
		if(certifs.size()>0)
		{
			if(null == parameters)
				parameters = new TagParameters();
			for(CertificationType certif : certifs)
			{
				parameters.addType(new TagParameter(certif.getName(), certif.getId_type(), certif.getDATA_METIER(), certif.getPDF_REASON(), certif.getPDF_LOCATION(), certif.getPDF_CONTACT(), certif.isDefaut()));
			}
		}
		
		return parameters;
	}
	
	public ArrayList<CertificationType> getCertifsFromDAO()
	{
		ArrayList<CertificationType> certifs = DAOCertificationType.getInstance().getAllCertifis();
		
		if(certifs.size()>0)
		{
			if(null == parameters)
				parameters = new TagParameters();
			for(CertificationType certif : certifs)
			{
				parameters.addType(new TagParameter(certif.getName(), certif.getId_type(), certif.getDATA_METIER(), certif.getPDF_REASON(), certif.getPDF_LOCATION(), certif.getPDF_CONTACT(), certif.isDefaut()));
			}
		}
		
		return certifs;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(TagParameters parameters)
	{
		this.parameters = parameters;
	}

	public TagParameter getType(String id, String name)
	{
		return parameters.getType(id, name);
	}
	
	public TagParameter getType(String name)
	{
		return parameters.getType(name);
	}

	public boolean saveTagXml(String savePath)
	{
		return ToolsXML.createTagXMLFile(savePath);
	}

	
	public boolean addTypeToDocument(long id, TagParameter type)
	{
		return DAODocumentPDF.getInstance().setCertificationType(id, type);
	}
}
