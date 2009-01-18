package org.jpericia.testcase.pertio;

import java.util.ArrayList;

import javax.ejb.EJB;

import junit.framework.TestCase;

import org.jpericia.businessdelegate.perito.PeritoDelegate;
import org.jpericia.common.entity.AbstractEntity;
import org.jpericia.common.entity.generic.Uf;
import org.jpericia.common.entity.perito.FuncaoPerito;
import org.jpericia.common.entity.perito.Perito;
import org.jpericia.common.entity.perito.TituloPerito;
import org.jpericia.exception.BusinessDelegateException;

public class TestPerito extends TestCase 
{
	@EJB
	public void test() 
	{
		try 
		{
			PeritoDelegate od = new PeritoDelegate();
			
			FuncaoPerito fp = new FuncaoPerito();
			fp.setCodigo(new Long(50));
			
			TituloPerito tp = new TituloPerito();
			tp.setCodigo(new Long(50));
			
			Uf uf = new Uf();
			uf.setCodigo(new Long(1));
			
			Perito p = new Perito();
			p.setFuncaoPerito(fp);
			p.setTituloPerito(tp);
			p.setUf(uf);
			p.setUsuario("psfax2");
			p.setSenha("qwe123");
			p.setGestor(true);
			p.setNome("Murilo Cadanus da Costa");
			p.setTelefone(new Long(33529551));
			p.setCelular(new Long(99853606));
			p.setBairro("Sao Lourenco");
			p.setCidade("Curitiba");
			p.setLogradouro("Rua");
			p.setNumero(new Integer(3803));
			p.setEndereco("Nilo Pecanha");
			
			od.inserir(p);
			
			
			
			//ArrayList<AbstractEntity> peritoList = od.pesquisar();
			//System.out.println(peritoList);
			//od.atualizar(fp);
			//od.remover(fp);
		} 
		catch (BusinessDelegateException e) 
		{
			e.printStackTrace();
		}
	}
}
