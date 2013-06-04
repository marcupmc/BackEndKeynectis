/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author dtadmi
 * 
 */
public class TagParameters
{

	ArrayList<TagParameter> types = null;

	/**
	 * 
	 */
	public TagParameters()
	{
		super();
		// TODO Auto-generated constructor stub
		types = new ArrayList<TagParameter>();
	}

	/**
	 * @param types
	 */
	public TagParameters(ArrayList<TagParameter> types)
	{
		super();
		this.types = types;
	}

	/**
	 * @return the types
	 */
	public ArrayList<TagParameter> getTypes()
	{
		return types;
	}

	/**
	 * @param types
	 *            the types to set
	 */
	public void setTypes(ArrayList<TagParameter> types)
	{
		this.types = types;
	}

	public boolean addType(TagParameter type)
	{
		boolean state = false;

		if (null == types)
			types = new ArrayList<TagParameter>();

		if (!contains(type.getId_type(), type.getName()))
		{
			types.add(type);
			if (type.isDefaut())
				setDefaultType(type);
			state = true;
		}

		return state;
	}

	public boolean removeType(TagParameter type)
	{
		boolean state = false;

		if (null == types)
			types = new ArrayList<TagParameter>();

		if (contains(type.getId_type(), type.getName()))
		{
			types.remove(type);
			state = true;
		}

		return state;
	}

	public boolean isEmpty()
	{
		if (null == types)
			types = new ArrayList<TagParameter>();

		return types.isEmpty();
	}

	private boolean contains(String id, String name)
	{
		boolean state = false;
		if (null == types)
			types = new ArrayList<TagParameter>();

		if (!types.isEmpty())
		{
			for (TagParameter type : types)
			{
				if (id.equals(type.getId_type()) && name.equals(type.getName()))
				{
					state = true;
					break;
				}
			}
		}

		return state;
	}

	public TagParameter getType(String id, String name)
	{
		if (contains(id, name))
		{
			for (TagParameter type : types)
			{
				if (id.equals(type.getId_type()) && name.equals(type.getName()))
				{
					return type;
				}
			}
		}

		return null;
	}

	public void setDefaultType(String id, String name)
	{
		getType(id, name).setDefaut(true);
		for (TagParameter type : types)
		{
			if (!(id.equals(type.getId_type()) && name.equals(type.getName())))
			{
				type.setDefaut(false);
			}
		}
	}

	public void setDefaultType(TagParameter typ)
	{
		typ.setDefaut(true);
		for (TagParameter type : types)
		{
			if (!typ.equals(type))
			{
				type.setDefaut(false);
			}
		}
	}

}
