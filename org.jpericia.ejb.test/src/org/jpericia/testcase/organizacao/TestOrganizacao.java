package org.jpericia.testcase.organizacao;

import junit.framework.TestCase;

import org.jpericia.businessdelegate.organizacao.OrganizacaoDelegate;
import org.jpericia.businessdelegate.organizacao.TipoOrganizacaoDelegate;
import org.jpericia.common.entity.organizacao.Organizacao;
import org.jpericia.common.entity.organizacao.TipoOrganizacao;
import org.jpericia.exception.BusinessDelegateException;

public class TestOrganizacao extends TestCase 
{
	public void test() 
	{
		try 
		{ 
/*			TipoOrganizacaoDelegate otd = new TipoOrganizacaoDelegate();
			
			TipoOrganizacao tpOrg = new TipoOrganizacao();
			tpOrg.setNome("Policia civil");
			
			otd.inserir(tpOrg);*/
			
			
			OrganizacaoDelegate od = new OrganizacaoDelegate();
			
			Organizacao org = new Organizacao();
			org.setNome("13 Distrito Policial");
			org.setCidade("Curitiba");
			org.setBairro("Centro");
			org.setEndereco("Sete de Setembro");
			org.setLogradouro("Avenida");
			org.setNumero(new Long(1234));

			TipoOrganizacao tpOrg = new TipoOrganizacao();
			tpOrg.setCodigo(new Long(50));
			
			org.setTipoOrganizacao(tpOrg);		
			od.inserir(org);
		} 
		catch (BusinessDelegateException e) 
		{
			e.printStackTrace();
		}
	}
}
