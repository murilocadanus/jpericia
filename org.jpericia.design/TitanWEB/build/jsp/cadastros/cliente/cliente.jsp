<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html:javascript formName="clienteForm" />

<div align="center" class="title"><bean:message key="cliente.cadastro" bundle="cliente" /></div>

<div align="center" >

<html:errors />

<html:messages id="message" message="true" bundle="cliente" >
		<div class="mensagem">
			<br /> <bean:write name="message"/> <br /><br />
		</div>
</html:messages>

<html:form styleClass="cssform" action="/cadastro/cliente/salvar" onsubmit="return validateClienteForm(this);">  

<html:xhtml/>

<html:hidden property="method" value="salvar" />

<p>
<label>Nome:</label>
<html:text property="nome" />
</p>

<p>
<label>Sobrenome:</label>
<html:text property="sobrenome" />
</p>

<p>
<label>RG:</label>
<html:text property="rg" />
</p>

<p>
<label>Email:</label>
<html:text property="email" />
</p>

<p>
<label>Telefone:</label>
<html:text property="telefone" />
</p>

<p>
<label>Sex:</label>
Masculino: <html:radio property="sexo" value="1" />Feminino: <html:radio property="sexo" value="2" /><br />
</p>

<p>
<label>Data de Nascimento: (dd/mm/aaaa)</label>
<html:text property="data" />
</p>

<div style="margin-left: 150px;">
<html:submit value="Enviar" styleClass="botao" /> <html:reset value="Limpar" styleClass="botao"/> 
</div>

</html:form>

</div>
