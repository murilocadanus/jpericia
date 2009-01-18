package org.jpericia.analise.views.preference.pages;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jpericia.analise.messages.Messages;
import org.jpericia.common.entity.analise.Cena;

public class CenaImagemPreferencePage extends PreferencePage
{
	private Cena cena;

	private Text tituloTxt;
	
    public CenaImagemPreferencePage()
    {
        setTitle("Cena - Dados Imagem");
        setDescription("Dados da cena");
        noDefaultAndApplyButton();
        setValid(false);
    }

    public CenaImagemPreferencePage(Cena cena)
    {
        this.cena = cena;
        setTitle("Cena - Dados Imagem");
        setDescription("Dados da cena");
        noDefaultAndApplyButton();
        setValid(true);
    }
	
	@Override
	protected Control createContents(Composite parent)
	{
		Composite content = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		content.setLayout(gridLayout);
		
		final GridData gridDataLbl = new GridData(GridData.HORIZONTAL_ALIGN_END);
		final GridData gridDataTxt = new GridData(GridData.FILL_HORIZONTAL);

		// Campo titulo
		final Label tituloLbl = new Label(content, SWT.NONE);
		tituloLbl.setLayoutData(gridDataLbl);
		tituloLbl.setText(Messages.titulo);
		
		tituloTxt = new Text(content, SWT.BORDER);
		tituloTxt.setText(cena.getTitulo());
		tituloTxt.setTextLimit(40);
		tituloTxt.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				updatePageComplete();
			}
		});
		tituloTxt.setLayoutData(gridDataTxt);
		
		// Campo Imagem
		final Label imagemLbl = new Label(content, SWT.NONE);
		imagemLbl.setLayoutData(gridDataLbl);
		imagemLbl.setText(Messages.imagem);
		imagemLbl.setLayoutData(gridDataLbl);
		
		InputStream in = new ByteArrayInputStream(cena.getImagem());
		ImageDescriptor dadosImagem = ImageDescriptor.createFromImageData(new ImageData(in));
		final Label imagem = new Label(content, SWT.BORDER);
		imagem.setImage(dadosImagem.createImage());
		
		return content;
	}

	private void updatePageComplete()
	{
		setValid(false);
		if("".equals(tituloTxt.getText()))
		{
			setMessage(null);
			setErrorMessage(Messages.informaCenaTitulo);
			return;
		}

		setValid(true);
		setMessage(null);
		setErrorMessage(null);
	}
	
	public void setEntityObject()
	{
		cena.setTitulo(tituloTxt.getText());
	}
}
