package com.vbkn.titan.form.cliente;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.struts.action.ActionMapping;

import com.vbkn.titan.entity.cliente.Cliente;
import com.vbkn.titan.form.BaseForm;

/**
 * @author Valter Bruno Konrad Neto
 */
public class ClienteForm extends BaseForm {
	
	private static final long serialVersionUID = -6122470398986762330L;

	private Integer codigo;
	
	private String nome;
	
	private String sobrenome;
	
	private String rg;
	
	private String email;
	
	private String telefone;
	
	private String sexo;
	
	private String data;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		this.setCodigo(null);
		this.setData(null);
		this.setEmail(null);
		this.setNome(null);
		this.setSobrenome(null);
		this.setSexo(null);
		this.setTelefone(null);
		this.setRg(null);
	}

	public Cliente getCliente(){
		Cliente cliente = new Cliente();
		cliente.setNome(this.getNome());
		cliente.setSobrenome(this.getSobrenome());
		cliente.setEmail(this.getEmail());
		cliente.setRg(this.getRg());
		cliente.setEmail(this.getEmail());
		cliente.setSexo(this.getSexo());
		cliente.setTelefone(this.getTelefone());
		
		String pattern = "dd/MM/yyyy";
        Locale locale = Locale.getDefault();
        DateLocaleConverter converter = new DateLocaleConverter(locale, pattern);
        cliente.setData((Date)converter.convert(this.getData()));
        
        return cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ClienteForm other = (ClienteForm) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
