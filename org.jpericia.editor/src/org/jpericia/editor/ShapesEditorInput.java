/*******************************************************************************
 * Copyright (c) 2005 Chris Aniszczyk
�* All rights reserved. This program and the accompanying materials
�* are made available under the terms of the Eclipse Public License v1.0
�* which accompanies this distribution, and is available at
�* http://www.eclipse.org/legal/epl-v10.html
�*
�* Contributors:
�*����Chris Aniszczyk - initial API and implementation
 *    IBM Corporation
�*******************************************************************************/

package org.jpericia.editor;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.jpericia.editor.model.ShapesDiagram;

public class ShapesEditorInput implements IPathEditorInput {
	
	private IPath path;
	
	public ShapesEditorInput(IPath path) {
		this.path = path;
	}
	
	public boolean exists() {
		return path.toFile().exists();
	}
	
	public ImageDescriptor getImageDescriptor() {
		return EditorPlugin.imageDescriptorFromPlugin("org.jpericia.editor","shapes.gif");
	}
	
	public String getName() {
		return path.toString();
	}
	
	public IPersistableElement getPersistable() {
		return null;
	}
	
	public String getToolTipText() {
		return path.toString();
	}
	
	public Object getAdapter(Class adapter) {
		return null;
	}
	
	public int hashCode() {
		return path.hashCode();
	}
	
	public ShapesDiagram getShapesDiagram() {
		return new ShapesDiagram();
	}
	
	public IPath getPath() {
		return path;
	}
	
}
