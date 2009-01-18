package org.jpericia.pericia.actions;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.analise.Analise;
import org.jpericia.common.entity.analise.Cena;
import org.jpericia.common.entity.objeto.Evidencia;
import org.jpericia.common.entity.objeto.Objeto;
import org.jpericia.common.entity.pericia.Anexo;
import org.jpericia.common.entity.pericia.Pericia;
import org.jpericia.core.ui.listeners.AbstractResultList;
import org.jpericia.pericia.bussinessdelegate.PericiaDelegate;
import org.jpericia.pericia.views.PericiaView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class LaudoVisualizarAction extends Action
{
	private PericiaView view;
	
	private static final Font fonteCabecalho = FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD, new Color(0, 0, 0));
	
	private static final Font fonteTitulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD, new Color(0, 0, 0));
	
	private static final Font fonteTexto = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, new Color(0, 0, 0));
	
	private static final Font fonteTextoNegrito = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, new Color(0, 0, 0));
	
	private static final Font fonteTextoTabela = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, new Color(0, 0, 0));
	
	private static final Font fonteTextoTabelaNegrito = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, new Color(0, 0, 0));
	
    public LaudoVisualizarAction(PericiaView view, String text)
    {
    	super(text);
        this.view = view;
    }
    
    public void run()
    {   
    	
    	try
    	{
	        FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(), SWT.OPEN);
	        fd.setText("Salvar");
	        fd.setFilterPath("/");
	        String[] filterExt = { "*.pdf" };
	        fd.setFilterExtensions(filterExt);
	        String pdfPath = fd.open();
	        
	        if(null != pdfPath)
	        {
	        	if (!pdfPath.endsWith(".pdf"))
	        	{
	        		pdfPath = pdfPath + ".pdf";
	        	}
	        	
	    		Pericia pericia = view.getPericia();
	    		
	    		/*
	    		 * Obtem as analises da pericia.
	    		 */
				AbstractResultList abstractObjetoList = PericiaDelegate.getInstance().pesquisarAnalises(pericia);
				java.util.List<AbstractEntity> analiseList = abstractObjetoList.getResultList();
	    		
				/*
				 * Obtem os anexos da pericia. 
				 */
				AbstractResultList abstractAnexoList = PericiaDelegate.getInstance().pesquisarAnexos(pericia);
				java.util.List<AbstractEntity> anexoList = abstractAnexoList.getResultList();
				
	    		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
		
				document.open();
				
				document.add(new Paragraph("Laudo Pericial", fonteCabecalho));
				document.add(Chunk.NEWLINE);
				
				document.add(new Paragraph("Prezado " + pericia.getContatoOrganizacao().getTituloContato().getTitulo() + " " + pericia.getContatoOrganizacao().getNome() + ", venho trazer aos autos o laudo pericial realizado para a perícia: " + pericia.getTitulo() + ".", fonteTexto));
				document.add(Chunk.NEWLINE);
				
				document.add(new Paragraph("Introdução", fonteTitulo));
				document.add(new Paragraph(pericia.getIntroducao()));
				document.add(Chunk.NEWLINE);
				
				document.add(new Paragraph("Descrição da perícia", fonteTitulo));
				document.add(new Paragraph(pericia.getDescricao()));
				document.add(Chunk.NEWLINE);
				
				document.add(new Paragraph("Vistoria e análises dos fatos", fonteTitulo));
				
				/*
				 * Exibe todos os dados das analises.
				 */
				for (Iterator iterator = analiseList.iterator(); iterator.hasNext();) {
					Analise analise = (Analise) iterator.next();
					
					document.add(new Paragraph("Analise: " + analise.getTipoAnalise().getNome(), fonteTexto));
					document.add(new Paragraph("Título: " + analise.getTitulo(), fonteTexto));
					document.add(new Paragraph("Descrição: " + analise.getDescricao(), fonteTexto));
					
					if (analise.getResultado() != null)
					{
						document.add(new Paragraph("Resultado: " + analise.getResultado(), fonteTexto));
					}
					document.add(Chunk.NEWLINE);
						
					/*
					 * Exibe os objetos continos em uma analise
					 */
					Set<Objeto> objetos = analise.getObjetos();
					
					if (objetos.size() > 0)
					{
						document.add(new Paragraph("Objetos contidos nesta análise", fonteTextoNegrito));
						document.add(Chunk.NEWLINE);
						
						int referenciaObjeto = 0;
						for (Iterator iterator2 = objetos.iterator(); iterator2.hasNext();) {
							
							referenciaObjeto++;
							Objeto objeto = (Objeto) iterator2.next();
							
				            
//							PdfPTable table = new PdfPTable(4);
//				            PdfPCell cell = new PdfPCell();
				            
	/*			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				            table.addCell(new Paragraph("Objeto", fonteTextoTabelaNegrito));
				            table.addCell(new Paragraph("Tipo", fonteTextoTabelaNegrito));
				            table.addCell(new Paragraph("Categoria", fonteTextoTabelaNegrito));
				            table.addCell(new Paragraph("Descrição", fonteTextoTabelaNegrito));
				            
				            table.addCell(new Paragraph(objeto.getTitulo(), fonteTextoTabela));
				            table.addCell(new Paragraph(objeto.getTipoObjeto().getTitulo(), fonteTextoTabela));
				            table.addCell(new Paragraph(objeto.getTipoObjeto().getCategoriaTipoObjeto().getNome(), fonteTextoTabela));
				            table.addCell(new Paragraph(objeto.getDescricao(), fonteTextoTabela));*/
				            
//				            document.add(table);
							
							document.add(new Paragraph("Refêrencia: " + referenciaObjeto));
							document.add(new Paragraph("Objeto: " + objeto.getTitulo()));
							document.add(new Paragraph("Tipo: " + objeto.getTipoObjeto().getTitulo()));
							document.add(new Paragraph("Categoria: " + objeto.getTipoObjeto().getCategoriaTipoObjeto().getNome()));
							document.add(new Paragraph("Descrição: " + objeto.getDescricao()));
				            
							if (objeto.getImagem() != null)
							{
								Image imagemObjeto = Image.getInstance(objeto.getImagem());
					            imagemObjeto.setAlignment(Image.MIDDLE);
								imagemObjeto.scaleToFit(100, 200);
								document.add(imagemObjeto);
							}
				            
				            document.add(Chunk.NEWLINE);
				            
				            /*
				             * Obtem as evidencias do objeto.
				             */
				            AbstractResultList abstractEvidenciaList = PericiaDelegate.getInstance().pesquisarEvidencias(objeto);
							java.util.List<AbstractEntity> evidenciaList = abstractEvidenciaList.getResultList();
							
							if (evidenciaList.size() > 0)
							{
								document.add(Chunk.NEXTPAGE);
								document.add(new Paragraph("Evidências contidas neste objeto", fonteTextoNegrito));
								document.add(Chunk.NEWLINE);
								
								PdfPTable tableEvidencia = new PdfPTable(2);
					            PdfPCell cellEvidencia = new PdfPCell();
					            
					            cellEvidencia.setHorizontalAlignment(Element.ALIGN_CENTER);
					            tableEvidencia.addCell(new Paragraph("Evidência", fonteTextoNegrito));
					            tableEvidencia.addCell(new Paragraph("Descrição", fonteTextoNegrito));
								
								for (Iterator iterator3 = evidenciaList.iterator(); iterator3.hasNext();) {
									Evidencia evidencia = (Evidencia) iterator3.next();

						            tableEvidencia.addCell(evidencia.getTitulo());
						            tableEvidencia.addCell(evidencia.getDescricao());
						            
						            document.add(tableEvidencia);

									if (evidencia.getImagem() != null)
									{
							            Image imagemEvidencia = Image.getInstance(evidencia.getImagem());
							            imagemEvidencia.setAlignment(Image.MIDDLE);
										imagemEvidencia.scaleToFit(100, 200);
							            document.add(imagemEvidencia);
									}
								}
							}
						}	
						
						document.add(Chunk.NEWLINE);
					}
					
					/*
					 * Exibe as cenas contidas analises
					 */
					AbstractResultList abstractCenaList = PericiaDelegate.getInstance().pesquisarCenas(analise);
					java.util.List<AbstractEntity> cenaList = abstractCenaList.getResultList();
					
					for (Iterator iterator2 = cenaList.iterator(); iterator2.hasNext();) {
						Cena cena = (Cena) iterator2.next();

						document.add(new Paragraph("Título da Cena: " + cena.getTitulo(), fonteTexto));
						if (cena.getImagem() != null)
						{
							Image imagemCena = Image.getInstance(cena.getImagem());
				            imagemCena.setAlignment(Image.MIDDLE);
							imagemCena.scaleToFit(200, 400);
							document.add(imagemCena);
						}
						document.add(new Paragraph("Descrição: " + cena.getDescricao(), fonteTexto));
						
						document.add(Chunk.NEWLINE);
					}
				}
				
				/*
				 * Exibe todos os dados dos anexos.
				 */
				for (Iterator iterator = anexoList.iterator(); iterator.hasNext();) {
					Anexo anexo = (Anexo) iterator.next();
					
					document.add(new Paragraph("Título do Anexo: " + anexo.getTitulo(), fonteTexto));
					document.add(new Paragraph("Descrição: " + anexo.getTexto(), fonteTexto));
					
					document.add(Chunk.NEWLINE);
				}
				
				/*
				 * Exibe conclusao
				 */
				document.add(new Paragraph("Conclusão", fonteTitulo));
				document.add(new Paragraph(pericia.getConclusao()));
				document.add(Chunk.NEWLINE);
				
				document.close();
	        	
	        }
    		
    	}
    	catch(Exception e)
    	{
    		//TODO - Tratar permissao de escrita
    		e.printStackTrace();
    	}
    }
}
