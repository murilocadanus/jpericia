package org.jpericia.core.ui.listeners;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.PlatformObject;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.core.ui.preferences.PropertyChangeEvents;

public abstract class AbstractResultList extends PlatformObject
{

	protected List<AbstractEntity> results = new ArrayList<AbstractEntity>();

	protected transient PropertyChangeSupport listeners = new PropertyChangeSupport(
			this);

	public void addPropertyChangeListener(PropertyChangeListener l)
	{
		if (l == null)
		{
			throw new IllegalArgumentException();
		}
		this.listeners.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l)
	{
		this.listeners.removePropertyChangeListener(l);
	}

	protected void firePropertyChange(String prop, Object old, Object newValue)
	{
		if (this.listeners.hasListeners(prop))
		{
			this.listeners.firePropertyChange(prop, old, newValue);
		}
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public void add(AbstractEntity o)
	{
		if (!this.results.contains(o))
		{
			this.results.add(o);
			firePropertyChange(PropertyChangeEvents.ADD, null, o);
		}
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public void addAll(ArrayList<AbstractEntity> o)
	{
		if (!this.results.contains(o))
		{
			this.results.addAll(o);
			firePropertyChange(PropertyChangeEvents.ADD, null, o);
		}
	}	
	
	/**
	 * 
	 * @see java.util.List#clear()
	 */
	public void clear()
	{
		this.results.clear();
		firePropertyChange(PropertyChangeEvents.CLEAR, this.results, null);
	}

	/**
	 * @return
	 * @see java.util.List#iterator()
	 */
	public Iterator<AbstractEntity> iterator()
	{
		return this.results.iterator();
	}

	/**
	 * @return
	 * @see java.util.List#size()
	 */
	public int size()
	{
		return this.results.size();
	}

	/**
	 * @return
	 * @see java.util.List#getResultList()
	 */
	public List<AbstractEntity> getResultList()
	{
		return results;
	}
}
